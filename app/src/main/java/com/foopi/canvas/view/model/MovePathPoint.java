package com.foopi.canvas.view.model;

import android.graphics.Path;

public class MovePathPoint extends PathPoint {

    public MovePathPoint(float x, float y) {
        super(x, y);
    }

    public MovePathPoint(MovePathPoint first) {
        this(first.x, first.y);
    }

    @Override
    public void updatePath(Path path, double left, double top, double zoomLevel) {
        path.moveTo((float) ((x * zoomLevel) + left), (float) ((y * zoomLevel) + top));
    }

    @Override
    public PathPoint clonePathPoint() {
        return new MovePathPoint(this);
    }
}
