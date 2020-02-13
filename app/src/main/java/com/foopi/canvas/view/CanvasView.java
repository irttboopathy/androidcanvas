package com.foopi.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.foopi.canvas.view.components.Component;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View {

    private Paint paint;

    private double onePartWidth;
    private double onePartHeight;

    private double totalPartWidth;
    private double totalPartHeight;

    private List<Component> components = new ArrayList<>();

    private OnComponentClickListener onComponentClickListener;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void setTotalPartWidth(double totalPartWidth, double totalPartHeight) {
        this.totalPartWidth = totalPartWidth;
        this.totalPartHeight = totalPartHeight;
        this.postInvalidate();
    }

    public double getOnePartWidth() {
        return onePartWidth;
    }

    public double getOnePartHeight() {
        return onePartHeight;
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        try {
            if (measuredWidth > measuredHeight) {
                measuredWidth = measuredHeight;
            }
            else {
                measuredHeight = measuredWidth;
            }

            setMeasuredDimension(measuredWidth, measuredHeight);

            onePartWidth = measuredWidth / totalPartWidth;
            onePartHeight = measuredHeight / totalPartHeight;

        } catch (Exception ex) {}
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

        try {
            for (Component component : components) {
                component.draw(onePartWidth, onePartHeight, canvas, paint);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
                    boolean bounded = component.isBounded(onePartWidth, onePartHeight, x, y);
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
}
