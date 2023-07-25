package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

public class LorenzPoint implements PointType, EnableToGetDeltaPoint{
    private double b =8.0/3.0;
    private double s = 10.0;
    private double r = 28.0;

    private double scope = 20;
    private Render render;

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

    public void draw(double x, double y, double z) {
        int color = ColorLoader.getColorByCoord(z * this.scope * 1.5);
        this.render.drawSquare((int)(x * this.scope + 1000), (int)(y * this.scope + 500), 1, color);
    }


}
