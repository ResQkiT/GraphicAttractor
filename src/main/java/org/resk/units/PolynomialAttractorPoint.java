package org.resk.units;


import org.resk.system.ColorLoader;
import org.resk.system.Render;

import java.util.ArrayList;

import static java.lang.Math.sqrt;
/*
GLXOESFTTPSV
MCRBIPOPHTBN
VBWNBDELYHUL
FIRCDERRPVLD
QFFVSLMJJCCR
LUFBBFISGJYS
RALLTIOBDULT
 */
public class PolynomialAttractorPoint  implements PointType, EnableToGetNextPoint {
    private Render render;
    private String pattern;
    private double scope = 500;
    private double[] odds = new double[12];
    private ArrayList<ArrayList<Integer>> startedPoints = new ArrayList<>();
    private int centerX;
    private int centerY;
    private int centerZ;
    private final int vectorX = 1920 / 2;
    private final int vectorY = (int) (1080 / 2.5);
    private final int vectorZ = 1920 / 2;
    private int dx = 0;
    private int dy = 0;
    private int dz = 0;
    public PolynomialAttractorPoint(Render render, String pattern) {
        this.pattern = pattern;
        this.render = render;
        initOdds(pattern);
    }
    private void initOdds(String pattern){
        char[] arr = pattern.toLowerCase().toCharArray();
        for (int i = 0; i<arr.length; i++) {
            int n = arr[i] - 'a';
            odds[i] = (-12 + n)/10.0;
        }
    }

    @Override
    public double getNextX(double x, double y, double z) {
        double nx = odds[0] + odds[1] * x + odds[2] * x*x + odds[3] * x * y + odds[4] * y + odds[5] * y *y;
        return nx;
    }

    @Override
    public double getNextY(double x, double y, double z) {
        double ny = odds[6] + odds[7] * x + odds[8] * x*x + odds[9] * x * y + odds[10] * y + odds[11] * y *y;
        return ny;
    }

    @Override
    public double getNextZ(double x, double y, double z) {
        return sqrt(x*x + y*y) ;
    }

    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        return new Point(getNextX(x, y, z),
                getNextY(x, y, z),
                getNextZ(x, y, z),
                this);
    }

    @Override
    public void draw(double x, double y, double z) {

        Integer X = (int)(x * scope);
        Integer Y = (int)(y * scope);
        Integer Z = (int)(z * scope);
        if(startedPoints.size() <= 100){

            ArrayList<Integer> toAdd = new ArrayList<>();
            toAdd.add(X);
            toAdd.add(Y);
            toAdd.add(Z);
            startedPoints.add(toAdd);

            int sumX = 0;
            int sumY = 0;
            int sumZ = 0;

            for (ArrayList<Integer> ar : startedPoints) {
                sumX += ar.get(0);
                sumY += ar.get(1);
                sumZ += ar.get(2);
            }

            this.centerX =  sumX / startedPoints.size();
            this.centerY =  sumY / startedPoints.size();
            this.centerZ =  sumZ / startedPoints.size();

            this.dx = vectorX - centerX;
            this.dy = vectorY - centerY;
            this.dz = vectorZ - centerZ;

        }else{
            int color = ColorLoader.getColorByCoord(Z + this.dz);
            this.render.drawSquare(X + this.dx, Y + this.dy, 1, color);
        }


    }
}
