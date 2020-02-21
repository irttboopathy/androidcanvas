package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

public abstract class Component implements Cloneable {

    protected GeometryFactory gf = new GeometryFactory();
    protected AffineTransformation at = new AffineTransformation();
    protected Path path = new Path();
    protected boolean controls = false;

    protected float left;
    protected float top;
    private boolean visible = true;

    protected String fillColor;
    protected String strokeColor;
    protected float strokeWidth = 2f;

    protected float opacity = 1f;
    protected double angle = 0;
    protected double scaleX = 1;
    protected double scaleY = 1;

    public final String getFillColor() {
        return fillColor;
    }

    public final void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public final String getStrokeColor() {
        return strokeColor;
    }

    public final void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public final float getStrokeWidth() {
        return strokeWidth;
    }

    public final void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public final float getOpacity() {
        return opacity;
    }

    public final void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public final double getAngle() {
        return angle;
    }

    public final void setAngle(double angle) {
        this.angle = angle;
    }

    public final double getScaleX() {
        return scaleX;
    }

    public final void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public final double getScaleY() {
        return scaleY;
    }

    public final void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public final float getLeft() {
        return left;
    }

    public final void setLeft(float left) {
        this.left = left;
    }

    public final float getTop() {
        return top;
    }

    public final void setTop(float top) {
        this.top = top;
    }

    public final boolean isVisible() {
        return visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isControls() {
        return controls;
    }

    public void setControls(boolean controls) {
        this.controls = controls;
    }

    public abstract Geometry getGeometry(float left, float top, double zoomLevel);

    public final Geometry createGeometry(float left, float top, double zoomLevel) {
        Geometry geometry = getGeometry(left, top, zoomLevel);
        if (geometry == null) {
            throw new IllegalArgumentException("Geometry cannot be empty");
        }

        AffineTransformation at = new AffineTransformation();

        Geometry boundary = geometry.getBoundary();
        at.setToRotation(angle * Math.PI / 180, left + actualLeft(zoomLevel), top + actualTop(zoomLevel));
//        at.setToRotation(angle * Math.PI / 180, centroid.getX(), centroid.getY());
        at.translate(-actualLeft(zoomLevel), -actualTop(zoomLevel));
        at.scale(scaleX, scaleY);
        at.translate(actualLeft(zoomLevel), actualTop(zoomLevel));

        Geometry transform = at.transform(geometry);
        return transform;
    }

    protected final float actualLeft(double zoomLevel) {
        return actualCoordinateValue(left, zoomLevel);
    }
    protected final float actualTop(double zoomLevel) {
        return actualCoordinateValue(top, zoomLevel);
    }
    protected final float actualCoordinateValue(float x, double zoomLevel) {
        return (float) (x * zoomLevel);
    }

    private int getValidColor(String color) {
        if (TextUtils.isEmpty(color)) {
            return -1;
        }

        if (color.startsWith("#")) {
            return Color.parseColor(color);
        }

        if (color.toLowerCase().startsWith("rgba")) {
            String substring = color.substring(5, color.length() - 1);
            String[] split = substring.split(",");
            if (split.length == 4) {
                setOpacity(Float.parseFloat(split[3].trim()));
                return Color.argb(1, Integer.parseInt(split[0].trim()),
                        Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()));
            }
        }

        if (color.toLowerCase().startsWith("rgb")) {
            String substring = color.substring(4, color.length() - 1);
            String[] split = substring.split(",");
            if (split.length == 3) {
                return Color.argb(1, Integer.parseInt(split[0].trim()),
                        Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()));
            }
        }

        return -1;
    }

    public Path getPath(float left, float top, double zoomLevel) {
        path.reset();
        Geometry geometry = createGeometry(left, top, zoomLevel);
        if (geometry instanceof GeometryCollection) {
            GeometryCollection collection = (GeometryCollection) geometry;
            for (int i = 0; i < collection.getNumGeometries(); i++) {
                Geometry geometryN = collection.getGeometryN(i);
                updatePath(geometryN);
            }
        }
        else {
            updatePath(geometry);
        }
        return path;
    }

    private void updatePath(Geometry geometry) {
        Coordinate[] coordinates = geometry.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            Coordinate coordinate = coordinates[i];
            if (i == 0) {
                path.moveTo((float) coordinate.x, (float) coordinate.y);
            } else {
                path.lineTo((float) coordinate.x, (float) coordinate.y);
            }
        }
    }

    public void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint) {
        Path path = getPath(left, top, zoomLevel);

        if (supportsFill() && !TextUtils.isEmpty(getFillColor())) {
            paint.setColor(getValidColor(getFillColor()));

            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha((int) (255 * opacity));
            canvas.drawPath(path, paint);
        }
        if (supportsStroke() && !TextUtils.isEmpty(strokeColor)) {
            paint.setColor(getValidColor(strokeColor));
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAlpha(255);
            canvas.drawPath(path, paint);
        }
    }

    public boolean supportsFill() {
        return true;
    }

    public boolean supportsStroke() {
        return true;
    }

    public final boolean isBounded(float zoomLevel, double x, double y) {
        Geometry geometry = createGeometry(left, top, zoomLevel);
        Point point = gf.createPoint(new Coordinate(x, y));
        return geometry.contains(point);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
