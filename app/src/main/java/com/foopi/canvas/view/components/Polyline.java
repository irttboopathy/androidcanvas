package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Polyline extends Component {

    private String fillColor;
    private String strokeColor;
    private float strokeWidth;

    private List<Point> points = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();
    private List<Point> modifiedPathPoints = new ArrayList<>();

    private boolean validated = false;

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

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        validated = false;
    }

    public void addPoint(Point point) {
        if (point != null) {
            points.add(point);
            coordinates.add(new Coordinate());
            validated = false;
        }
    }

    public void setPoints(JSONArray p1Array) throws JSONException {
        for (int i = 0; i < p1Array.length(); i++) {
            JSONObject jsonObject = p1Array.getJSONObject(i);
            addPoint(new Point((float) jsonObject.getDouble("x"), (float) jsonObject.getDouble("y")));
        }
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Geometry getGeometry(float left, float top, double zoomLevel) {
        updatePoints(left, top, zoomLevel);
        LineString lineString = gf.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
        return lineString;
    }

    private void updatePoints(float left, float top, double zoomLevel) {
        if (validated) {
            return;
        }

        float minX = Integer.MAX_VALUE;
        float minY = Integer.MAX_VALUE;

        for (Point point : points) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
        }

        modifiedPathPoints = new ArrayList<>(points.size());
        validated = true;
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            Point clonedPathPoint = new Point(point);
            clonedPathPoint.x -= minX;
            clonedPathPoint.y -= minY;

            modifiedPathPoints.add(clonedPathPoint);
            clonedPathPoint.getCoordinate(coordinates.get(i), left + actualLeft(zoomLevel), top + actualTop(zoomLevel), zoomLevel);
        }
    }

    @Override
    public void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint) {
        Path path = getPath(left, top, zoomLevel);
        if (!TextUtils.isEmpty(fillColor)) {
            paint.setColor(Color.parseColor(fillColor));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, paint);
        }
        if (!TextUtils.isEmpty(strokeColor)) {
            paint.setColor(Color.parseColor(strokeColor));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, paint);
        }
    }
}
