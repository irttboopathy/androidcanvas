package com.foopi.canvas.view.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Rectangle extends Component {

    private float top;
    private float left;
    private float width;
    private float height;

    private String fillColor;
    private String strokeColor;
    private float strokeWidth = 2f;

    private boolean hasControls = false;
    private float opacity = 1f;

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

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

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
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

    public boolean isHasControls() {
        return hasControls;
    }

    public void setHasControls(boolean hasControls) {
        this.hasControls = hasControls;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
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

        hasControls = param.optBoolean("hasControls", false);
    }

    private float actualLeft(double onePartWidth) {
        return (float) (left * onePartWidth);
    }
    private float actualTop(double onePartHeight) {
        return (float) (top * onePartHeight);
    }
    private float actualRight(double onePartWidth) {
        return (float) ((left + width) * onePartWidth);
    }
    private float actualBottom(double onePartHeight) {
        return (float) ((top + height) * onePartHeight);
    }

    @Override
    public boolean hasControls() {
        return hasControls;
    }

    public RectF getRectF(double onePartWidth, double onePartHeight) {
        RectF rectF = new RectF(actualLeft(onePartWidth), actualTop(onePartHeight), actualRight(onePartWidth), actualBottom(onePartHeight));
        Log.e(Rectangle.class.getName(), rectF.toString());
        return rectF;
    }

    @Override
    public void draw(double onePartWidth, double onePartHeight, Canvas canvas, Paint paint) {
        if (!TextUtils.isEmpty(fillColor)) {
            paint.setColor(Color.parseColor(fillColor));
            paint.setStyle(Paint.Style.FILL);
            paint.setAlpha((int) (255 * opacity));
            canvas.drawRect(getRectF(onePartWidth, onePartHeight), paint);
        }
        if (!TextUtils.isEmpty(strokeColor)) {
            paint.setColor(Color.parseColor(strokeColor));
            paint.setStrokeWidth(strokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAlpha(255);
            canvas.drawRect(getRectF(onePartWidth, onePartHeight), paint);
        }
    }

    @Override
    public boolean isBounded(double onePartWidth, double onePartHeight, float x, float y) {
        if (x >= actualLeft(onePartWidth) && x <= actualRight(onePartWidth)
                && y >= actualTop(onePartHeight) && y <= actualBottom(onePartHeight)) {
            return true;
        }
        return false;
    }
}