package com.foopi.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.foopi.canvas.view.components.Component;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {

    private float top = 0;
    private float left = 0;

    private Paint paint;

//    private double onePartWidth;
//    private double onePartHeight;

//    private double totalPartWidth;
//    private double totalPartHeight;

    private List<Component> components = new ArrayList<>();

    private OnComponentClickListener onComponentClickListener;
    private double widthPercentage;
    private double heightPercentage;
    private float zoomLevel = 1.0f;
    private Component highlight;

    public CanvasView(Context context) {
        super(context);
        initializeView();
    }

    private void initializeView() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void zoomLevel(float zoomLevel) {
        this.zoomLevel = zoomLevel;
        this.invalidate();
    }

//    public void setTotalPartWidth(double totalPartWidth, double totalPartHeight) {
//        this.totalPartWidth = totalPartWidth;
//        this.totalPartHeight = totalPartHeight;
//        this.postInvalidate();
//    }

//    public double getOnePartWidth() {
//        return onePartWidth;
//    }
//
//    public double getOnePartHeight() {
//        return onePartHeight;
//    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        if (getMeasuredHeight() > getMeasuredWidth()) {
//            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
//        }
//        else {
//            setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
//        }

//        widthPercentage = getMeasuredWidth() / totalPartWidth;
//        heightPercentage = getMeasuredHeight() / totalPartHeight;
    }

    public OnComponentClickListener getOnComponentClickListener() {
        return onComponentClickListener;
    }

    public void setOnComponentClickListener(OnComponentClickListener onComponentClickListener) {
        this.onComponentClickListener = onComponentClickListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

//        onePartWidth = getMeasuredWidth() / totalPartWidth;
//        onePartHeight = getMeasuredHeight() / totalPartHeight;


        try {
            for (Component component : components) {
                component.draw(left, top, zoomLevel, canvas, paint);
            }

            if (highlight != null) {
                Geometry geometry = highlight.createGeometry(left, top, zoomLevel);
                Path path = new Path();
                if (geometry instanceof GeometryCollection) {
                    GeometryCollection collection = (GeometryCollection) geometry;
                    for (int i = 0; i < collection.getNumGeometries(); i++) {
                        Geometry geometryN = collection.getGeometryN(i);
                        updatePath(geometryN, path);
                    }
                }
                else {
                    updatePath(geometry, path);
                }
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5.0f);
                canvas.drawPath(path, paint);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updatePath(Geometry geometry, Path path) {
        Coordinate[] coordinates = geometry.getCoordinates();
        for (int i = 0; i < coordinates.length; i++) {
            Coordinate coordinate = coordinates[i];
            if (i == 0) {
                path.moveTo((float) coordinate.x, (float) coordinate.y);
            } else {
                path.lineTo((float) coordinate.x, (float) coordinate.y);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        if(event.getAction()== MotionEvent.ACTION_DOWN){
            for (int i = components.size() - 1; i >= 0; i--) {
                Component component = components.get(i);
                if (component.isVisible() && component.isControls()) {
                    highlight = component;
                    invalidate();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            highlight = null;
                            invalidate();
                        }
                    }, 5000);
                    boolean bounded = component.isBounded(zoomLevel, x, y);
                    if (bounded) {
                        if (onComponentClickListener != null) {
                            onComponentClickListener.onComponentClick(this, component);
                        }
                        break;
                    }
                }
             }
        }
        return super.onTouchEvent(event);
    }

    public void setComponents(List<Component> components) {
        if (components == null) {
            components = new ArrayList<>();
        }
        this.components = components;
        this.postInvalidate();
    }

    private float percentageZoom = 1.1f;
    public void zoomIn() {
        this.zoomLevel *= percentageZoom;
//        totalPartWidth /= (percentageZoom);
//        totalPartHeight /= (percentageZoom);
//
//        Log.e(getClass().toString(), totalPartWidth + " : " + totalPartHeight);
        this.invalidate();
    }

    public void zoomOut() {
        this.zoomLevel /= percentageZoom;
//        totalPartWidth *= (percentageZoom);
//        totalPartHeight *= (percentageZoom);
//
//        Log.e(getClass().toString(), totalPartWidth + " : " + totalPartHeight);
        this.invalidate();
    }

    public void left() {
        left -= 100;
//        if (left < 0) {
//            left = 0;
//        }
        this.invalidate();
    }

    public void right() {
        left += 100;
        invalidate();
    }

    public void up() {
        top -= 100;
        invalidate();
    }

    public void down() {
        top += 100;
        invalidate();
    }
}
