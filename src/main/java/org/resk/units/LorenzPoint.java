package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

import java.util.ArrayList;

public class LorenzPoint implements PointType, EnableToGetDeltaPoint{
    private double b =8.0/3.0;
    private double s = 10.0;
    private double r = 28.0;

    private double scope = 20;
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

    public LorenzPoint(Render render) {
        this.render = render;
    }
    public double getDeltaX(double x, double y, double z) {
        return -this.s * x + this.s * y;
    }

    public double getDeltaY(double x, double y, double z) {
        return -x * z + this.r * x - y;
    }

    public double getDeltaZ(double x, double y, double z) {
        return x * y - this.b * z;
    }
    public Point getNext(double x, double y, double z, double deltaTime) {
        double dx = this.getDeltaX(x, y, z);
        double dy = this.getDeltaY(x, y, z);
        double dz = this.getDeltaZ(x, y, z);
        return new Point(x + dx * deltaTime,
                y + dy * deltaTime,
                z + dz * deltaTime,
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
