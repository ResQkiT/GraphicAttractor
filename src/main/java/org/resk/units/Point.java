package org.resk.units;

public class Point {
    private double x;
    private double y;
    private double z;
    private PointType lightweight;

    public Point(double x, double y, double z, PointType lightweight) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.lightweight = lightweight;
    }
    public Point getNext(double deltaTime){
        return this.lightweight.getNext(x, y, z, deltaTime);
    }
    public void draw(){
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
