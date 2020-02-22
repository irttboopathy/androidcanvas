package com.foopi.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foopi.canvas.view.CanvasView;
import com.foopi.canvas.view.OnComponentClickListener;
import com.foopi.canvas.view.components.Circle;
import com.foopi.canvas.view.components.Component;
import com.foopi.canvas.view.components.ComponentGroup;
import com.foopi.canvas.view.components.Ellipse;
import com.foopi.canvas.view.components.Path;
import com.foopi.canvas.view.components.Polygon;
import com.foopi.canvas.view.components.Polyline;
import com.foopi.canvas.view.components.Rectangle;
import com.foopi.canvas.view.components.Text;
import com.foopi.canvas.view.model.ClosePathPoint;
import com.foopi.canvas.view.model.CubicPathPoint;
import com.foopi.canvas.view.model.LinePathPoint;
import com.foopi.canvas.view.model.MovePathPoint;
import com.foopi.canvas.view.model.Point;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CanvasView canvasView;
    private Button zoomIn;
    private Button zoomOut;
    private Button up;
    private Button left;
    private Button right;
    private Button bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView textView = findViewById(R.id.sample);
//        textView.setText("Sample");

//        CanvasView canvasView = new CanvasView(this);
        canvasView = findViewById(R.id.canvas);
        zoomIn = findViewById(R.id.zoomin);
        zoomIn.setOnClickListener(this);
        zoomOut = findViewById(R.id.zoomout);
        zoomOut.setOnClickListener(this);

        up = findViewById(R.id.up);
        up.setOnClickListener(this);
        left = findViewById(R.id.left);
        left.setOnClickListener(this);
        right = findViewById(R.id.right);
        right.setOnClickListener(this);
        bottom = findViewById(R.id.down);
        bottom.setOnClickListener(this);
