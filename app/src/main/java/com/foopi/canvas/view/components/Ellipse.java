package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.util.GeometricShapeFactory;

public class Ellipse extends Component {

    private float width;
    private float height;
    private float rx;
    private float ry;

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;
    private float opacity = 1;

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

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        if (fillColor.startsWith("rgba")) {
//            Color.ar
            this.fillColor = "#ff0000";
        }
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
    public Geometry getGeometry(float left, float top, double onePartWidth, double onePartHeight) {
        Coordinate pointCoordinate = new Coordinate();
        pointCoordinate.x = left +  actualLeft(onePartWidth);
        pointCoordinate.y = top + actualTop(onePartHeight);

        GeometricShapeFactory factory = new GeometricShapeFactory();
        factory.setCentre(new Coordinate(left + actualLeft(onePartWidth), top + actualTop(onePartHeight)));
        factory.setWidth(actualX(this.rx, onePartWidth));
        factory.setHeight(actualY(this.ry, onePartHeight));
        Polygon ellipse = factory.createEllipse();
        return ellipse;

//        LineString lineString = gf.createLineString(new Coordinate[]{
//                new Coordinate(left + actualX(this.left + this.rx, onePartWidth), top + actualY(this.top + this.rx, onePartHeight)),
//                new Coordinate(left + actualX(this.left + this.rx, onePartWidth), top + actualY(this.top + this.ry, onePartHeight))
//        });
//
//        float minRadius = rx > ry ? ry : rx;
//        Geometry circle = lineString.buffer(minRadius * onePartWidth);
//        return circle;
//        gf.create
//        return null;
    }

//    @Override
//    public void setTop(float top) {
//        if (top < 0) {
//            top = 0;
//        }
//        super.setTop(top);
//    }

    protected float actualLeft(double onePartWidth) {
        return (float) ((left + rx / 2) * onePartWidth);
    }

    protected float actualTop(double onePartHeight) {
        return (float) ((top + ry / 2) * onePartHeight);
    }

    @Override
    public void draw(float left, float top, double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        Path path = getPath(left, top, onePartWidth, onePartHeight);
        if (!TextUtils.isEmpty(fillColor)) {
            try {
                paint.setColor(Color.parseColor(fillColor));
            } catch (Exception e) {
                paint.setColor(Color.RED);
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha((int) (255 * opacity));
            canvas.drawPath(path, paint);
//            canvas.drawOval(left + actualLeft(onePartWidth), top + actualTop(onePartWidth),
//                    actualX(this.left + this.left + rx, onePartWidth) + left,
//                    actualY(this.top + this.top + ry, onePartHeight) + top, paint);
        }
    }
}
