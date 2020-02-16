package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;

import com.vividsolutions.jts.geom.Geometry;

public class Ellipse extends Component {

    private float width;
    private float height;
    private float rx;
    private float ry;

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;
    private float opacity = 1;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getRx() {
        return rx;
    }

    public void setRx(float rx) {
        this.rx = rx;
    }

    public float getRy() {
        return ry;
    }

    public void setRy(float ry) {
        this.ry = ry;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        if (fillColor.startsWith("rgba")) {
//            Color.ar
            this.fillColor = "#ff0000";
        }
        this.fillColor = fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    @Override
    public Geometry getGeometry(float left, float top, double onePartWidth, double onePartHeight) {
        return null;
    }

    @Override
    public void draw(float left, float top, double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        if (!TextUtils.isEmpty(fillColor)) {
            try {
                paint.setColor(Color.parseColor(fillColor));
            } catch (Exception e) {
                paint.setColor(Color.RED);
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha((int) (255 * opacity));
            canvas.drawRoundRect(actualLeft(onePartWidth), actualTop(onePartWidth),
                    actualX(this.left + width, onePartWidth), actualY(this.top + height, onePartHeight),
                    actualX(rx, onePartWidth), actualY(ry, onePartHeight), paint);
        }
    }
}
