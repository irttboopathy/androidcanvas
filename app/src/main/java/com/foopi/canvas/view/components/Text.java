package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;

public class Text extends Component {

    private String text;
    private float textSize = 12;

    private String fillColor = "#000000";
    private String backgroundColor;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    private Paint myPaint = new Paint();
    private Rect bounds = new Rect();

    private float padding = 10f;

    private Coordinate topLeftCoordinate = new Coordinate();
    private Coordinate topRightCoordinate = new Coordinate();
    private Coordinate bottomLeftCoordinate = new Coordinate();
    private Coordinate bottomRightCoordinate = new Coordinate();
    private Coordinate[] coordinates = {topLeftCoordinate, topRightCoordinate, bottomRightCoordinate, bottomLeftCoordinate, topLeftCoordinate};

    @Override
    public Geometry getGeometry(float left, float top, double onePartWidth, double onePartHeight) {
        myPaint.setColor(Color.parseColor(fillColor));
        myPaint.setTextSize((float) (textSize * (onePartWidth + onePartHeight) / 2));
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.getTextBounds(text, 0, text.length(), bounds);

        topLeftCoordinate.x = actualLeft(onePartWidth) - padding;
        topLeftCoordinate.y = actualTop(onePartHeight) - padding;

        topRightCoordinate.x = actualLeft(onePartWidth) + bounds.width() + padding;
        topRightCoordinate.y = actualTop(onePartHeight) - padding;

        bottomLeftCoordinate.x = actualLeft(onePartWidth) - padding;
        bottomLeftCoordinate.y = actualTop(onePartHeight) + bounds.height() + padding;

        bottomRightCoordinate.x = actualLeft(onePartWidth) + bounds.width() + padding;
        bottomRightCoordinate.y = actualTop(onePartHeight) + bounds.height() + padding;

        Polygon polygon = gf.createPolygon(coordinates);
        return polygon;
    }

    @Override
    public void draw(float left, float top, double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        if (!TextUtils.isEmpty(backgroundColor)) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.parseColor(backgroundColor));
            Path path = getPath(left, top, onePartWidth, onePartHeight);
            canvas.drawPath(path, paint);
        }

        paint.setColor(Color.parseColor(fillColor));
        paint.setTextSize((float) (textSize * (onePartWidth + onePartHeight) / 2));
        paint.setStyle(Paint.Style.FILL);
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, actualLeft(onePartWidth), actualTop(onePartHeight) + bounds.height(), paint);
    }
}
