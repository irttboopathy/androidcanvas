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
import com.foopi.canvas.view.components.Rectangle;
import com.foopi.canvas.view.components.Text;

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

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

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


        canvasView.setComponents(components);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
