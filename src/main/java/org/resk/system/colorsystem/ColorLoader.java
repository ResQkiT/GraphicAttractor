package org.resk.system.colorsystem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorLoader {
    private static String path ="src/main/java/org/resk/patterns/rainbow.jpg" ;
    private static BufferedImage bi;

    public static void init(String path){
        ColorLoader.path = path;
        try{
            BufferedImage image = ImageIO.read(new File(path));
            bi = image;
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public static int getColorByCoord(double c){
        int r = Math.abs((int)c);
        r = r % bi.getWidth();
        return bi.getRGB(r, 0);
    }
}
