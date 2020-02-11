package com.foopi.canvas;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foopi.canvas.view.CanvasView;
import com.foopi.canvas.view.components.Component;
import com.foopi.canvas.view.OnComponentClickListener;
import com.foopi.canvas.view.components.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CanvasView canvasView = new CanvasView(this);
        setContentView(canvasView);
        canvasView.setOnComponentClickListener(new OnComponentClickListener() {
            @Override
            public void onComponentClick(CanvasView canvasView, Component component) {
                Toast.makeText(MainActivity.this, "Component Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        List<Component> components = new ArrayList<>();

        canvasView.setTotalPartWidth(120, 120);

        Rectangle green = new Rectangle();
        green.setLeft(10);
        green.setTop(50);
        green.setWidth(50);
        green.setHeight(50);
        green.setFillColor("#00FF00");
        green.setStrokeColor("#000000");
        green.setStrokeWidth(20);
        green.setHasControls(true);
        components.add(green);

        Rectangle red = new Rectangle();
        red.setLeft(40);
        red.setTop(60);
        red.setWidth(50);
        red.setHeight(50);
        red.setOpacity(0.8f);
        red.setFillColor("#FF0000");
        red.setStrokeColor("#000000");
        red.setStrokeWidth(2);
        red.setHasControls(false);
        components.add(red);


        canvasView.setComponents(components);

    }
}
