package org.resk.units.points;

import org.resk.system.Render;
import org.resk.units.Properties;

public class DenTsucsAttractorPoint extends BasePointType implements EnableToGetDeltaPoint {
    private double a;
    private double c ;
    private double d ;
    private  double e ;
    private double f ;

    public DenTsucsAttractorPoint(Render render) {
        super(render);
        properties = new Properties<Double>()
                .registerProperties("a" , 40.0)
                .registerProperties("c", 0.833)
                .registerProperties("d", 0.5)
                .registerProperties("e", 0.65)
                .registerProperties("f", 20.0)
                .registerProperties("scope", 20.0);
        updateProperties();
    }

    @Override
    protected void updateProperties() {
        a = Double.parseDouble(properties.getByName("a").toString());
        c = Double.parseDouble(properties.getByName("c").toString());
        d = Double.parseDouble(properties.getByName("d").toString());
        e = Double.parseDouble(properties.getByName("e").toString());
        f = Double.parseDouble(properties.getByName("f").toString());
        scope = Double.parseDouble(properties.getByName("scope").toString());
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
