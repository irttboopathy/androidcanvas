package com.foopi.canvas.view.components;

import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Polygon extends PointComponent {

    @Override
    public GeomProperty getGeomProperty(float left, float top, double zoomLevel) {
        if (!validated) {
            if (getPoints().size() >= 3) {
                Point first = getPoints().get(0);
                Point last = getPoints().get(getPoints().size() - 1);
                if (!first.equals(last)) {
                    addPoint(new Point(first));
                }
            }
            else {
                throw new IllegalArgumentException("Atleast three points should be given");
            }

            updatePoints();
        }

        for (int i = 0; i < modifiedPoints.size(); i++) {
            Point point = modifiedPoints.get(i);
            point.getCoordinate(coordinates.get(i), left + actualLeft(zoomLevel), top + actualTop(zoomLevel), zoomLevel);
        }
        com.vividsolutions.jts.geom.Polygon polygon = gf.createPolygon(coordinates.toArray(new Coordinate[coordinates.size()]));
        return new GeomProperty(polygon, getProperty());
    }

    public void setPoints(JSONArray p1Array) throws JSONException {
        for (int i = 0; i < p1Array.length(); i++) {
            JSONObject jsonObject = p1Array.getJSONObject(i);
            addPoint(new Point((float) jsonObject.getDouble("x"), (float) jsonObject.getDouble("y")));
        }
    }
}
