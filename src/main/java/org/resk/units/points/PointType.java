package org.resk.units.points;

public interface PointType {
    Point getNext(double x, double y, double z, double deltaTime);

    void draw(double x, double y, double z);

}
