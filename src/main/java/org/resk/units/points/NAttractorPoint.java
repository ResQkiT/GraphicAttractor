package org.resk.units.points;

import org.resk.system.Render;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;

import static java.lang.Math.*;

public class NAttractorPoint extends BasePointType implements EnableToGetNextPoint {
    private Double c0;
    private Double c1;
    private Double c2;
    private Double c3;

    public NAttractorPoint(Render render) {
        super(render);
        properties = new Properties()
                .registerProperties(new Property<Double>("c0", 1.6831349342542232))
                .registerProperties(new Property<Double>("c1", -2.9984035545418575))
                .registerProperties(new Property<Double>("c2", 2.1207267208634164))
                .registerProperties(new Property<Double>("c3", -2.121518002564899))
                .registerProperties(new Property<Double>("scope", 125.0));
        updateProperties();
    }

    @Override
    protected void updateProperties() {
        c0 = (Double) properties.getByName("c0").getValue();
        c1 = (Double) properties.getByName("c1").getValue();
        c2 = (Double) properties.getByName("c2").getValue();
        c3 = (Double) properties.getByName("c3").getValue();
        scope = (Double) properties.getByName("scope").getValue();
    }

    @Override
    public double getNextX(double x, double y, double z) {
        return c0 * sin(y - y * (y * y + 1) / 2) + c1 * tanh(x - x * (x * x + 1) / 2);
    }

    @Override
    public double getNextY(double x, double y, double z) {
        return c2 * sin(x - x * (x * x + 1) / 2) + c3 / (cosh(y - y * (y * y + 1) / 2));
    }

    @Override
    public double getNextZ(double x, double y, double z) {
        return sqrt(x * x + y * y);
    }

    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        return new Point(getNextX(x, y, z),
                getNextY(x, y, z),
                getNextZ(x, y, z),
                this);
    }
}
