package com.foopi.canvas.view.components;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.util.GeometricShapeFactory;

public class Ellipse extends Component {

    private float width;
    private float height;
    private float rx;
    private float ry;

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

    public float getRx() {
        return rx;
    }

    public void setRx(float rx) {
        this.rx = rx;
    }

    public float getRy() {
        return ry;
    }

    public void setRy(float ry) {
        this.ry = ry;
    }

    @Override
    public GeomProperty getGeomProperty(float left, float top, double zoomLevel) {
        Coordinate pointCoordinate = new Coordinate();
        pointCoordinate.x = left +  actualLeft(zoomLevel);
        pointCoordinate.y = top + actualTop(zoomLevel);

        GeometricShapeFactory factory = new GeometricShapeFactory();
        factory.setCentre(new Coordinate(left + actualCoordinateValue((this.left + rx), zoomLevel),
                top + actualCoordinateValue((this.top + ry), zoomLevel)));
        factory.setWidth(actualCoordinateValue(this.rx * 2, zoomLevel));
        factory.setHeight(actualCoordinateValue(this.ry * 2, zoomLevel));
        Polygon ellipse = factory.createEllipse();
        return new GeomProperty(ellipse, getProperty());
    }
}
