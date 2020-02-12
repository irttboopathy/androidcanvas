package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.util.AffineTransformation;

import org.json.JSONException;
import org.json.JSONObject;

public class Rectangle extends Component {

    GeometryFactory gf = new GeometryFactory();
    AffineTransformation at = new AffineTransformation();
    Path path = new Path();

    private float top;
    private float left;
    private float width;
    private float height;

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;

    private boolean hasControls = false;
    private float opacity = 1f;
    private double angle = 0;

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

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

    public boolean isHasControls() {
        return hasControls;
    }

    public void setHasControls(boolean hasControls) {
        this.hasControls = hasControls;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Rectangle() {
    }

    public Rectangle(JSONObject param) throws JSONException {
        top = (float) (param.getDouble("top"));
        left = (float) (param.getDouble("left"));

        width = (float) (param.getDouble("width"));
        height = (float) (param.getDouble("height"));

        fillColor = param.optString("fill");
        strokeColor = param.optString("stroke");
        strokeWidth = (float) param.optDouble("strokeWidth", 1f);

        hasControls = param.optBoolean("hasControls", false);
    }

    private float actualLeft(double onePartWidth) {
        return (float) (left * onePartWidth);
    }
    private float actualTop(double onePartHeight) {
        return (float) (top * onePartHeight);
    }
    private float actualRight(double onePartWidth) {
        return (float) ((left + width) * onePartWidth);
    }
    private float actualBottom(double onePartHeight) {
        return (float) ((top + height) * onePartHeight);
    }

    @Override
    public boolean hasControls() {
        return hasControls;
    }

    private Coordinate topLeftCoordinate = new Coordinate();
    private Coordinate topRightCoordinate = new Coordinate();
    private Coordinate bottomLeftCoordinate = new Coordinate();
    private Coordinate bottomRightCoordinate = new Coordinate();
    private Coordinate[] coordinates = {topLeftCoordinate, topRightCoordinate, bottomRightCoordinate, bottomLeftCoordinate, topLeftCoordinate};

    @Override
    public Geometry getGeometry(double onePartWidth, double onePartHeight) {
        topLeftCoordinate.x = actualLeft(onePartWidth);
        topLeftCoordinate.y = actualTop(onePartHeight);

        topRightCoordinate.x = actualRight(onePartWidth);
        topRightCoordinate.y = actualTop(onePartHeight);

        bottomLeftCoordinate.x = actualLeft(onePartWidth);
        bottomLeftCoordinate.y = actualBottom(onePartHeight);

        bottomRightCoordinate.x = actualRight(onePartWidth);
        bottomRightCoordinate.y = actualBottom(onePartHeight);

        Polygon polygon = gf.createPolygon(coordinates);

        if (angle != 0) {
            at.setToRotation(angle * Math.PI / 180, actualLeft(onePartWidth), actualTop(onePartHeight));
            polygon = (Polygon) at.transform(polygon);
        }
        return polygon;
    }

    @Override
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

    @Override
    public boolean isBounded(double onePartWidth, double onePartHeight, float x, float y) {
        Geometry geometry = getGeometry(onePartWidth, onePartHeight);
        Point point = gf.createPoint(new Coordinate(x, y));
        return geometry.contains(point);
    }
}