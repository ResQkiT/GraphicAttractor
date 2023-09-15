package org.resk.units.points;

import org.resk.system.Render;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;

public class DenTsucsAttractorPoint extends BasePointType implements EnableToGetDeltaPoint {
    private double a;
    private double c ;
    private double d ;
    private  double e ;
    private double f ;

    public DenTsucsAttractorPoint(Render render) {
        super(render);
        properties = new Properties()
                .registerProperties(new Property<Double>("a" , 40.0))
                .registerProperties(new Property<Double>("c", 0.833))
                .registerProperties(new Property<Double>("d", 0.5))
                .registerProperties(new Property<Double>("e", 0.65))
                .registerProperties(new Property<Double>("f", 20.0))
                .registerProperties(new Property<Double>("scope", 20.0));
        updateProperties();
    }

    @Override
    protected void updateProperties() {
        a = (Double) properties.getByName("a").getValue();
        c = (Double) properties.getByName("c").getValue();
        d = (Double) properties.getByName("d").getValue();
        e = (Double) properties.getByName("e").getValue();
        f = (Double) properties.getByName("f").getValue();
        scope = (Double) properties.getByName("scope").getValue();
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
