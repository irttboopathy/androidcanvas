package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.util.AffineTransformation;

import org.json.JSONException;
import org.json.JSONObject;

public class Rectangle extends Component {

    private float width;
    private float height;

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;

    private float opacity = 1f;
    private double angle = 0;
    private double scaleX = 1;
    private double scaleY = 1;

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

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
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

        controls = param.optBoolean("controls", false);
    }

    private float actualRight(double onePartWidth) {
        return (float) ((left + width) * onePartWidth);
    }
    private float actualBottom(double onePartHeight) {
        return (float) ((top + height) * onePartHeight);
    }

    private Coordinate topLeftCoordinate = new Coordinate();
    private Coordinate topRightCoordinate = new Coordinate();
    private Coordinate bottomLeftCoordinate = new Coordinate();
    private Coordinate bottomRightCoordinate = new Coordinate();
    private Coordinate[] coordinates = {topLeftCoordinate, topRightCoordinate, bottomRightCoordinate, bottomLeftCoordinate, topLeftCoordinate};

    @Override
    public Geometry getGeometry(float left, float top, double zoomLevel) {
        topLeftCoordinate.x = left + actualLeft(zoomLevel);
        topLeftCoordinate.y = top + actualTop(zoomLevel);

        topRightCoordinate.x = left + actualRight(zoomLevel);
        topRightCoordinate.y = top + actualTop(zoomLevel);

        bottomLeftCoordinate.x = left + actualLeft(zoomLevel);
        bottomLeftCoordinate.y = top + actualBottom(zoomLevel);

        bottomRightCoordinate.x = left + actualRight(zoomLevel);
        bottomRightCoordinate.y = top + actualBottom(zoomLevel);

        Polygon polygon = gf.createPolygon(coordinates);

        AffineTransformation at = new AffineTransformation();
        at.setToRotation(angle * Math.PI / 180, actualLeft(zoomLevel), actualTop(zoomLevel));
        at.translate(-actualLeft(zoomLevel), -actualTop(zoomLevel));
        at.scale(scaleX, scaleY);
        at.translate(actualLeft(zoomLevel), actualTop(zoomLevel));
        polygon = (Polygon) at.transform(polygon);
        return polygon;
    }

    @Override
    public void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint) {
        Path path = getPath(left, top, zoomLevel);
        if (!TextUtils.isEmpty(fillColor)) {
            try {
                paint.setColor(Color.parseColor(fillColor));
            } catch (Exception ex) {
                paint.setColor(Color.RED);
            }
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