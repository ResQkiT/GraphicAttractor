package org.resk.units;

import org.resk.system.ColorLoader;
import org.resk.system.Render;

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
public class PolynomialAttractorPoint implements PointType, EnableToGetNextPoint {
    private Render render;
    private String pattern;
    private double scope = 500;
    private double[] odds = new double[12];

    public PolynomialAttractorPoint(Render render, String pattern) {
        this.pattern = pattern;
        this.render = render;
        initOdds(pattern);
    }
    private void initOdds(String pattern){
        char[] arr = pattern.toLowerCase().toCharArray();
        for (int i = 0; i<arr.length; i++) {
            int n = arr[i] - 'a';
            odds[i] = (-12 + n)/10.0;
        }
    }

    @Override
    public double getNextX(double x, double y, double z) {
        double nx = odds[0] + odds[1] * x + odds[2] * x*x + odds[3] * x * y + odds[4] * y + odds[5] * y *y;
        return nx;
    }

    @Override
    public double getNextY(double x, double y, double z) {
        double ny = odds[6] + odds[7] * x + odds[8] * x*x + odds[9] * x * y + odds[10] * y + odds[11] * y *y;
        return ny;
    }

    @Override
    public double getNextZ(double x, double y, double z) {
        return sqrt(x*x + y*y) ;
    }

    @Override
    public Point getNext(double x, double y, double z, double deltaTime) {
        return new Point(getNextX(x, y, z),
                getNextY(x, y, z),
                getNextZ(x, y, z),
                this);
    }

    @Override
    public void draw(double x, double y, double z) {
        int color = ColorLoader.getColorByCoord(z * this.scope * 1.5 );
        this.render.drawSquare((int)(x * this.scope), (int)(y * this.scope + 1000), 1, color);
    }
}
