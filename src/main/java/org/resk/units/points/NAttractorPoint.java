package org.resk.units.points;

import org.resk.system.Render;
import org.resk.units.Properties;

import static java.lang.Math.*;

public class NAttractorPoint extends BasePointType implements EnableToGetNextPoint {
    private Double c0;
    private Double c1;
    private Double c2;
    private Double c3;
    public NAttractorPoint(Render render) {
        super(render, 125);
        properties = new Properties<Double>()
                .registerProperties("c0" ,1.6831349342542232 )
                .registerProperties("c1" ,-2.9984035545418575 )
                .registerProperties("c2" ,2.1207267208634164)
                .registerProperties("c3" ,-2.121518002564899 );
        updateProperties();
    }
    @Override
    protected void updateProperties() {
        c0 = Double.parseDouble(properties.getByName("c0").toString());
        c1 = Double.parseDouble(properties.getByName("c1").toString());
        c2 = Double.parseDouble(properties.getByName("c2").toString());
        c3 = Double.parseDouble(properties.getByName("c3").toString());
    }
    @Override
    public double getNextX(double x, double y, double z) {
        return c0 * sin(y - y*(y*y + 1) / 2) + c1 * tanh(x - x * (x*x+1) / 2);
    }
    @Override
    public double getNextY(double x, double y, double z) {
        return c2*sin(x - x*(x*x +1) / 2) + c3 / (cosh(y - y*(y*y+1) / 2));
    }
    @Override
    public double getNextZ(double x, double y, double z) {
        return sqrt(x*x + y*y);
    }
    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        return new Point(getNextX(x, y, z),
                getNextY(x, y, z),
                getNextZ(x, y, z),
                this);
    }
}
