package org.resk.units.points;

import org.resk.system.Render;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;

import java.util.Random;

public class RRAttractorPoint extends BasePointType implements EnableToGetNextPoint{
    Random random = new Random();
    public RRAttractorPoint(Render render) {
        super(render);
        super.properties = new Properties()
                .registerProperties(new Property<Double>("scope", 1.0));
        updateProperties();
    }

    @Override
    protected void updateProperties() {
        scope = (Double) properties.getByName("scope").getValue();
    }

    @Override
    public double getNextX(double x, double y, double z) {
        return random.nextInt(1920);
    }

    @Override
    public double getNextY(double x, double y, double z) {
        return random.nextInt(1080);
    }

    @Override
    public double getNextZ(double x, double y, double z) {
        return random.nextInt(1920);
    }
    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        return new Point(getNextX(x, y, z),
                getNextY(x, y, z),
                getNextZ(x, y, z),
                this);
    }
}
