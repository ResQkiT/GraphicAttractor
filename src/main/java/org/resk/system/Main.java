package org.resk.system;

import org.resk.system.adapters.service.ScreenshotsAdapter;
import org.resk.system.colorsystem.ColorLoader;
import org.resk.system.colorsystem.Patterns;
import org.resk.system.commands.ClearCommand;
import org.resk.system.commands.MakeScreenshotCommand;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;
import org.resk.units.points.Point;
import org.resk.units.points.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.*;

public class Main extends Canvas
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCALE = 1;
    private String title = "Game";
    private volatile boolean running = false;
    private BufferStrategy bs = null;
    private Graphics g = null;
    public volatile static Render renderer;
    private JFrame frame = new JFrame(title);
    private JFrame dialog ;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int pixels[] = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public enum AttractorsEnum {
        Lorenz(new LorenzPoint(renderer)),
        Rossel(new RosselPoint(renderer)),
        NAttractor(new NAttractorPoint(renderer)),
        Polynomial(new PolynomialAttractorPoint(renderer, "LUFBBFISGJYS")),
        BurkleShawAttractorPoint(new BurkleShawAttractorPoint(renderer)),
        DenTsucsAttractor(new DenTsucsAttractorPoint(renderer)),
        RRAttractorPoint(new RRAttractorPoint(renderer));
        private BasePointType type;
        private Render rend;
        AttractorsEnum(BasePointType type) {
            this.type = type;
        }
        public void setRend(Render rend) {
            this.rend = rend;
        }

        public BasePointType getType() {
            return type;
        }
    }
    public Main() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        renderer = new Render(WIDTH, HEIGHT, pixels);
        init();
        initDialog();
        start();
    }

    private void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long jvmLastTime = System.nanoTime();
                long timer = System.currentTimeMillis();
                double jvmPartTime = 1_000_000_000.0 / 60;
                double delta = 0;
                int updates = 0;
                int frames = 0;
                while (true){
                    long jvmNow = System.nanoTime();
                    delta += (jvmNow - jvmLastTime);
                    jvmLastTime = jvmNow;
                    if(delta >= jvmPartTime) {
                        update();
                        updates += 1;
                        delta = 0;
                    }
                    render();
                    frames += 1;
                    if(System.currentTimeMillis() - timer > 1000){
                        timer += 1000;
                        frame.setTitle(title + " | " + "Updates: "+ updates + " Frames: " + frames);
                        updates = 0;
                        frames = 0;
                    }
                }
            }
        }).start();
    }
    private void update(){}
    private void render(){
        if(bs == null){
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }
        renderer.render();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0,0, getWidth(), getHeight(), null);
        g.dispose();
        bufferSwap();
    }

    private void bufferSwap(){
        bs.show();
    }
    private void init() {
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        this.addMouseMotionListener(new ScreenshotsAdapter(renderer));
        this.addMouseListener(new ScreenshotsAdapter(renderer));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    ArrayList<Label> settingsLabels = new ArrayList<>();
    ArrayList<TextField> settingsTextFields = new ArrayList<>();
    private void generateSettingsBox(final org.resk.system.properties.Properties properties){
        final HashMap map = properties.getList();
        Set<String> keys = map.keySet();
        String[] s = keys.toArray(new String[keys.size()]);
        int deltaY = 0;
        for (final String propName: s) {
            final Property property = properties.getByName(propName);

            final TextField tf = new TextField();
            final Label l = new Label(propName);

            l.setBounds(24, 145 + deltaY, 50, 34);
            tf.setText( ( (Property) map.get(propName) ).getValue().toString()  );
            tf.setBounds(54 + 50, 145 + deltaY, 100, 34);
            tf.addTextListener(new TextListener() {
                @Override
                public void textValueChanged(TextEvent e) {
                    if ("".equals(tf.getText()))
                        return;
                    System.out.println(tf.getText());
                    BasePointType bpt = comboBoxType.getItemAt(comboBoxType.getSelectedIndex()).getType();

                    property.setValue(tf.getText().toString());
                    properties.setByName(l.getText() , property);
                    bpt.setProperties(properties);
                }
            });
            settingsLabels.add(l);
            settingsTextFields.add(tf);
            dialog.add(l);
            dialog.add(tf);
            deltaY += 37;
        }
    }
    private void deleteSettingsBox(){
        for (Label l: settingsLabels
             ) {
            dialog.remove(l);
        }
        for (TextField tf: settingsTextFields
             ) {
            dialog.remove(tf);
        }
        settingsLabels.clear();
        settingsTextFields.clear();
        dialog.revalidate();
    }
    private JComboBox<AttractorsEnum> comboBoxType;
    private void initDialog(){
        dialog = new JFrame();

        dialog.setSize(400,640);
        dialog.setResizable(true );

        Label simuText = new Label("Симуляция");
        simuText.setBounds(13, 47, 164, 27);
        dialog.add(simuText);

        Label gradText = new Label("Градиент");
        gradText.setBounds(13, 89, 164, 27);
        dialog.add(gradText);

        comboBoxType = new JComboBox<>(AttractorsEnum.values());
        comboBoxType.setBounds(204,47, 183, 27);
        comboBoxType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttractorsEnum en = comboBoxType.getItemAt(comboBoxType.getSelectedIndex());
                BasePointType bpt = en.getType();
                Properties prop = bpt.getProperties();
                deleteSettingsBox();
                generateSettingsBox(prop);
            }
        });
        dialog.add(comboBoxType);

        final JComboBox<Patterns> comboBoxPattern = new JComboBox<>(  Patterns.values() );
        comboBoxPattern.setBounds(204,89, 183, 27);
        dialog.add(comboBoxPattern);

        JButton screenshotButton = new JButton("Скриншот");
        screenshotButton.setBounds(0, 518, 192, 40);
        screenshotButton.setBackground(Color.GRAY);
        screenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MakeScreenshotCommand(renderer).exec();
            }
        });

        JButton clearB = new JButton("Очистить");
        clearB.setLocation(208,518);
        clearB.setSize(192, 40);
        clearB.setBackground(Color.RED);
        clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
                new ClearCommand(renderer).exec();
            }
        });

        JButton startB=new JButton("Запуск");
        startB.setLocation(0,560);
        startB.setSize(400, 40);
        startB.setBackground(Color.GRAY);
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = true;
                final PointType pt = comboBoxType.getItemAt(comboBoxType.getSelectedIndex()).getType();
                final Patterns pattern = comboBoxPattern.getItemAt(comboBoxPattern.getSelectedIndex());
                ColorLoader.init(pattern.getPath());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Point start_point = new Point(1, 0, 0, pt);
                        while (running){

                            Point new_point = start_point.getNext(0.0001);
                            //System.out.println(new_point);
                            new_point.draw();
                            start_point = new_point;
                        }
                    }
                }).start();
            }
        });
        dialog.add(startB);//adding button in JFrame
        dialog.add(clearB);
        dialog.add(screenshotButton);
        dialog.setLayout(null);
        dialog.setVisible(true);
    }
    public static void main(String[] args) {
        Main main = new Main();
        ColorLoader.init("src/main/java/org/resk/patterns/rainbow.jpg");
    }

}
