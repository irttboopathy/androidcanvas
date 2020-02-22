package com.foopi.canvas.view.components;

import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Polyline extends PointComponent {

    public void setPoints(JSONArray p1Array) throws JSONException {
        for (int i = 0; i < p1Array.length(); i++) {
            JSONObject jsonObject = p1Array.getJSONObject(i);
            addPoint(new Point((float) jsonObject.getDouble("x"), (float) jsonObject.getDouble("y")));
        }
    }

    @Override
    public GeomProperty getGeomProperty(float left, float top, double zoomLevel) {
        updatePoints();
        this.path.reset();
        for (int i = 0; i < modifiedPoints.size(); i++) {
            Point pathPoint = modifiedPoints.get(i);
            pathPoint.getCoordinate(coordinates.get(i),left + actualLeft(zoomLevel), top + actualTop(zoomLevel), zoomLevel);
        }
//        at.setToRotation(angle * Math.PI / 180, actualLeft(zoomLevel), actualTop(zoomLevel));
        LineString lineString = gf.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
        return new GeomProperty(lineString, getProperty());
    }
}
