package org.resk.units.points;

public interface PointType {
    public Point getNext(double x, double y, double z, double deltaTime);
    public void draw(double x, double y, double z);

}
