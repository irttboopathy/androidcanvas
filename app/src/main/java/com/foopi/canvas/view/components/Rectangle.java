package com.foopi.canvas.view.components;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;

import org.json.JSONException;
import org.json.JSONObject;

public class Rectangle extends Component {

    private float width;
    private float height;


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
        return polygon;
    }

}