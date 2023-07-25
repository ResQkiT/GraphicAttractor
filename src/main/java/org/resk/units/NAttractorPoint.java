package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

import static java.lang.Math.*;

public class NAttractorPoint implements PointType, EnableToGetNextPoint {
    private double c0 =1.6831349342542232;
    private double c1 =-2.9984035545418575;
    private double c2 =2.1207267208634164;
    private double c3 =-2.121518002564899;

    private double scope = 125;
    private Render render;

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
        int color = ColorLoader.getColorByCoord(z * this.scope * 1.5 );
        this.render.drawSquare((int)(x * this.scope + 1000), (int)(y * this.scope + 700), 1, color);
    }
}
