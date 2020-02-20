package com.foopi.canvas.view.model;

import android.graphics.Path;

public class CubicPathPoint extends PathPoint {

    public float x1, y1, x2, y2;

    public CubicPathPoint(float x1, float y1, float x2, float y2, float x, float y) {
        super(x, y);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public CubicPathPoint(CubicPathPoint first) {
        this(first.x1, first.y1, first.x2, first.y2, first.x, first.y);
    }

    @Override
    public void updatePath(Path path, double left, double top, double onePartWidth, double onePartHeight) {
        path.cubicTo((float) ((x1 * onePartWidth) + left), (float) ((y1 * onePartHeight) + top),
                (float) ((x2 * onePartWidth) + left), (float) ((y2 * onePartHeight) + top),
                (float) ((x * onePartWidth) + left), (float) ((y * onePartHeight) + top));
    }

    @Override
    public PathPoint clonePathPoint() {
        return new CubicPathPoint(this);
    }

    @Override
    public void getMin(Point point) {
        super.getMin(point);

        if (x1 < point.x) {
            point.x = x;
        }
        if (y1 < point.y) {
            point.y = y;
        }

        if (x2 < point.x) {
            point.x = x;
        }
        if (y2 < point.y) {
            point.y = y;
        }
    }

    @Override
    public void adjustPoints(Point point) {
        super.adjustPoints(point);

        x1 -= point.x;
        y1 -= point.y;
        x2 -= point.x;
        y2 -= point.y;
    }
}
