package org.resk.units.points;


import org.resk.system.Render;
import org.resk.system.properties.Properties;
import org.resk.system.properties.Property;

import static java.lang.Math.sqrt;

/*
GLXOESFTTPSV
MCRBIPOPHTBN
VBWNBDELYHUL
FIRCDERRPVLD
QFFVSLMJJCCR
LUFBBFISGJYS
RALLTIOBDULT
 */
public class PolynomialAttractorPoint extends BasePointType implements EnableToGetNextPoint {
    private String pattern;
    private final double[] odds = new double[12];

    public PolynomialAttractorPoint(Render render, String pattern) {
        super(render);
        this.pattern = pattern;
        initOdds(pattern);
        properties = new Properties()
                .registerProperties(new Property<String>("pattern", pattern))
                .registerProperties(new Property<Double>("scope", 500.0));
        updateProperties();
    }

    @Override
    protected void updateProperties() {
        scope = (Double) properties.getByName("scope").getValue();
        pattern = (String) properties.getByName("pattern").getValue();
        if (pattern.length() != 12)
            return;
        clearCenterList();
        initOdds(pattern);
    }

    private void initOdds(String pattern) {
        char[] arr = pattern.toLowerCase().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i] - 'a';
            odds[i] = (-12 + n) / 10.0;
        }
    }

    public void clearCenterList() {
        super.startedPoints.clear();
    }

    @Override
    public double getNextX(double x, double y, double z) {
        double nx = odds[0] + odds[1] * x + odds[2] * x * x + odds[3] * x * y + odds[4] * y + odds[5] * y * y;
        return nx;
    }

    @Override
    public double getNextY(double x, double y, double z) {
        double ny = odds[6] + odds[7] * x + odds[8] * x * x + odds[9] * x * y + odds[10] * y + odds[11] * y * y;
        return ny;
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
