package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

import java.util.ArrayList;

import static java.lang.Math.*;

public class NAttractorPoint implements PointType, EnableToGetNextPoint {
    private double c0 = 1.6831349342542232;
    private double c1 = -2.9984035545418575;
    private double c2 = 2.1207267208634164;
    private double c3 = -2.121518002564899;

    private double scope = 125;
    private Render render;
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
    public NAttractorPoint(Render render) {
        this.render = render;
    }
    @Override
    public double getNextX(double x, double y, double z) {
        return c0 * sin(y - y*(y*y + 1) / 2) + c1 * tanh(x - x * (x*x+1) / 2);
    }
    @Override
    public double getNextY(double x, double y, double z) {
        return c2*sin(x - x*(x*x +1) / 2) + c3 / (cosh(y - y*(y*y+1) / 2));
    }
    @Override
    public double getNextZ(double x, double y, double z) {
        return sqrt(x*x + y*y);
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
