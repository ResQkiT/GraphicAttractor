package org.resk.units;

import org.resk.system.Render;

public class DenTsucsAttractorPoint extends CanDrawPointType implements EnableToGetDeltaPoint{
    private double a = 40;
    private double c = 0.833;
    private double d = 0.5;
    private  double e = 0.65;
    private double f = 20;

    public DenTsucsAttractorPoint(Render render) {
        super(render, 20);
    }

    @Override
    public double getDeltaX(double x, double y, double z) {
        return a * (y - x) + d * x * z;
    }

    @Override
    public double getDeltaY(double x, double y, double z) {
        return f * y - x * z;
    }

    @Override
    public double getDeltaZ(double x, double y, double z) {
        return c * z + x*y - e* x * x;
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
