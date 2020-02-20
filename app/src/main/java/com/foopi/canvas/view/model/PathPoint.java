package com.foopi.canvas.view.model;

import android.graphics.Path;

public abstract class PathPoint extends Point {

    public PathPoint(float x, float y) {
        super(x, y);
    }

    public abstract void updatePath(Path path, double left, double top, double onePartWidth, double onePartHeight);

    public abstract PathPoint clonePathPoint();

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
}
