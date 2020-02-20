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

    private float radius;

    private String fillColor;
    private float opacity = 1;
    private String strokeColor;
    private float strokeWidth = 1;

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

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    private Coordinate pointCoordinate = new Coordinate();

    protected float actualLeft(double zoomLevel) {
        return (float) ((left + radius) * zoomLevel);
    }

    protected float actualTop(double zoomLevel) {
        return (float) ((top + radius) * zoomLevel);
    }

    @Override
    public Geometry getGeometry(float left, float top, double zoomLevel) {
        pointCoordinate.x = left +  actualLeft(zoomLevel);
        pointCoordinate.y = top + actualTop(zoomLevel);

        Point point = gf.createPoint(pointCoordinate);
        Geometry circle = point.buffer(radius * zoomLevel);
        return circle;
    }

    @Override
    public void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint) {
        Path path = getPath(left, top, zoomLevel);
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
