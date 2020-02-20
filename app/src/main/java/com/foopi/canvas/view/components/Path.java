package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;

import com.foopi.canvas.view.model.ClosePathPoint;
import com.foopi.canvas.view.model.CubicPathPoint;
import com.foopi.canvas.view.model.LinePathPoint;
import com.foopi.canvas.view.model.MovePathPoint;
import com.foopi.canvas.view.model.PathPoint;
import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Path extends Component {

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;
    private float opacity = 1;

    private List<PathPoint> pathPoints = new ArrayList<>();
    private List<PathPoint> modifiedPathPoints = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();

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

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public List<PathPoint> getPathPoints() {
        return pathPoints;
    }

    public void setPathPoints(List<PathPoint> pathPoints) {
        if (pathPoints == null) {
            pathPoints = new ArrayList<>();
        }
        this.pathPoints = pathPoints;

        coordinates.clear();
        validated = false;
        for (Point p : pathPoints) {
            coordinates.add(p.getCoordinate());
        }
    }

    public void addPathPoint(PathPoint pathPoint) {
        addPathPoint(pathPoint, false);
    }

    private void addPathPoint(PathPoint pathPoint, boolean isValidPoints) {
        if (pathPoint != null) {
            pathPoints.add(pathPoint);
            coordinates.add(new Coordinate());
            validated = false;
        }
    }

    @Override
    public Geometry getGeometry(float left, float top, double onePartWidth, double onePartHeight) {
        updateCoordinates(left, top, onePartWidth, onePartHeight);
//        gf.
        return null;
    }

    private void updateCoordinates(float left, float top, double onePartWidth, double onePartHeight) {
        if (validated) {
            return;
        }

        float minX = Integer.MAX_VALUE;
        float minY = Integer.MAX_VALUE;
        Point p = new Point(minX, minY);

        for (PathPoint pathPoint : pathPoints) {
            pathPoint.getMin(p);
        }

        modifiedPathPoints = new ArrayList<>(pathPoints.size());
        validated = true;
        for (int i = 0; i < pathPoints.size(); i++) {
            PathPoint point = pathPoints.get(i);
            PathPoint clonedPathPoint = point.clonePathPoint();
            clonedPathPoint.adjustPoints(p);

            modifiedPathPoints.add(clonedPathPoint);
            clonedPathPoint.getCoordinate(coordinates.get(i), left + actualLeft(onePartWidth), top + actualTop(onePartHeight), onePartWidth, onePartHeight);
        }
    }

    @Override
    public android.graphics.Path getPath(float left, float top, double onePartWidth, double onePartHeight) {
        updateCoordinates(left, top, onePartWidth, onePartHeight);

        this.path.reset();
        for (int i = 0; i < modifiedPathPoints.size(); i++) {
            PathPoint pathPoint = modifiedPathPoints.get(i);
            pathPoint.updatePath(path, left + actualLeft(onePartWidth), top + actualTop(onePartHeight), onePartWidth, onePartHeight);
        }
        return path;
    }

    @Override
    public void draw(float left, float top, double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        android.graphics.Path path = getPath(left, top, onePartWidth, onePartHeight);

        if (!TextUtils.isEmpty(fillColor)) {
            try {
                paint.setColor(Color.parseColor(fillColor));
            } catch (Exception w) {
                paint.setColor(Color.RED);
            }
            paint.setAlpha((int) (opacity * 255));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, paint);
        }
        if (!TextUtils.isEmpty(strokeColor)) {
            paint.setColor(Color.parseColor(strokeColor));
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
        }
    }

    public void setPathPoints(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray j = jsonArray.getJSONArray(i);
            if (j.getString(0).equals("M")) {
                addPathPoint(new MovePathPoint((float) j.getDouble(1), (float) j.getDouble(2)));
            } else if (j.getString(0).equals("L")) {
                addPathPoint(new LinePathPoint((float) j.getDouble(1), (float) j.getDouble(2)));
            } else if (j.getString(0).equals("C")) {
                addPathPoint(new CubicPathPoint(

                        (float) j.getDouble(3), (float) j.getDouble(4),
                                (float) j.getDouble(1), (float) j.getDouble(2),
                        (float) j.getDouble(5), (float) j.getDouble(6))
                        );
            } else if (j.getString(0).equals("Z")) {
                addPathPoint(new ClosePathPoint());
            }
        }
    }
}
