package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

public class RosselPoint implements PointType, EnableToGetDeltaPoint {
    private double a = 0.2;
    private double b = 0.2;
    private double c = 14;
    private double scope = 20;
    private Render render;

    public RosselPoint(Render render) {
        this.render = render;
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

    @Override
    public void draw(double x, double y, double z) {
        int color = ColorLoader.getColorByCoord(z * this.scope * 0.9);
        this.render.drawSquare((int)(x * this.scope + 800), (int)(y * this.scope + 600), 2, color);
    }
}
