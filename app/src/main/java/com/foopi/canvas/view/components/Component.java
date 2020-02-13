package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.util.AffineTransformation;

public abstract class Component {

    protected GeometryFactory gf = new GeometryFactory();
    protected AffineTransformation at = new AffineTransformation();
    protected Path path = new Path();
    protected boolean controls = false;

    private boolean visible = true;

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

    public abstract Geometry getGeometry(double onePartWidth, double onePartHeight);

    public Path getPath(double onePartWidth, double onePartHeight) {
        path.reset();
        Geometry geometry = getGeometry(onePartWidth, onePartHeight);
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

    public abstract void draw(double onePartWidth, double onePartHeight, Canvas canvas, Paint paint);

    public final boolean isBounded(double onePartWidth, double onePartHeight, float x, float y) {
        Geometry geometry = getGeometry(onePartWidth, onePartHeight);
        Point point = gf.createPoint(new Coordinate(x, y));
        return geometry.contains(point);
    }
}
