package com.foopi.canvas.view.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.vividsolutions.jts.geom.Geometry;

public class Image extends Component {

    private float left;
    private float top;
    private Bitmap bitmap;
    private float scaleX;
    private float scaleY;

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

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

    private float actualLeft(double onePartWidth) {
        return (float) (left * onePartWidth);
    }
    private float actualTop(double onePartHeight) {
        return (float) (top * onePartHeight);
    }

    @Override
    public Geometry getGeometry(double onePartWidth, double onePartHeight) {
        return null;
    }

    @Override
    public void draw(double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        Matrix matrix = new Matrix();
        matrix.setScale(scaleX, scaleY, actualLeft(onePartWidth), actualTop(onePartHeight));
        canvas.drawBitmap(bitmap, matrix, paint);
    }
}
