package com.foopi.canvas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.foopi.canvas.view.CanvasView;
import com.foopi.canvas.view.components.Circle;
import com.foopi.canvas.view.components.Component;
import com.foopi.canvas.view.OnComponentClickListener;
import com.foopi.canvas.view.components.ComponentGroup;
import com.foopi.canvas.view.components.Polygon;
import com.foopi.canvas.view.components.Rectangle;
import com.foopi.canvas.view.components.Text;
import com.foopi.canvas.view.model.Point;
import com.vividsolutions.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;

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

        canvasView.setTotalPartWidth(1200, 1000);

        Rectangle green = new Rectangle();
        green.setLeft(80);
        green.setTop(40);
        green.setWidth(60);
        green.setHeight(70);
        green.setFillColor("#A01010");
        green.setAngle(18.93);
        green.setStrokeColor("#25243e");
        green.setStrokeWidth(1);
        green.setControls(true);
        green.setScaleX(1);
        green.setScaleY(1);
        components.add(green);

        Circle circle = new Circle();
        circle.setLeft(120);
        circle.setTop(30);
        circle.setRadius(50);
        circle.setFillColor("#3B8B1E");
        circle.setStrokeColor("#25243e");
        components.add(circle);

        Rectangle red = new Rectangle();
        red.setLeft(300);
        red.setTop(50);
        red.setWidth(60);
        red.setHeight(70);
        red.setOpacity(1f);
//        red.setFillColor("");
        red.setStrokeColor("#25243e");
        red.setStrokeWidth(1);
        red.setControls(false);
        components.add(red);


//        Image image = new Image();
//        image.setLeft(400);
//        image.setTop(10);
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/maxresdefault.jpg"));
//            image.setBitmap(BitmapFactory.decodeStream(inputStream));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        image.setScaleX(0.3f);
//        image.setScaleY(0.3f);
//        components.add(image);

        Text text = new Text();
        text.setLeft(60);
        text.setTop(160);
        text.setFillColor("#0c0c0c");
        text.setText("Add Text");
        text.setBackgroundColor("#3964C5");
        text.setTextSize(40);
        text.setControls(true);
        components.add(text);

        float left = 252.6f;
        float top = 209.6f;
        Polygon polygon = new Polygon();
        polygon.setLeft(left);
        polygon.setTop(top);
        polygon.setFillColor("#ff0000");
        polygon.setOpacity(0.2f);
        polygon.addPoint(new Point(253 - left, 210 - top));
        polygon.addPoint(new Point(347 - left, 236 - top));
        polygon.addPoint(new Point(264 - left, 292 - top));
        polygon.setControls(true);
        components.add(polygon);

        ComponentGroup componentGroup = new ComponentGroup();
        componentGroup.setLeft(352.6f);
        componentGroup.setTop(209.6f);
        componentGroup.setControls(true);
        Polygon po = new Polygon();
        po.setLeft(0);
        po.setTop(0);
        po.setFillColor("#ff0000");
        po.setOpacity(0.2f);
        po.addPoint(new Point(0, 0));
        po.addPoint(new Point(180, 0));
        po.addPoint(new Point(90, 90));
        componentGroup.addComponent(po);

        Rectangle r = new Rectangle();
        r.setLeft(0);
        r.setTop(10);
        r.setWidth(100);
        r.setHeight(50);
        r.setFillColor("#0000ff");
        componentGroup.addComponent(r);

        components.add(componentGroup);

        canvasView.setComponents(components);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
