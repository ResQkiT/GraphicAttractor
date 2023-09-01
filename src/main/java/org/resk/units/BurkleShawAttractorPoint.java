package org.resk.units;

import org.resk.system.Render;

public class BurkleShawAttractorPoint extends CanDrawPointType implements EnableToGetDeltaPoint{
    private double s = 10;
    private double v = 4.272;
    public BurkleShawAttractorPoint(Render render) {
        super(render, 200);
    }

    @Override
    public double getDeltaX(double x, double y, double z) {
        return -s*(x + y);
    }
    @Override
    public double getDeltaY(double x, double y, double z) {
        return -y - s * x * z;
    }

    @Override
    public double getDeltaZ(double x, double y, double z) {
        return s * x * y + v;
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
}
