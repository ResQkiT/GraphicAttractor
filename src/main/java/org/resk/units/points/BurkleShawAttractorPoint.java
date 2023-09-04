package org.resk.units.points;

import org.resk.system.Render;
import org.resk.units.Properties;

public class BurkleShawAttractorPoint extends BasePointType implements EnableToGetDeltaPoint {
    private Double s;
    private Double v;
    public BurkleShawAttractorPoint(Render render) {
        super(render, 200);
        properties = new Properties<Double>()
                .registerProperties("s", 10.0)
                .registerProperties("v", 4.272);
        updateProperties();
    }
    @Override
    protected void updateProperties() {
        s = Double.parseDouble(properties.getByName("s").toString());
        v = Double.parseDouble(properties.getByName("v").toString());
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
