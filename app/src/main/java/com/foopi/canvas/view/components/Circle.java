package com.foopi.canvas.view.components;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

public class Circle extends Component {

    private float radius;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    private Coordinate pointCoordinate = new Coordinate();

    @Override
    public Geometry getGeometry(float left, float top, double zoomLevel) {
        pointCoordinate.x = left +  actualCoordinateValue((this.left + this.radius), zoomLevel);
        pointCoordinate.y = top + actualCoordinateValue((this.top + this.radius), zoomLevel);

        Point point = gf.createPoint(pointCoordinate);
        Geometry circle = point.buffer(radius * zoomLevel);
        return circle;
    }
}
