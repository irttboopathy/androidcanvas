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

    public Coordinate getCoordinate(double left, double top) {
        return getCoordinate(null, left, top);
    }

    public Coordinate getCoordinate(Coordinate coordinate, double left, double top) {
        if (coordinate == null) {
            coordinate = new Coordinate();
        }
        coordinate.x = x + left;
        coordinate.y = y + top;
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
}