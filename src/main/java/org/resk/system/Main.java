package org.resk.system;


import org.resk.units.*;
import org.resk.units.Point;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Hello world!
 *
 */
public class Main extends Canvas
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCALE = 1;
    private String title = "Game";
    private boolean running = false;
    private BufferStrategy bs = null;
    private Graphics g = null;
    public static Render renderer;
    private JFrame frame = new JFrame(title);
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int pixels[] = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public enum AttractorsEnum {

        Lorenz(new LorenzPoint(renderer)),
        Rossel(new RosselPoint(renderer)),
        NAttractor(new NAttractorPoint(renderer)),
        Polynomial(new PolynomialAttractorPoint(renderer, "LUFBBFISGJYS"));
        private PointType type;
        private Render rend;
        AttractorsEnum(PointType type) {
            this.type = type;
        }
        public void setRend(Render rend) {
            this.rend = rend;
        }

        public PointType getType() {
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void initDialog(){
        JFrame f=new JFrame();
        f.setSize(400,640);
        f.setResizable(true );

        Label simuText = new Label("Симуляция");
        simuText.setBounds(13, 47, 164, 27);
        f.add(simuText);

        Label gradText = new Label("Градиент");
        gradText.setBounds(13, 89, 164, 27);
        f.add(gradText);

        final JComboBox<AttractorsEnum> comboBox = new JComboBox<>(AttractorsEnum.values());
        comboBox.setBounds(204,47, 183, 27);
        f.add(comboBox);

        JButton clearB = new JButton("Очистить");
        clearB.setLocation(208,518);
        clearB.setSize(192, 40);
        clearB.setBackground(Color.RED);
        clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
                renderer.clear();
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
                final PointType pt = comboBox.getItemAt(comboBox.getSelectedIndex()).getType();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Point start_point = new Point(0, 0, 0, pt);
                        while (running){

                            Point new_point = start_point.getNext(0.0001);
                            System.out.println(new_point);
                            new_point.draw();
                            start_point = new_point;
                        }
                    }
                }).start();
            }
        });

        f.add(startB);//adding button in JFrame
        f.add(clearB);
        f.setLayout(null);
        f.setVisible(true);
    }
    /*
    добавить запуск по нажатию кнопки
    добавить выбор рисунка (Factory)
    добавить возможноть выбирать начальную точку построения (будет отмечена белым)

     */
    public static void main(String[] args) {
        Main main = new Main();
        ColorLoader.init("src/main/java/org/resk/patterns/rainbow.jpg");

    }

}
