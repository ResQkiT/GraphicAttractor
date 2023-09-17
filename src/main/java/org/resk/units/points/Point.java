package org.resk.units.points;

public class Point {
    private final double x;
    private final double y;
    private final double z;
    private final PointType lightweight;

    public Point(double x, double y, double z, PointType lightweight) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.lightweight = lightweight;
    }

    public Point getNext(double deltaTime) {
        return this.lightweight.getNext(x, y, z, deltaTime);
    }

    public void draw() {
        this.lightweight.draw(x, y, z);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
