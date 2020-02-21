package com.foopi.canvas.view.model;

import androidx.annotation.Nullable;

import com.vividsolutions.jts.geom.Coordinate;

public class Point {
    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point first) {
        this(first.x, first.y);
    }

    public Coordinate getCoordinate() {
        return new Coordinate();
    }

    public Coordinate getCoordinate(double left, double top, double zoomLevel) {
        return getCoordinate(null, left, top, zoomLevel);
    }

    public Coordinate getCoordinate(Coordinate coordinate, double left, double top, double zoomLevel) {
        if (coordinate == null) {
            coordinate = new Coordinate();
        }
        coordinate.x = (x * zoomLevel) + left;
        coordinate.y = (y * zoomLevel) + top;
        return coordinate;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean equals = super.equals(obj);
        if (!equals) {
            if (obj instanceof Point) {
                Point last = (Point) obj;
                if (x == last.x && y == last.y) {
                    equals = true;
                }
            }
        }
        return equals;
    }

    public void getMin(Point point) {
        if (x < point.x) {
            point.x = x;
        }
        if (y < point.y) {
            point.y = y;
        }
    }

    public void adjustPoints(Point point) {
        x -= point.x;
        y -= point.y;
    }

    public Point clonePoint() {
        return new Point(this);
    }
}