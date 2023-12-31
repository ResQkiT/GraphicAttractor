package org.resk.units.points;

import org.resk.system.Render;
import org.resk.system.colorsystem.ColorLoader;
import org.resk.system.properties.Properties;

import java.util.ArrayList;

public abstract class BasePointType implements PointType {
    private final Render render;
    protected ArrayList<ArrayList<Integer>> startedPoints = new ArrayList<>();
    private int centerX;
    private int centerY;
    private int centerZ;
    private final int vectorX = 1920 / 2;
    private final int vectorY = 1080 / 2;
    private final int vectorZ = 1920 / 2;
    private int dx = 0;
    private int dy = 0;
    private int dz = 0;
    protected Double scope;
    protected Properties properties;

    public BasePointType(Render render) {
        this.render = render;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
        updateProperties();
    }

    protected abstract void updateProperties();

    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        return null;
    }

    @Override
    public void draw(double x, double y, double z) {

        Integer X = (int) (x * scope);
        Integer Y = (int) (y * scope);
        Integer Z = (int) (z * scope);
        if (startedPoints.size() <= 1000) {

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

            this.centerX = sumX / startedPoints.size();
            this.centerY = sumY / startedPoints.size();
            this.centerZ = sumZ / startedPoints.size();

            this.dx = vectorX - centerX;
            this.dy = vectorY - centerY;
            this.dz = vectorZ - centerZ;

        } else {
            int color = ColorLoader.getColorByCoord(Z + this.dz);
            this.render.drawSquare(X + this.dx, Y + this.dy, 1, color);
        }
    }
}
