package org.resk.units.points;

import org.resk.system.Render;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;


public class LorenzPoint extends BasePointType implements PointType, EnableToGetDeltaPoint {

    private Double b;
    private Double s;
    private Double r;
    public LorenzPoint(Render render) {
        super(render);
        properties = new Properties()
                .registerProperties(new Property<Double>("b", 8.0/3.0))
                .registerProperties(new Property<Double>("s", 10.0))
                .registerProperties(new Property<Double>("r", 28.0))
                .registerProperties(new Property<Double>("scope", 20.0));
        updateProperties();
    }
    @Override
    protected void updateProperties(){
        b = (Double) properties.getByName("b").getValue();
        s = (Double) properties.getByName("s").getValue();
        r = (Double) properties.getByName("r").getValue();
        scope = (Double) properties.getByName("scope").getValue();
    }

    public double getDeltaX(double x, double y, double z) {
        return -s * x + s * y;
    }

    public double getDeltaY(double x, double y, double z) {
        return -x * z + r * x - y;
    }

    public double getDeltaZ(double x, double y, double z) {
        return x * y - b * z;
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
