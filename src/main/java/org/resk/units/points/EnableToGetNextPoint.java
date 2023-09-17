package org.resk.units.points;

public interface EnableToGetNextPoint {
    double getNextX(double x, double y, double z);

    double getNextY(double x, double y, double z);

    double getNextZ(double x, double y, double z);
}
