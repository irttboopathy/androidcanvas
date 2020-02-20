package com.foopi.canvas.view.model;

import android.graphics.Path;

public class ClosePathPoint extends PathPoint {

    public ClosePathPoint() {
        super(0, 0);
    }

    public ClosePathPoint(ClosePathPoint pathPoint) {
        this();
    }

    @Override
    public void updatePath(Path path, double left, double top, double onePartWidth, double onePartHeight) {
        path.close();
    }

    @Override
    public PathPoint clonePathPoint() {
        return new ClosePathPoint();
    }

    @Override
    public void getMin(Point point) {}

    @Override
    public void adjustPoints(Point point) {}
}
