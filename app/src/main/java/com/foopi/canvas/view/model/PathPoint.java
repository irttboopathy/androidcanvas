package com.foopi.canvas.view.model;

import android.graphics.Path;

public abstract class PathPoint extends Point {

    public PathPoint(float x, float y) {
        super(x, y);
    }

    public abstract void updatePath(Path path, double left, double top, double zoomLevel);

    public abstract Point clonePoint();
}
