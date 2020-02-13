package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

public class Circle extends Component {

    private float left;
    private float top;
    private float radius;

    private String fillColor;
    private float opacity = 1;
    private String strokeColor;
    private float strokeWidth = 1;

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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
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

    private Coordinate pointCoordinate = new Coordinate();

    private double actualLeft(double onePartWidth) {
        return (left + radius) * onePartWidth;
    }

    private double actualTop(double onePartHeight) {
        return (top + radius) * onePartHeight;
    }

    @Override
    public Geometry getGeometry(double onePartWidth, double onePartHeight) {
        pointCoordinate.x = actualLeft(onePartWidth);
        pointCoordinate.y = actualTop(onePartHeight);

        Point point = gf.createPoint(pointCoordinate);
        Geometry circle = point.buffer(radius);
        return circle;
    }

    @Override
    public void draw(double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        Path path = getPath(onePartWidth, onePartHeight);
        if (!TextUtils.isEmpty(fillColor)) {
            paint.setColor(Color.parseColor(fillColor));
            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha((int) (255 * opacity));
            canvas.drawPath(path, paint);
        }
        if (!TextUtils.isEmpty(strokeColor)) {
            paint.setColor(Color.parseColor(strokeColor));
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAlpha(255);
            canvas.drawPath(path, paint);
        }
    }
}
