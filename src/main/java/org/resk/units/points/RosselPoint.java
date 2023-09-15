package org.resk.units.points;

import org.resk.system.Render;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;

public class RosselPoint extends BasePointType implements EnableToGetDeltaPoint {
    private Double a;
    private Double b;
    private Double c;
    public RosselPoint(Render render) {
        super(render);
        properties = new Properties()
                .registerProperties(new Property<Double>("a", 0.2))
                .registerProperties(new Property<Double>("b", 0.2))
                .registerProperties(new Property<Double>("c", 14.0))
                .registerProperties(new Property<Double>("scope", 20.0));
        updateProperties();
    }
    @Override
    protected void updateProperties() {
        a = (Double) properties.getByName("a").getValue();
        b = (Double) properties.getByName("b").getValue();
        c = (Double) properties.getByName("c").getValue();
        scope = (Double) properties.getByName("scope").getValue();
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
