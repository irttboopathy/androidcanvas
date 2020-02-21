package com.foopi.canvas.view.components;

import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class PointComponent extends Component {

    protected boolean validated = false;
    private List<Point> points = new ArrayList<>();
    protected List<Point> modifiedPoints;
    protected List<Coordinate> coordinates = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        if (points == null) {
            points = new ArrayList<>();
        }
        this.points = points;
        validated = false;

        coordinates.clear();
        for (int i = 0; i < points.size(); i++) {
            coordinates.add(new Coordinate());
        }
    }

    public void addPoint(Point point) {
        if (point != null) {
            points.add(point);
            this.validated = false;
            coordinates.add(new Coordinate());
        }
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    protected void updatePoints() {
        if (validated) {
            return;
        }

        float minX = Integer.MAX_VALUE;
        float minY = Integer.MAX_VALUE;
        Point p = new Point(minX, minY);

        for (Point point : points) {
            point.getMin(p);
        }

        modifiedPoints = new ArrayList<>(points.size());
        validated = true;
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            Point clonedPathPoint = point.clonePoint();
            clonedPathPoint.adjustPoints(p);

            modifiedPoints.add(clonedPathPoint);
        }
    }
}