//        canvasView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        setContentView(canvasView);
        canvasView.setOnComponentClickListener(new OnComponentClickListener() {
            @Override
            public void onComponentClick(CanvasView canvasView, Component component) {
                Toast.makeText(MainActivity.this, "Component Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        canvasView.zoomLevel(1f);

        List<Component> components = new ArrayList<>();

//        canvasView.setTotalPartWidth(300, 250);

//        Circle c = new Circle();
//        c.setTop(100);
//        c.setLeft(100);
//        c.setRadius(50);
//        c.setFillColor("#ff0f0f");
//        components.add(c);
//
//        Rectangle r = new Rectangle();
//        r.setTop(40);
//        r.setLeft(60);
//        r.setWidth(10);
//        r.setHeight(30);
//        r.setFillColor("#ff0fff");
//        components.add(r);

        try {
            parseJSONArray(components);

            ComponentGroup componentGroup = new ComponentGroup();
            componentGroup.setTop(0);
            componentGroup.setLeft(0);
            componentGroup.setFillColor("#ff0000");
            componentGroup.setScaleX(2);
            componentGroup.setScaleY(2);

            Path p1 = new Path();
            p1.setLeft(48.3f);
            p1.setTop(16.5f);
            p1.setAngle(0);
            p1.setControls(true);
            p1.setPathPoints(new JSONArray("[\n" +
                    "            [\n" +
                    "              \"M\",\n" +
                    "              80.9399304,\n" +
                    "              58.3186943\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              \"L\",\n" +
                    "              80.9399304,\n" +
                    "              58.3186943\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              \"C\",\n" +
                    "              85.4108069,\n" +
                    "              58.3186943,\n" +
                    "              89.0351685,\n" +
                    "              46.3967103,\n" +
                    "              89.0351685,\n" +
                    "              31.6901977\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              \"C\",\n" +
                    "              89.0351685,\n" +
                    "              16.9836852,\n" +
                    "              85.3920064,\n" +
                    "              4.51869429,\n" +
                    "              80.9211299,\n" +
                    "              4.51869429\n" +
                    "            ]\n" +
                    "          ]"));
//            p1.setFillColor("#b3c1e6");
//            p1.setOpacity(0.5f);
//            components.add(p1);
////            c.addComponent(p1);
//
//            Path p2 = new Path();
//            p2.setPathPoints(new JSONArray("[[\"M\",0,0],[\"L\",0,400],[\"L\",200,400],[\"L\",200,0]]"));
//            p2.setTop(42.06f);
//            p2.setLeft(45.69f);
//            p2.setControls(true);
//            p2.setFillColor("#b3c1e6");
//            components.add(p2);
//            c.addComponent(p2);
//
//            Ellipse e = new Ellipse();
//            e.setTop(0);
//            e.setLeft(0);
//            e.setRx(150);
//            e.setRy(100);
//            e.setFillColor("#ff0000");
//            e.setOpacity(0.5f);
//            components.add(e);

            Circle c = new Circle();
            c.setTop(0);
            c.setLeft(0);
            c.setRadius(100);
            c.setFillColor("#00ff00");
            c.setOpacity(0.5f);
            componentGroup.addComponent(c);

            Rectangle r = new Rectangle();
            r.setTop(0);
            r.setLeft(0);
            r.setWidth(100);
            r.setHeight(100);
//            r.setAngle(45);
            r.setFillColor("#00ffff");
            r.setOpacity(0.5f);
            r.setControls(true);
            componentGroup.addComponent(r);
//            components.add(r);

            components.add(componentGroup);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        canvasView.setComponents(components);
    }

    private void parseJSONArray(List<Component> components) throws JSONException {
        JSONArray array = new JSONArray("[\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 0,\n" +
                "    \"left\": 0,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            119.5,\n" +
                "            30.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            94.5,\n" +
                "            30.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            94.5,\n" +
                "            49.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            119.5,\n" +
                "            49.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            119.5,\n" +
                "            30.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -19.64,\n" +
                "        \"left\": 34,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-94.5,\\\"offsetY\\\":-30.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":31.7285822997,\\\"x1\\\":107,\\\"y2\\\":42.759743255000004,\\\"x2\\\":107}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            119.5,\n" +
                "            60.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            94.5,\n" +
                "            60.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            94.5,\n" +
                "            79.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            119.5,\n" +
                "            79.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            119.5,\n" +
                "            60.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": 10.36,\n" +
                "        \"left\": 34,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-94.5,\\\"offsetY\\\":-60.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":61.7285822997,\\\"x1\\\":107,\\\"y2\\\":72.759743255,\\\"x2\\\":107}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            10.1910289,\n" +
                "            0.68061213\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.0372086,\n" +
                "            17.0952369\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            108.335487,\n" +
                "            17.0952369\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            91.227338,\n" +
                "            0.68061213\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            10.1910289,\n" +
                "            0.68061213\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -49.46,\n" +
                "        \"left\": -50.31,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-10.1910289,\\\"offsetY\\\":-0.68061213,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":1.7420183115073256,\\\"x1\\\":59.26325795,\\\"y2\\\":11.272142935650706,\\\"x2\\\":59.26325795}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            9.47939776,\n" +
                "            1.3467575\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            9.47939776,\n" +
                "            80.7954903\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            26.449456,\n" +
                "            97.9400156\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            26.449456,\n" +
                "            17.6206167\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            9.47939776,\n" +
                "            1.3467575\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -48.8,\n" +
                "        \"left\": -51.02,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-9.47939776,\\\"offsetY\\\":-1.3467575,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":7.592692614316507,\\\"x1\\\":17.964426879999998,\\\"y2\\\":63.67352299841837,\\\"x2\\\":17.964426879999998}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            20.0663229,\n" +
                "            20.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            0.5,\n" +
                "            20.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            0.5,\n" +
                "            39.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            20.0663229,\n" +
                "            39.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            20.3732942,\n" +
                "            38.4767626\n" +
                "          ],\n" +
                "          [\n" +
                "            \"C\",\n" +
                "            22.0321269,\n" +
                "            32.94732,\n" +
                "            22.0321269,\n" +
                "            27.05268,\n" +
                "            20.3732942,\n" +
                "            21.5232374\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            20.0663229,\n" +
                "            20.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -29.64,\n" +
                "        \"left\": -60,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-0.5,\\\"offsetY\\\":-20.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":21.7285822997,\\\"x1\\\":11.0587093625,\\\"y2\\\":32.759743255000004,\\\"x2\\\":11.0587093625}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            20.0663229,\n" +
                "            50.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            0.5,\n" +
                "            50.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            0.5,\n" +
                "            69.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            20.0663229,\n" +
                "            69.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            20.3732942,\n" +
                "            68.4767626\n" +
                "          ],\n" +
                "          [\n" +
                "            \"C\",\n" +
                "            22.0321269,\n" +
                "            62.94732,\n" +
                "            22.0321269,\n" +
                "            57.05268,\n" +
                "            20.3732942,\n" +
                "            51.5232374\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            20.0663229,\n" +
                "            50.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": 0.36,\n" +
                "        \"left\": -60,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-0.5,\\\"offsetY\\\":-50.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":51.7285822997,\\\"x1\\\":11.0587093625,\\\"y2\\\":62.759743255000004,\\\"x2\\\":11.0587093625}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            27.2941176,\n" +
                "            18.094273\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2941176,\n" +
                "            98.9640503\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.014897,\n" +
                "            98.9640503\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.066079,\n" +
                "            18.094273\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2941176,\n" +
                "            18.094273\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -32.05,\n" +
                "        \"left\": -33.21,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-27.2941176,\\\"offsetY\\\":-18.094273,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":23.323492840603205,\\\"x1\\\":68.1800983,\\\"y2\\\":70.27546809405405,\\\"x2\\\":68.1800983}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -44.99,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 15.5054915,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 32.7097805,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 32.7097805,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -42.79,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 17.7113738,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 34.9156629,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 34.9156629,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -40.58,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 19.9172562,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 37.1215453,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 37.1215453,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -38.38,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 22.1231385,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 39.3274276,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 39.3274276,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -36.17,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 24.3290209,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 41.53331,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 41.53331,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -33.97,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 26.5349032,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 43.7391923,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 43.7391923,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -31.76,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 28.7407856,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 45.9450747,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 45.9450747,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -29.55,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 30.9466679,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 48.150957,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 48.150957,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -27.35,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 33.1525503,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 50.3568394,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 50.3568394,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -25.14,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 35.3584326,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 52.5627217,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 52.5627217,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -22.94,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 37.564315,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 54.7686041,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 54.7686041,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -20.73,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 39.7701973,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 56.9744864,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 56.9744864,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -18.52,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 41.9760797,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 59.1803688,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 59.1803688,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -16.32,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 44.1819621,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 61.3862511,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 61.3862511,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -14.11,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 46.3878444,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 63.5921335,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 63.5921335,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -11.91,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 48.5937268,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 65.7980158,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 65.7980158,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -9.7,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 50.7996091,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 68.0038982,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 68.0038982,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -7.49,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 53.0054915,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 70.2097805,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 70.2097805,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -5.29,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 55.2113738,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 72.4156629,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 72.4156629,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -3.08,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 57.4172562,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 74.6215453,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 74.6215453,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": -0.88,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 59.6231385,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 76.8274276,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 76.8274276,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 1.33,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 61.8290209,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 79.03331,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 79.03331,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 3.53,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 64.0349032,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 81.2391923,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 81.2391923,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 5.74,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 66.2407856,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 83.4450747,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 83.4450747,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 7.95,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 68.4466679,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 85.650957,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 85.650957,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 10.15,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 70.6525503,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 87.8568394,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 87.8568394,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 12.36,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 72.8584326,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 90.0627217,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 90.0627217,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 14.56,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 75.064315,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 92.2686041,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 92.2686041,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 16.77,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 77.2701973,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 94.4744864,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 94.4744864,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 18.98,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 79.4760797,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 96.6803688,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 96.6803688,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 21.18,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 81.6819621,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 98.8862511,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 98.8862511,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 23.39,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 83.8878444,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 101.092133,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 101.092133,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"top\": -49.82,\n" +
                "        \"left\": 25.59,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 86.0937268,\n" +
                "            \"y\": 0.322334382\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 103.298016,\n" +
                "            \"y\": 17.5266235\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 103.298016,\n" +
                "            \"y\": 98.9412857\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            27.2781249,\n" +
                "            36.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2781249,\n" +
                "            42.3368302\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.022554,\n" +
                "            42.3368302\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.022554,\n" +
                "            36.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2781249,\n" +
                "            36.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -13.64,\n" +
                "        \"left\": -33.22,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-27.2781249,\\\"offsetY\\\":-36.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":36.87742243526707,\\\"x1\\\":68.15033944999999,\\\"y2\\\":40.26621261447528,\\\"x2\\\":68.15033944999999}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            27.2781249,\n" +
                "            56.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2781249,\n" +
                "            62.3368302\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.022554,\n" +
                "            62.3368302\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.022554,\n" +
                "            56.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2781249,\n" +
                "            56.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": 6.36,\n" +
                "        \"left\": -33.22,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-27.2781249,\\\"offsetY\\\":-56.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":56.87742243526707,\\\"x1\\\":68.15033944999999,\\\"y2\\\":60.26621261447528,\\\"x2\\\":68.15033944999999}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            27.2781249,\n" +
                "            76.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2781249,\n" +
                "            82.3368302\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.022554,\n" +
                "            82.3368302\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            109.022554,\n" +
                "            76.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            27.2781249,\n" +
                "            76.5\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": 26.36,\n" +
                "        \"left\": -33.22,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"{\\\"offsetX\\\":-27.2781249,\\\"offsetY\\\":-76.5,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":76.87742243526708,\\\"x1\\\":68.15033944999999,\\\"y2\\\":80.26621261447528,\\\"x2\\\":68.15033944999999}}\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.91,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 230,\n" +
                "    \"left\": 60,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 198.88,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#612B9B\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.89,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 230,\n" +
                "    \"left\": 240,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 89.75,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#C95DB4\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.86,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 320,\n" +
                "    \"left\": 70,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 208.89,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#612B9B\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.79,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 320,\n" +
                "    \"left\": 246.83,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 104.75,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#C95DB4\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 250,\n" +
                "    \"left\": 740,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 213.87,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#612B9B\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.89,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 360,\n" +
                "    \"left\": 950,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 89.75,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#C95DB4\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.94,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 360,\n" +
                "    \"left\": 740,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 223.87,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#612B9B\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 0,\n" +
                "    \"scaleX\": 0.89,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"top\": 250,\n" +
                "    \"left\": 950,\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"scaleX\": 1,\n" +
                "        \"scaleY\": 0.5,\n" +
                "        \"top\": 0,\n" +
                "        \"left\": 0,\n" +
                "        \"width\": 89.75,\n" +
                "        \"angle\": 0,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#C95DB4\",\n" +
                "        \"stroke\": \"\",\n" +
                "        \"height\": 56.95\n" +
                "      }\n" +
                "    ],\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"group\",\n" +
                "    \"fill\": \"rgb(0,0,0)\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 130,\n" +
                "    \"left\": 100,\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 230,\n" +
                "    \"left\": 320,\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        0,\n" +
                "        8.92943201,\n" +
                "        0,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 260,\n" +
                "    \"left\": 450,\n" +
                "    \"angle\": 90,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 350,\n" +
                "    \"left\": 410,\n" +
                "    \"angle\": 180,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 400,\n" +
                "    \"left\": 210,\n" +
                "    \"angle\": 180,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 280,\n" +
                "    \"left\": 760,\n" +
                "    \"angle\": 180,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        0,\n" +
                "        8.92943201,\n" +
                "        0,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 270,\n" +
                "    \"left\": 680,\n" +
                "    \"angle\": 90,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 360,\n" +
                "    \"left\": 670,\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 430,\n" +
                "    \"left\": 860,\n" +
                "    \"angle\": 0,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"strokeWidth\": 1,\n" +
                "    \"scaleX\": 1,\n" +
                "    \"scaleY\": 1,\n" +
                "    \"path\": [\n" +
                "      [\n" +
                "        \"M\",\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        71.0864516,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.32,\n" +
                "        -0.185886403,\n" +
                "        69.0425806,\n" +
                "        -0.185886403,\n" +
                "        68.276129,\n" +
                "        0.557659208\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        1.30120482,\n" +
                "        67.5096774,\n" +
                "        2.5404475,\n" +
                "        68.276129,\n" +
                "        3.28399312\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        1.98709677,\n" +
                "        8.0757315\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        0.88,\n" +
                "        8.0757315,\n" +
                "        1.1354806e-13,\n" +
                "        8.92943201,\n" +
                "        1.1354806e-13,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        1.1354806e-13,\n" +
                "        11.0774527,\n" +
                "        0.88,\n" +
                "        11.9311532,\n" +
                "        1.98709677,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        73.1870968,\n" +
                "        11.9311532\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        68.276129,\n" +
                "        16.6953528\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        67.5096774,\n" +
                "        17.4388985,\n" +
                "        67.5096774,\n" +
                "        18.6781411,\n" +
                "        68.276129,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        68.6735484,\n" +
                "        19.8072289,\n" +
                "        69.1845161,\n" +
                "        20,\n" +
                "        69.6954839,\n" +
                "        20\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        70.2064516,\n" +
                "        20,\n" +
                "        70.7174194,\n" +
                "        19.8072289,\n" +
                "        71.1148387,\n" +
                "        19.4216867\n" +
                "      ],\n" +
                "      [\n" +
                "        \"L\",\n" +
                "        79.403871,\n" +
                "        11.3803787\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        79.7729032,\n" +
                "        11.0223752,\n" +
                "        80,\n" +
                "        10.5266781,\n" +
                "        80,\n" +
                "        10.0034423\n" +
                "      ],\n" +
                "      [\n" +
                "        \"C\",\n" +
                "        80,\n" +
                "        9.48020654,\n" +
                "        79.8012903,\n" +
                "        8.98450947,\n" +
                "        79.403871,\n" +
                "        8.62650602\n" +
                "      ],\n" +
                "      [\n" +
                "        \"Z\"\n" +
                "      ]\n" +
                "    ],\n" +
                "    \"top\": 140,\n" +
                "    \"left\": 910,\n" +
                "    \"angle\": 180,\n" +
                "    \"type\": \"path\",\n" +
                "    \"fill\": \"#F5A623\",\n" +
                "    \"stroke\": \"\"\n" +
                "  }\n" +
                "]");
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            Component component = getObject(jsonObject);

            if (component != null) {
//                if (component instanceof Text) {
//                    continue;
//                }
                components.add(component);
            }
        }
    }

    private Component getObject(JSONObject jsonObject) throws JSONException {
        Component component = null;
        String type = jsonObject.getString("type");
        switch (type) {
            case "group": {
                component = new ComponentGroup();

                ComponentGroup componentGroup = (ComponentGroup) component;
                JSONArray objects = jsonObject.getJSONArray("objects");
                for (int i = 0; i < objects.length(); i++) {
                    JSONObject object = objects.getJSONObject(i);
                    Component com = getObject(object);
//                    if (com instanceof Polyline) {
//                        continue;
//                    }
                    componentGroup.addComponent(com);
                }
                break;
            }

            case "polygon": {
                component = new Polygon();
                Polygon polygon = (Polygon) component;
                JSONArray points = jsonObject.getJSONArray("points");
                for (int i = 0; i < points.length(); i++) {
                    JSONObject point = points.getJSONObject(i);
                    polygon.addPoint(new Point((float) point.getDouble("x"), (float) point.getDouble("y")));
                }
                break;
            }

            case "path": {
                component = new Path();
                Path path = (Path) component;
                path.setPathPoints(jsonObject.getJSONArray("path"));
                break;
            }

            case "circle": {
                component = new Circle();
                Circle circle = (Circle) component;
                circle.setRadius((float) jsonObject.getDouble("radius"));

                break;
            }

            case "rect": {
                component = new Rectangle();
                Rectangle rectangle = (Rectangle) component;
                rectangle.setWidth((float) jsonObject.getDouble("width"));
                rectangle.setHeight((float) jsonObject.getDouble("height"));

                break;
            }

            case "ellipse": {
                component = new Ellipse();
                Ellipse ellipse = (Ellipse) component;
                ellipse.setRx((float) jsonObject.getDouble("rx"));
                ellipse.setRy((float) jsonObject.getDouble("ry"));

                ellipse.setWidth((float) jsonObject.getDouble("width"));
                ellipse.setHeight((float) jsonObject.getDouble("height"));

                break;
            }

            case "polyline": {
                component = new Polyline();
                Polyline polyline = (Polyline) component;

                JSONArray points = jsonObject.getJSONArray("points");
                polyline.setPoints(points);
                break;
            }

            case "text": {
                component = new Text();
                Text text = (Text) component;
                text.setText(jsonObject.getString("text"));
                text.setTextSize((float) jsonObject.optDouble("fontSize"));
                break;
            }
        }
        if (component == null) {
            throw new IllegalArgumentException("unknown type: " + type);
//            return null;
        }
        component.setTop((float) jsonObject.getDouble("top"));
        component.setLeft((float) jsonObject.getDouble("left"));
        component.setFillColor(jsonObject.optString("fill"));
        component.setStrokeColor(jsonObject.optString("stroke"));
        component.setStrokeWidth((float) jsonObject.optDouble("strokeWidth"));
        component.setAngle(jsonObject.optDouble("angle", 0f));
        component.setScaleX(jsonObject.optDouble("scaleX", 1f));
        component.setScaleY(jsonObject.optDouble("scaleY", 1f));
        return component;
    }

    private void test(List<Component> components) {
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

        Path circlePath = new Path();
        circlePath.setLeft(120);
        circlePath.setTop(30);
        circlePath.addPoint(new MovePathPoint(0, 50));
        circlePath.addPoint(new CubicPathPoint(0, 0, 100, 0, 100, 50));
//        circlePath.addPathPoint(new CubicPathPoint(0, 50, 20, 20, 0, 50));
        circlePath.setFillColor("#ff00ff");
        circlePath.setOpacity(0.4f);
        components.add(circlePath);

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
        r.setTop(0);
        r.setWidth(180);
        r.setHeight(50);
        r.setFillColor("#0000ff");
        r.setOpacity(0.5f);
        componentGroup.addComponent(r);
        components.add(componentGroup);

        ComponentGroup test = new ComponentGroup();
        test.setLeft(60);
        test.setTop(230);

        Polygon p2 = new Polygon();
        p2.setLeft(0f);
        p2.setTop(0f);
        try {
            JSONArray p1Array = new JSONArray(" [{\"x\":23.4459314,\"y\":1.11022302e-16},{\"x\":1,\"y\":30},{\"x\":48.0893011,\"y\":30},{\"x\":48.0893011,\"y\":1.11022302e-16}]");
            p2.setPoints(p1Array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        p2.setFillColor("#2a3a47");
        p2.setOpacity(0.4f);
        test.addComponent(p2);

        Polygon p1 = new Polygon();
        p1.setLeft(0f);
        p1.setTop(0f);
        try {
            JSONArray p1Array = new JSONArray("[{\"x\":48.0605918,\"y\":0},{\"x\":48.0605918,\"y\":77.2625973},{\"x\":23,\"y\":77.2625973},{\"x\":23,\"y\":30.0869141},{\"x\":1,\"y\":30.0869141},{\"x\":23,\"y\":0}]");
            p1.setPoints(p1Array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        p1.setFillColor("#415f76");
        test.addComponent(p1);

        Path p3 = new Path();
        p3.setLeft(0);
        p3.setTop(0);
        try {
            p3.setPathPoints(new JSONArray("[[\"M\",47.9824677,100],[\"L\",47.9824677,77],[\"L\",22.8651008,77],[\"L\",1.6950237,98.294976],[\"C\",1.30564971,98.6866472,1.30751198,99.3198095,1.69918319,99.7091835],[\"C\",1.88654765,99.895449,2.14000972,100,2.40420717,100],[\"L\",47.9824677,100],[\"Z\"]]"));
        } catch (JSONException ex) {}
        p3.setFillColor("#6a889f");
        test.addComponent(p3);
        components.add(test);


        ComponentGroup g = new ComponentGroup();
        g.setTop(280);
        g.setLeft(260);
        Polygon p = new Polygon();
        p.setLeft(-28.03f);
        p.setTop(-50.5f);
        p.addPoint(new Point(99.0605918f, 0));
        p.addPoint(new Point(99.0605918f, 77.2625973f));
        p.addPoint(new Point(22, 77.2625973f));
        p.addPoint(new Point(22, 0));
        p.setFillColor("#ff0000");
        g.addComponent(p);

        Path firstPath = new Path();
        firstPath.setLeft(-49.63f);
        firstPath.setTop(26.5f);
        firstPath.addPoint(new MovePathPoint(99, 100));
        firstPath.addPoint(new LinePathPoint(99, 77));
        firstPath.addPoint(new LinePathPoint(21.8651008f, 77));
        firstPath.addPoint(new LinePathPoint(0.695023702f, 98.294976f));
        firstPath.addPoint(new CubicPathPoint(0.305649711f, 98.6866472f, 0.307511978f, 99.3198095f, 0.699183192f, 99.7091835f));
        firstPath.addPoint(new CubicPathPoint(0.886547653f, 99.895449f, 1.14000972f, 100, 1.40420717f, 100));
        firstPath.addPoint(new LinePathPoint(99, 100));
        firstPath.addPoint(new ClosePathPoint());
        firstPath.setFillColor("#00ff00");
        g.addComponent(firstPath);

        Path secondPath = new Path();
        secondPath.setLeft(-49.63f);
        secondPath.setTop(-49.9f);
        secondPath.addPoint(new MovePathPoint(0.404207143f, 0));
        secondPath.addPoint(new CubicPathPoint(14.4613263f,
                10.4298917f,
                21.6599239f,
                0.630286972f,
                22f,
                0.601185771f));
        secondPath.addPoint(new CubicPathPoint(22.3400761f,
                0.572708821f,
                22.4746361f,
                9.75152654f,
                22.4036799f,
                28.1376389f));
        secondPath.addPoint(new LinePathPoint(22.0033763f,
                76.5913291f));
        secondPath.addPoint(new CubicPathPoint(22.0012123f,
                76.8532613f,
                21.8963606f,
                77.1038874f,
                21.7113617f,
                77.289329f));
        secondPath.addPoint(new LinePathPoint(0.404207143f,
                98.6474712f));
        secondPath.addPoint(new LinePathPoint(0.404207143f,
                30));
        secondPath.addPoint(new ClosePathPoint());
        secondPath.setFillColor("#6a889f");
        g.addComponent(secondPath);
        components.add(g);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zoomin:
                canvasView.zoomIn();
                break;

            case R.id.zoomout:
                canvasView.zoomOut();
                break;

            case R.id.left:
                canvasView.left();
                break;

            case R.id.right:
                canvasView.right();
                break;

            case R.id.up:
                canvasView.up();
                break;

            case R.id.down:
                canvasView.down();
                break;
        }
    }
}
