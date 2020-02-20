package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import androidx.annotation.NonNull;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
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

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
//        if (left < 0) {
//            left = 0;
//        }
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
//        if (top < 0) {
//            top = 0;
//        }
        this.top = top;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isControls() {
        return controls;
    }

    public void setControls(boolean controls) {
        this.controls = controls;
    }

    public abstract Geometry getGeometry(float left, float top, double zoomLevel);

    protected float actualLeft(double zoomLevel) {
        return actualCoordinateValue(left, zoomLevel);
    }
    protected float actualTop(double zoomLevel) {
        return actualCoordinateValue(top, zoomLevel);
    }
    protected float actualCoordinateValue(float x, double zoomLevel) {
        return (float) (x * zoomLevel);
    }

    public Path getPath(float left, float top, double zoomLevel) {
        path.reset();
        Geometry geometry = getGeometry(left, top, zoomLevel);
        Coordinate[] coordinates = geometry.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            Coordinate coordinate = coordinates[i];
            if (i == 0) {
                path.moveTo((float) coordinate.x, (float) coordinate.y);
            }
            else {
                path.lineTo((float) coordinate.x, (float) coordinate.y);
            }
        }
        return path;
    }

    public abstract void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint);

    public final boolean isBounded(float zoomLevel, double x, double y) {
        Geometry geometry = getGeometry(left, top, zoomLevel);
        Point point = gf.createPoint(new Coordinate(x, y));
        return geometry.contains(point);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
