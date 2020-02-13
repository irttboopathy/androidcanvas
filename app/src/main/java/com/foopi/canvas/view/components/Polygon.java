package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends Component {

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;
    private float opacity = 1;

    private List<Point> points = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        if (points == null) {
            points = new ArrayList<>();
        }
        this.points = points;
        isValidPoints = false;

        coordinates.clear();
        for (Point p : points) {
            coordinates.add(p.getCoordinate());
        }
    }

    public void addPoint(Point point) {
        addPoint(point, false);
    }

    private void addPoint(Point point, boolean isValidPoints) {
        if (point != null) {
            points.add(point);
            this.isValidPoints = isValidPoints;

            coordinates.add(new Coordinate());
        }
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

    @Override
    public Geometry getGeometry(double onePartWidth, double onePartHeight) {
        if (!isValidPoints) {
            if (points.size() >= 3) {
                Point first = points.get(0);
                Point last = points.get(points.size() - 1);
                if (first.equals(last)) {
                    isValidPoints = true;
                }
                else {
                    addPoint(new Point(first), true);
                }
            }
            else {
                throw new IllegalArgumentException("Atleast three points should be given");
            }

        }
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            point.getCoordinate(coordinates.get(i), actualLeft(onePartWidth), actualTop(onePartHeight));
        }
        com.vividsolutions.jts.geom.Polygon polygon = gf.createPolygon(coordinates.toArray(new Coordinate[coordinates.size()]));
        return polygon;
    }

    private boolean isValidPoints = false;

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
            canvas.drawPath(path, paint);
        }
    }


}
