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

    private String strokeColor;
    private float strokeWidth;

    private List<Point> points = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();

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
    }

    public void addPoint(Point point) {
        if (point != null) {
            points.add(point);
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
    public Geometry getGeometry(float left, float top, double onePartWidth, double onePartHeight) {
        updatePoints(onePartWidth, onePartHeight);
        LineString lineString = gf.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
        return lineString;
    }

    private void updatePoints(double onePartWidth, double onePartHeight) {
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            point.getCoordinate(coordinates.get(i), actualLeft(onePartWidth), actualTop(onePartHeight), onePartWidth, onePartHeight);
        }
    }

    @Override
    public void draw(float left, float top, double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        Path path = getPath(left, top, onePartWidth, onePartHeight);
        if (!TextUtils.isEmpty(strokeColor)) {
            paint.setColor(Color.parseColor(strokeColor));
            paint.setStrokeWidth(strokeWidth);
            canvas.drawPath(path, paint);
        }
    }
}
