package com.foopi.canvas.view.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.vividsolutions.jts.geom.Geometry;

public class Image extends Component {

    private Bitmap bitmap;
    private float scaleX;
    private float scaleY;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    @Override
    public Geometry getGeometry(float left, float top, double onePartWidth, double onePartHeight) {
        return null;
    }

    @Override
    public void draw(float left, float top, double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY, actualLeft(onePartWidth), actualTop(onePartHeight));
        canvas.drawBitmap(bitmap, matrix, paint);
    }
}
