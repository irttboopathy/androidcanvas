package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Component {

    public boolean hasControls() {
        return false;
    }

    public abstract void draw(double onePartWidth, double onePartHeight, Canvas canvas, Paint paint);

    public abstract boolean isBounded(double onePartWidth, double onePartHeight, float x, float y);
}
