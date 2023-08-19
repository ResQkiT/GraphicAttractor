package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

import java.util.ArrayList;

import static java.lang.Math.*;

public class NAttractorPoint extends CanDrawPointType implements EnableToGetNextPoint {
    private double c0 = 1.6831349342542232;
    private double c1 = -2.9984035545418575;
    private double c2 = 2.1207267208634164;
    private double c3 = -2.121518002564899;

    public NAttractorPoint(Render render) {
        super(render, 125);
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
}
