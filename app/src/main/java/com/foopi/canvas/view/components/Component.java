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
import com.vividsolutions.jts.geom.util.AffineTransformation;

import java.util.HashMap;

public abstract class Component implements Cloneable {

    protected GeometryFactory gf = new GeometryFactory();
    private AffineTransformation at = new AffineTransformation();
    protected Path path = new Path();
    protected boolean controls = false;

    protected float left;
    protected float top;
    private boolean visible = true;

    private Property property = new Property();

    protected double angle = 0;
    protected double scaleX = 1;
    protected double scaleY = 1;

    public Property getProperty() {
        return property;
    }

    public final String getFillColor() {
        return property.getFillColor();
    }

    public final void setFillColor(String fillColor) {
        this.property.setFillColor(fillColor);
    }

    public final String getStrokeColor() {
        return property.getStrokeColor();
    }

    public final void setStrokeColor(String strokeColor) {
        this.property.setStrokeColor(strokeColor);
    }

    public final float getStrokeWidth() {
        return property.getStrokeWidth();
    }

    public final void setStrokeWidth(float strokeWidth) {
        this.property.setStrokeWidth(strokeWidth);
    }

    public final float getOpacity() {
        return property.getOpacity();
    }

    public final void setOpacity(float opacity) {
        this.property.setOpacity(opacity);
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

//    public abstract Geometry getGeometry(float left, float top, double zoomLevel);

    public abstract GeomProperty getGeomProperty(float left, float top, double zoomLevel);

    public final GeomProperty createGeometry(float left, float top, double zoomLevel) {
        GeomProperty geometryProperty = getGeomProperty(left, top, zoomLevel);
        if (geometryProperty == null) {
            throw new IllegalArgumentException("Geometry cannot be empty");
        }

        AffineTransformation at = new AffineTransformation();

        at.setToRotation(angle * Math.PI / 180, left + actualLeft(zoomLevel), top + actualTop(zoomLevel));
        at.translate(-actualLeft(zoomLevel), -actualTop(zoomLevel));
        at.scale(scaleX, scaleY);
        at.translate(actualLeft(zoomLevel), actualTop(zoomLevel));

//        GeomProperty transformedGeometries;
//        for (GeomProperty geometry : geometryProperty) {
            Geometry transform = at.transform(geometryProperty.getGeometry());
            GeomProperty clone = geometryProperty.clone();
            clone.setGeometry(transform);
//            transformedGeometries.add(clone);
//            transformedGeometries.add(transform);

            if (clone instanceof GeomCollProperty) {
                ((GeomCollProperty) clone).updateGeometryCollection((GeometryCollection) clone.getGeometry());
            }
//        }
        return clone;
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

    private Path getPath(Geometry geometry) {
        Path path = new Path();
        if (geometry instanceof GeometryCollection) {
            GeometryCollection collection = (GeometryCollection) geometry;
            for (int i = 0; i < collection.getNumGeometries(); i++) {
                Geometry geometryN = collection.getGeometryN(i);
                updatePath(geometryN, path);
            }
        }
        else {
            updatePath(geometry, path);
        }
        return path;
    }

    public Path getPath(float left, float top, double zoomLevel) {
        path.reset();
        GeomProperty geomProperty = createGeometry(left, top, zoomLevel);
        if (geomProperty == null) {
            return path;
        }
        if (geomProperty instanceof GeomCollProperty) {
            GeometryCollection collection = (GeometryCollection) geomProperty.getGeometry();
            for (int i = 0; i < collection.getNumGeometries(); i++) {
                Geometry geometryN = collection.getGeometryN(i);
                updatePath(geometryN, this.path);
            }
        }
        else {
            updatePath(geomProperty.getGeometry(), this.path);
        }
        return path;
    }

    private void updatePath(Geometry geometry, Path path) {
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
//        Path path = getPath(left, top, zoomLevel);
        GeomProperty geometryProperty = createGeometry(left, top, zoomLevel);
//        for (GeomProperty geometryProperty : geometries) {
            Path path = null;
            if (geometryProperty instanceof GeomCollProperty) {
                GeomCollProperty geomCollProperty = (GeomCollProperty) geometryProperty;
                GeometryCollection collection = (GeometryCollection) geometryProperty.getGeometry();
                for (int i = 0; i < collection.getNumGeometries(); i++) {
                    Geometry geometryN = collection.getGeometryN(i);
                    path = getPath(geometryN);
                    drawPathCanvas(path, canvas, paint, geomCollProperty.getGeoProperty(geometryN).fillColor);
                }
            }
            else {
                path = getPath(geometryProperty.getGeometry());
                drawPathCanvas(path, canvas, paint, geometryProperty.fillColor);
            }
//        }
    }

    private void drawPathCanvas(Path path, Canvas canvas, Paint paint, Property property) {
        if (supportsFill() && !TextUtils.isEmpty(property.getFillColor())) {
            paint.setColor(getValidColor(property.getFillColor()));

            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha((int) (255 * property.getOpacity()));
            canvas.drawPath(path, paint);
        }
        if (supportsStroke() && !TextUtils.isEmpty(property.getStrokeColor())) {
            paint.setColor(getValidColor(property.getStrokeColor()));
            paint.setStrokeWidth(property.getStrokeWidth());
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
//        Geometry geometry = createGeometry(left, top, zoomLevel);
//        Point point = gf.createPoint(new Coordinate(x, y));
//        return geometry.contains(point);
        return false;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static class GeomCollProperty extends GeomProperty {
//        GeometryCollection geometryCollection;
        GeomProperty geomProperties[];

        private HashMap<Geometry, GeomProperty> propertyMap = new HashMap<>();

        public GeomCollProperty(GeometryCollection geometryCollection, GeomProperty[] geomProperties) {
            super(geometryCollection);
//            this.geometryCollection = geometryCollection;
            this.geomProperties = geomProperties;

            if (geometryCollection.getNumGeometries() != geomProperties.length) {
                throw new IllegalArgumentException("Mismatched GeomCollProperty");
            }
        }

        public GeomCollProperty(GeomCollProperty geomCollProperty) {
            this((GeometryCollection) geomCollProperty.getGeometry(), geomCollProperty.geomProperties);
        }

        public void updateGeometryCollection(GeometryCollection geometryCollection) {
            if (geometryCollection.getNumGeometries() != geomProperties.length) {
                throw new IllegalArgumentException("Mismatched GeomCollProperty");
            }

            setGeometry(geometryCollection);
            for (int i = 0; i < geomProperties.length; i++) {
                GeomProperty geomProperty = geomProperties[i];
                geomProperty.geometry = geometryCollection.getGeometryN(i);
            }
            this.propertyMap.clear();
        }

        public GeomProperty getGeoProperty(Geometry geometry) {
            if (propertyMap.isEmpty()) {
                for (int i = 0; i < geomProperties.length; i++) {
                    GeomProperty geomProperty = geomProperties[i];
                    propertyMap.put(geomProperty.geometry, geomProperty);
                }
            }
            return propertyMap.get(geometry);
        }

        @Override
        public GeomCollProperty clone() {
            return new GeomCollProperty(this);
        }
    }

    public static class GeomProperty {
        protected Geometry geometry;
        protected Property fillColor;

        public GeomProperty(Geometry geometry) {
            this.geometry = geometry;
        }

        public GeomProperty(Geometry geometry, Property fillColor) {
            this(geometry);
            this.fillColor = fillColor;
        }

        public GeomProperty(GeomProperty geomProperty) {
            this(geomProperty.geometry, geomProperty.fillColor);
        }

        public GeomProperty clone() {
            return new GeomProperty(this);
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }
    }
}
