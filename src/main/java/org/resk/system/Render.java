package org.resk.system;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Render {
    private int width, height;
    private int pixels[];
    private int tileIndex;
    private static final int MAP_SIZE = 128;
    private static final int MAP_SIZE_MASK = MAP_SIZE - 1;
    private int tiles[] = new int[MAP_SIZE * MAP_SIZE];

    private static final Random random = new Random();
    public Render(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
        fill();
    }
    private void fill(){
        for (int i = 0; i< MAP_SIZE * MAP_SIZE  ; i++){
            tiles[i] = random.nextInt(0xffffff);
        }
    }
    public void clear(){
        for(int i = 0; i< pixels.length; i++){
            pixels[i] = 0;
        }
    }
    public void render(){
        for(int y = 0; y < height; y++ ){
            for(int x = 0; x< width; x++){

            }
        }
    }
    public void drawSquare(int x, int y, int size, int color){
        if( (x < 0 || y<0) || (x > width || y > height)){
            return;
        }
        for(int i_y = y; i_y < y+size; i_y++ ){
            for(int i_x = x; i_x < x+size; i_x++ ){
                pixels[x + (y*width)] = color;
            }
        }
    }
    public void makeScreenShot(){
        BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        for(int y = 0; y < height; y++ ){
            for(int x = 0; x< width; x++){
                bi.setRGB(x, y, pixels[x + (y*width)]);
            }
        }
        File out = new File("Screenshots/" + System.currentTimeMillis() + "screenshot.png");
        try {
            ImageIO.write(bi, "png", out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}