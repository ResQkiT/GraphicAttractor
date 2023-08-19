package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

import java.util.ArrayList;

public class RosselPoint extends CanDrawPointType implements EnableToGetDeltaPoint {
    private double a = 0.2;
    private double b = 0.2;
    private double c = 14;
    public RosselPoint(Render render) {
        super(render, 20);
    }

    @Override
    public double getDeltaX(double x, double y, double z) {
        return -y - z;
    }

    @Override
    public double getDeltaY(double x, double y, double z) {
        return x + this.a * y;
    }

    @Override
    public double getDeltaZ(double x, double y, double z) {
        return this.b + z * (x - this.c);
    }

    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        double dx = this.getDeltaX(x, y, z);
        double dy = this.getDeltaY(x, y, z);
        double dz = this.getDeltaZ(x, y, z);
        return new Point(x + dx * deltaTime,
                y + dy * deltaTime,
                z + dz * deltaTime,
                this);
    }
}
