package org.resk.units;

public interface PointType {
    public Point getNext(double x, double y, double z, double deltaTime);
    public void draw(double x, double y, double z);

}
