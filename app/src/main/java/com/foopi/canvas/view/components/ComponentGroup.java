package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComponentGroup extends Component {

    private List<Component> components = new ArrayList<>();

    private List<Component> positionedComponents = new ArrayList<>();

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        if (components == null) {
            components = new ArrayList<>();
        }
        this.components = components;
        movedComponentsToNewPosition = false;
    }

    public void addComponent(Component component) {
        components.add(component);
        movedComponentsToNewPosition = false;
    }

    private boolean movedComponentsToNewPosition = false;
    private List<Geometry> geometries = new ArrayList<>();

//    @Override
//    public Geometry getGeometry(float left, float top, double zoomLevel) {
//        if (!movedComponentsToNewPosition) {
//            moveComponentsToNewPosition();
//        }
//        geometries.clear();
//        for (Component component : positionedComponents) {
//            Geometry geometry = component.getGeometry(left, top, zoomLevel);
//            geometries.add(geometry);
//        }
//        GeometryCollection gc = new GeometryCollection(geometries.toArray(new Geometry[geometries.size()]), gf);
//        Geometry union = gc.union();
//        return union;
//    }

    @Override
    public GeomProperty getGeomProperty(float left, float top, double zoomLevel) {
        if (!movedComponentsToNewPosition) {
            moveComponentsToNewPosition();
        }
        geometries.clear();
        List<GeomProperty> geomProperties = new ArrayList<>();
        for (Component component : positionedComponents) {
            GeomProperty geomProperty = component.createGeometry(left, top, zoomLevel);
            geomProperties.add(geomProperty);
//            for (GeomProperty geomProperty : geometries) {
                this.geometries.add(geomProperty.geometry);
//            }
        }
        return new GeomCollProperty(
                new GeometryCollection(geometries.toArray(new Geometry[geometries.size()]), gf),
                geomProperties.toArray(new GeomProperty[geomProperties.size()]));
//        return Arrays.asList((Geometry) new GeometryCollection(geometries.toArray(new Geometry[geometries.size()]), gf));
    }

    private void moveComponentsToNewPosition() {
        positionedComponents.clear();
        try {
            float minX = Integer.MAX_VALUE;
            float minY = Integer.MAX_VALUE;
            Point p = new Point(minX, minY);

            for (Component component : components) {
                new Point(component.getLeft(), component.getTop()).getMin(p);
            }

            for (Component component : components) {
                Component clone = (Component) component.clone();
                clone.setLeft((component.getLeft() + left - p.x));
                clone.setTop((component.getTop() + top - p.y));
                positionedComponents.add(clone);
            }
        } catch (Exception ex) {}
        movedComponentsToNewPosition = true;
    }

//    @Override
//    public void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint) {
//        GeomCollProperty geometryProperty = (GeomCollProperty) createGeometry(left, top, zoomLevel);
//
//    }
}
