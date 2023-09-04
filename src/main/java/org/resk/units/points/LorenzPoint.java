package org.resk.units.points;

import org.resk.system.Render;
import org.resk.units.Properties;

import java.util.HashMap;


public class LorenzPoint extends BasePointType implements PointType, EnableToGetDeltaPoint {

    private Double b;
    private Double s;
    private Double r;
    public LorenzPoint(Render render) {
        super(render , 20);
        properties = new Properties<Double>()
                .registerProperties("b", 8.0/3.0)
                .registerProperties("s", 10.0)
                .registerProperties("r", 28.0);
        updateProperties();
    }
    @Override
    protected void updateProperties(){
        b = Double.parseDouble(properties.getByName("b").toString());
        s = Double.parseDouble(properties.getByName("s").toString());
        r = Double.parseDouble(properties.getByName("r").toString());
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
