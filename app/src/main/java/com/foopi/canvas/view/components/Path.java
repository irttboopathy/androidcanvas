package com.foopi.canvas.view.components;

import android.graphics.Matrix;
import android.util.Log;

import com.foopi.canvas.view.model.ClosePathPoint;
import com.foopi.canvas.view.model.CubicPathPoint;
import com.foopi.canvas.view.model.LinePathPoint;
import com.foopi.canvas.view.model.MovePathPoint;
import com.foopi.canvas.view.model.PathPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Polygon;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path extends PointComponent {

    @Override
    public GeomProperty getGeomProperty(float left, float top, double zoomLevel) {
        updatePoints();

        List<Geometry> geometries = new ArrayList<>();
        List<Coordinate> groupedCoordinates = new ArrayList<>();

        boolean firstTime = true;
        for (int i = 0; i < modifiedPoints.size(); i++) {
            PathPoint pathPoint = (PathPoint) modifiedPoints.get(i);
            pathPoint.getCoordinate(coordinates.get(i), left + actualLeft(zoomLevel), top + actualTop(zoomLevel), zoomLevel);

            groupedCoordinates.add(coordinates.get(i));
            if (!firstTime && pathPoint instanceof MovePathPoint) {
                LineString lineString = gf.createLineString(groupedCoordinates.toArray(new Coordinate[groupedCoordinates.size()]));
                geometries.add(lineString);
                groupedCoordinates.clear();
            }
            firstTime = false;
        }

        if (!groupedCoordinates.isEmpty()) {
            LineString lineString = gf.createLineString(groupedCoordinates.toArray(new Coordinate[groupedCoordinates.size()]));
            boolean closed = lineString.isClosed();
            List<Coordinate> coordinates = new ArrayList<>(Arrays.asList(lineString.getCoordinates()));
            if (!closed) {
                coordinates.add(new Coordinate(coordinates.get(0)));
            }
            Polygon polygon = gf.createPolygon(coordinates.toArray(new Coordinate[coordinates.size()]));
            geometries.add(polygon);
        }
        GeometryCollection gc = new GeometryCollection(geometries.toArray(new Geometry[geometries.size()]), gf);
        Geometry union = gc.union();
        return new GeomProperty(union, getProperty());
    }

    @Override
    public android.graphics.Path getPath(float left, float top, double zoomLevel) {
        updatePoints();

        this.path.reset();
        for (int i = 0; i < modifiedPoints.size(); i++) {
            PathPoint pathPoint = (PathPoint) modifiedPoints.get(i);
            pathPoint.updatePath(path, left + actualLeft(zoomLevel), top + actualTop(zoomLevel), zoomLevel);
        }
        Matrix matrix = new Matrix();
        if (angle != 0) {
            Log.e(getClass().toString(), "Hi");
        }
        matrix.setRotate((float) angle, left + actualLeft(zoomLevel), top + actualTop(zoomLevel));
        matrix.setScale((float) scaleX, (float) scaleY, left + actualLeft(zoomLevel), top + actualTop(zoomLevel));
        path.transform(matrix);
        return path;
    }

    public void setPathPoints(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray j = jsonArray.getJSONArray(i);
            if (j.getString(0).equals("M")) {
                addPoint(new MovePathPoint((float) j.getDouble(1), (float) j.getDouble(2)));
            } else if (j.getString(0).equals("L")) {
                addPoint(new LinePathPoint((float) j.getDouble(1), (float) j.getDouble(2)));
            } else if (j.getString(0).equals("C")) {
                addPoint(new CubicPathPoint(

                        (float) j.getDouble(3), (float) j.getDouble(4),
                                (float) j.getDouble(1), (float) j.getDouble(2),
                        (float) j.getDouble(5), (float) j.getDouble(6))
                        );
            } else if (j.getString(0).equals("Z")) {
                addPoint(new ClosePathPoint());
            }
        }
    }
}
