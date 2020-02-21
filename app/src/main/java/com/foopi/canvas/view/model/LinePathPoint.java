package com.foopi.canvas.view.model;

import android.graphics.Path;

public class LinePathPoint extends PathPoint {

    public LinePathPoint(float x, float y) {
        super(x, y);
    }

    public LinePathPoint(LinePathPoint first) {
        this(first.x, first.y);
    }

    @Override
    public void updatePath(Path path, double left, double top, double zoomLevel) {
        path.lineTo((float) ((x * zoomLevel) + left), (float) ((y * zoomLevel) + top));
    }

    @Override
    public PathPoint clonePoint() {
        return new LinePathPoint(this);
    }
}
