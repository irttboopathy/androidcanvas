package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.vividsolutions.jts.geom.Geometry;

public abstract class Component {

    public boolean hasControls() {
        return false;
    }

    public abstract Geometry getGeometry(double onePartWidth, double onePartHeight);

    public abstract Path getPath(double onePartWidth, double onePartHeight);

    public abstract void draw(double onePartWidth, double onePartHeight, Canvas canvas, Paint paint);

    public abstract boolean isBounded(double onePartWidth, double onePartHeight, float x, float y);
}
