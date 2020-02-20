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
    public void updatePath(Path path, double left, double top, double onePartWidth, double onePartHeight) {
        path.lineTo((float) ((x * onePartWidth) + left), (float) ((y * onePartHeight) + top));
    }

    @Override
    public PathPoint clonePathPoint() {
        return new LinePathPoint(this);
    }
}
