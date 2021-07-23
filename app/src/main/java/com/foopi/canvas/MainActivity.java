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

            Text text1 = new Text();
            text1.setLeft(0);
            text1.setTop(0);
            text1.setTextSize(20);
            text1.setScaleX(3f);
            text1.setText("Hello World!");
            text1.setFillColor("#ff0000");
//            components.add(text1);

            Text text = new Text();
            text.setLeft(0);
            text.setTop(100);
            text.setTextSize(20);
            text.setScaleX(4f);
            text.setText("Hello World!");
            text.setFillColor("#ff0000");
//            components.add(text);

            Circle c = new Circle();
            c.setTop(0);
            c.setLeft(0);
            c.setRadius(100);
            c.setFillColor("#00ff00");
            c.setOpacity(0.5f);
//            componentGroup.addComponent(c);
//            components.add(c);

            Rectangle r = new Rectangle();
            r.setTop(0);
            r.setLeft(0);
            r.setWidth(100);
            r.setHeight(100);
//            r.setAngle(45);
            r.setFillColor("#00ffff");
            r.setOpacity(0.5f);
            r.setControls(true);
            r.setScaleX(3f);
            r.setScaleY(2f);
//            componentGroup.addComponent(r);
//            components.add(r);

//            components.add(componentGroup);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        canvasView.setComponents(components);
    }

    private void parseJSONArray(List<Component> components) throws JSONException {
        String jsonString = new StringBuffer("[\n" +
                "    {\n" +
                "      \"type\": \"group\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 310,\n" +
                "      \"top\": 130,\n" +
                "      \"width\": 220,\n" +
                "      \"height\": 110,\n" +
                "      \"fill\": \"rgb(0,0,0)\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 0,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 2.23,\n" +
                "      \"scaleY\": 2.59,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"uid\": \"com.facilio.graphics.shape.chiller.chiller_2\"\n" +
                "      },\n" +
                "      \"objects\": [\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 4.55,\n" +
                "          \"top\": 29.05,\n" +
                "          \"width\": 53.39,\n" +
                "          \"height\": 6.23,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 141.741728,\n" +
                "              \"y1\": 84.66785030172761,\n" +
                "              \"x2\": 141.741728,\n" +
                "              \"y2\": 88.28338291725869\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -115.049017,\n" +
                "            \"offsetY\": -84.2651747,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              115.049017,\n" +
                "              85.560445\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              117.494788,\n" +
                "              90.4925446\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              168.244727,\n" +
                "              89.6229402\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              168.434439,\n" +
                "              84.2651747\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              115.049017,\n" +
                "              85.560445\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 99,\n" +
                "          \"top\": 25.29,\n" +
                "          \"width\": 10,\n" +
                "          \"height\": 19,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 214.5,\n" +
                "              \"y1\": 81.7285822997,\n" +
                "              \"x2\": 214.5,\n" +
                "              \"y2\": 92.759743255\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -209.5,\n" +
                "            \"offsetY\": -80.5,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              209.5,\n" +
                "              80.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              209.5,\n" +
                "              99.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              219.5,\n" +
                "              99.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              219.5,\n" +
                "              80.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              209.5,\n" +
                "              80.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 99,\n" +
                "          \"top\": -4.71,\n" +
                "          \"width\": 10,\n" +
                "          \"height\": 19,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 214.5,\n" +
                "              \"y1\": 51.7285822997,\n" +
                "              \"x2\": 214.5,\n" +
                "              \"y2\": 62.759743255000004\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -209.5,\n" +
                "            \"offsetY\": -50.5,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              209.5,\n" +
                "              50.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              209.5,\n" +
                "              69.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              219.5,\n" +
                "              69.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              219.5,\n" +
                "              50.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              209.5,\n" +
                "              50.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 68.39,\n" +
                "          \"top\": -38.28,\n" +
                "          \"width\": 38.61,\n" +
                "          \"height\": 91.97,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 198.1934885,\n" +
                "              \"y1\": 22.877543604198735,\n" +
                "              \"x2\": 198.1934885,\n" +
                "              \"y2\": 76.27229599149493\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -178.886073,\n" +
                "            \"offsetY\": -16.9307672,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              178.886073,\n" +
                "              25.601926\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              180.580388,\n" +
                "              85.3887414\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              205.852644,\n" +
                "              108.897542\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              217.500904,\n" +
                "              108.897542\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              212.390561,\n" +
                "              101.281941\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              211.448232,\n" +
                "              49.3903688\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              211.426516,\n" +
                "              48.1945503,\n" +
                "              210.929651,\n" +
                "              47.0565693,\n" +
                "              210.067373,\n" +
                "              46.2277573\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              179.732562,\n" +
                "              17.0702876\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              179.639426,\n" +
                "              16.9807665,\n" +
                "              179.515256,\n" +
                "              16.9307672,\n" +
                "              179.386073,\n" +
                "              16.9307672\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              179.10993,\n" +
                "              16.9307672,\n" +
                "              178.886073,\n" +
                "              17.1546248,\n" +
                "              178.886073,\n" +
                "              17.4307672\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              178.886073,\n" +
                "              25.601926\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 54.67,\n" +
                "          \"top\": -29.75,\n" +
                "          \"width\": 23.72,\n" +
                "          \"height\": 14.18,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 185.9081769968435,\n" +
                "              \"y1\": 27.746680240910997,\n" +
                "              \"x2\": 170.71963406504562,\n" +
                "              \"y2\": 38.085535924155366\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -165.165349,\n" +
                "            \"offsetY\": -25.4681443,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              165.165349,\n" +
                "              25.4681443\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              165.165349,\n" +
                "              39.6445009\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              188.881253,\n" +
                "              39.6445009\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              187.549795,\n" +
                "              35.5619819,\n" +
                "              186.095906,\n" +
                "              32.5460397,\n" +
                "              184.530892,\n" +
                "              30.6017512\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              182.909621,\n" +
                "              28.5875713,\n" +
                "              180.4838,\n" +
                "              26.8743357,\n" +
                "              177.246974,\n" +
                "              25.4681443\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              165.165349,\n" +
                "              25.4681443\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -77.66,\n" +
                "          \"top\": -15.54,\n" +
                "          \"width\": 174.7,\n" +
                "          \"height\": 48.34,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 120.18748466203306,\n" +
                "              \"y1\": 42.80030185231626,\n" +
                "              \"x2\": 120.18748466203306,\n" +
                "              \"y2\": 70.86609280770568\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -32.8355884,\n" +
                "            \"offsetY\": -39.6745081,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              167.371874,\n" +
                "              39.6745081\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              78.2241506,\n" +
                "              41.4232403\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              32.8355884,\n" +
                "              42.9384945\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              38.5085933,\n" +
                "              49.1347066\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              40.1394382,\n" +
                "              88.0148442\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              203.269284,\n" +
                "              84.6286293\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              205.102971,\n" +
                "              80.5367467,\n" +
                "              206.215501,\n" +
                "              77.5262676,\n" +
                "              206.604791,\n" +
                "              75.6158206\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              207.59579,\n" +
                "              70.7524834,\n" +
                "              207.778425,\n" +
                "              65.6475925,\n" +
                "              207.248713,\n" +
                "              61.9973348\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              206.516363,\n" +
                "              56.9506858,\n" +
                "              205.347883,\n" +
                "              52.5344349,\n" +
                "              203.147678,\n" +
                "              48.0922406\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              202.205059,\n" +
                "              46.1891013,\n" +
                "              200.05512,\n" +
                "              43.3754579,\n" +
                "              196.706383,\n" +
                "              39.6745081\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              167.371874,\n" +
                "              39.6745081\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -71.37,\n" +
                "          \"top\": -42.88,\n" +
                "          \"width\": 40.95,\n" +
                "          \"height\": 15.91,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 59.6046704,\n" +
                "              \"y1\": 13.363324953221097,\n" +
                "              \"x2\": 59.6046704,\n" +
                "              \"y2\": 22.599408890349295\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -39.1290244,\n" +
                "            \"offsetY\": -12.3346672,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              39.1290244,\n" +
                "              28.2428382\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              50.2900378,\n" +
                "              28.2428382\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              50.5462132,\n" +
                "              24.6649117,\n" +
                "              53.5300362,\n" +
                "              21.84181,\n" +
                "              57.17323,\n" +
                "              21.84181\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              80.0803164,\n" +
                "              21.84181\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              80.0803164,\n" +
                "              12.3346672\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              51.6290244,\n" +
                "              12.3346672\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              44.725465,\n" +
                "              12.3346672,\n" +
                "              39.1290244,\n" +
                "              17.9311078,\n" +
                "              39.1290244,\n" +
                "              24.8346672\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              39.1290244,\n" +
                "              28.2428382\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 44.61,\n" +
                "          \"top\": -43.21,\n" +
                "          \"width\": 11.79,\n" +
                "          \"height\": 30.71,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 165.42562717779154,\n" +
                "              \"y1\": 16.941398207836315,\n" +
                "              \"x2\": 157.87212913489824,\n" +
                "              \"y2\": 39.33594386537596\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -155.109897,\n" +
                "            \"offsetY\": -12.0059606,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              155.109897,\n" +
                "              12.0059606\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              155.109897,\n" +
                "              42.7127501\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              166.904184,\n" +
                "              42.7127501\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              166.904184,\n" +
                "              18.0586841\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              160.02531,\n" +
                "              12.0059606\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              155.109897,\n" +
                "              12.0059606\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"ellipse\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -99.36,\n" +
                "          \"top\": -12.12,\n" +
                "          \"width\": 16.13,\n" +
                "          \"height\": 44,\n" +
                "          \"fill\": \"#C5D0E2\",\n" +
                "          \"stroke\": \"\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": -7,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"rx\": 8.06666667,\n" +
                "          \"ry\": 22\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -92.25,\n" +
                "          \"top\": -54.71,\n" +
                "          \"width\": 137.45,\n" +
                "          \"height\": 49.16,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 86.9759613,\n" +
                "              \"y1\": 3.6832679887510595,\n" +
                "              \"x2\": 86.9759613,\n" +
                "              \"y2\": 32.22547289475127\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -18.2513366,\n" +
                "            \"offsetY\": -0.504414205,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              18.2513366,\n" +
                "              27.2786982\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              33.2455597,\n" +
                "              42.6788581\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              78.4445409,\n" +
                "              41.2651287\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              87.8003704,\n" +
                "              49.6653226\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              155.700586,\n" +
                "              48.2434373\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              155.700586,\n" +
                "              9.55014697\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              147.923302,\n" +
                "              0.504414205\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              78.7597235,\n" +
                "              1.82893097\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              78.7597235,\n" +
                "              27.2786982\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              18.2513366,\n" +
                "              27.2786982\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -31.51,\n" +
                "          \"top\": -28.22,\n" +
                "          \"width\": 0.04,\n" +
                "          \"height\": 14.48,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              78.9926301,\n" +
                "              26.9980503\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              79.0301087,\n" +
                "              41.4826328\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"polyline\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 3.39,\n" +
                "          \"top\": -54.02,\n" +
                "          \"width\": 5.7,\n" +
                "          \"height\": 47.35,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"points\": [\n" +
                "            {\n" +
                "              \"x\": 113.894531,\n" +
                "              \"y\": 1.1902374\n" +
                "            },\n" +
                "            {\n" +
                "              \"x\": 119.591263,\n" +
                "              \"y\": 9.83612118\n" +
                "            },\n" +
                "            {\n" +
                "              \"x\": 119.591263,\n" +
                "              \"y\": 48.539587\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -60.53,\n" +
                "          \"top\": 31.58,\n" +
                "          \"width\": 54.64,\n" +
                "          \"height\": 4.6,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 77.2855615,\n" +
                "              \"y1\": 87.09473807238281,\n" +
                "              \"x2\": 77.2855615,\n" +
                "              \"y2\": 89.7641933288624\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -49.967328,\n" +
                "            \"offsetY\": -86.7974307,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              49.967328,\n" +
                "              87.7615988\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              52.1784283,\n" +
                "              91.3952831\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              104.603795,\n" +
                "              90.5971294\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              104.603795,\n" +
                "              86.7974307\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              49.967328,\n" +
                "              87.7615988\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -110,\n" +
                "          \"top\": -24.71,\n" +
                "          \"width\": 12.98,\n" +
                "          \"height\": 19,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 6.99099815,\n" +
                "              \"y1\": 31.7285822997,\n" +
                "              \"x2\": 6.99099815,\n" +
                "              \"y2\": 42.759743255000004\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -0.5,\n" +
                "            \"offsetY\": -30.5,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              12.8097428,\n" +
                "              30.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              0.5,\n" +
                "              30.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              0.5,\n" +
                "              49.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              13.4819963,\n" +
                "              49.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              12.8097428,\n" +
                "              30.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -110,\n" +
                "          \"top\": 5.29,\n" +
                "          \"width\": 12.98,\n" +
                "          \"height\": 19,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 6.99099815,\n" +
                "              \"y1\": 61.7285822997,\n" +
                "              \"x2\": 6.99099815,\n" +
                "              \"y2\": 72.759743255\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -0.5,\n" +
                "            \"offsetY\": -60.5,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              12.8097428,\n" +
                "              60.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              0.5,\n" +
                "              60.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              0.5,\n" +
                "              79.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              13.4819963,\n" +
                "              79.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              12.8097428,\n" +
                "              60.5\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -104.38,\n" +
                "          \"top\": -34.99,\n" +
                "          \"width\": 42.35,\n" +
                "          \"height\": 88.7,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 27.292084655000004,\n" +
                "              \"y1\": 25.958827165734597,\n" +
                "              \"x2\": 27.292084655000004,\n" +
                "              \"y2\": 77.45783889479497\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.279376385,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(153,172,185)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -6.11516921,\n" +
                "            \"offsetY\": -20.2231866,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": null\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              6.11516921,\n" +
                "              80.3126608\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              36.1081537,\n" +
                "              108.92475\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              48.4690001,\n" +
                "              108.92475\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              42.8452029,\n" +
                "              103.137991\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              42.8452029,\n" +
                "              48.4671336\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              42.8452029,\n" +
                "              47.0968016,\n" +
                "              42.2208046,\n" +
                "              45.8011402,\n" +
                "              41.1489645,\n" +
                "              44.9473437\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              10.2474461,\n" +
                "              20.3320988\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              10.1589222,\n" +
                "              20.2615833,\n" +
                "              10.0490936,\n" +
                "              20.2231866,\n" +
                "              9.93591703,\n" +
                "              20.2231866\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              9.65977466,\n" +
                "              20.2231866,\n" +
                "              9.43591703,\n" +
                "              20.4470442,\n" +
                "              9.43591703,\n" +
                "              20.7231866\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              9.43591703,\n" +
                "              80.3126608\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              6.11516921,\n" +
                "              80.3126608\n" +
                "            ],\n" +
                "            [\n" +
                "              \"Z\"\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"ellipse\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -94.87,\n" +
                "          \"top\": -11.68,\n" +
                "          \"width\": 15.13,\n" +
                "          \"height\": 43,\n" +
                "          \"fill\": {\n" +
                "            \"type\": \"linear\",\n" +
                "            \"coords\": {\n" +
                "              \"x1\": 31.744400346276095,\n" +
                "              \"y1\": 23.038137596865237,\n" +
                "              \"x2\": 18.25433113,\n" +
                "              \"y2\": 22.8009821312941\n" +
                "            },\n" +
                "            \"colorStops\": [\n" +
                "              {\n" +
                "                \"offset\": 1,\n" +
                "                \"color\": \"rgb(219,225,230)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0.585423968,\n" +
                "                \"color\": \"rgb(179,193,203)\",\n" +
                "                \"opacity\": 1\n" +
                "              },\n" +
                "              {\n" +
                "                \"offset\": 0,\n" +
                "                \"color\": \"rgb(109,125,136)\",\n" +
                "                \"opacity\": 1\n" +
                "              }\n" +
                "            ],\n" +
                "            \"offsetX\": -18.25433113,\n" +
                "            \"offsetY\": -42.3857614,\n" +
                "            \"gradientUnits\": \"pixels\",\n" +
                "            \"gradientTransform\": [\n" +
                "              1,\n" +
                "              0,\n" +
                "              0,\n" +
                "              2.8414096903782338,\n" +
                "              0,\n" +
                "              0\n" +
                "            ]\n" +
                "          },\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": -7,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"rx\": 7.56666667,\n" +
                "          \"ry\": 21.5\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 9.04,\n" +
                "          \"top\": -6.94,\n" +
                "          \"width\": 5.19,\n" +
                "          \"height\": 38.15,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": -4,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              120.901618,\n" +
                "              48.0068252\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              123.557182,\n" +
                "              52.6761074,\n" +
                "              125.073123,\n" +
                "              56.9155719,\n" +
                "              125.568781,\n" +
                "              60.7148849\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              126.018898,\n" +
                "              64.1651191,\n" +
                "              126.486005,\n" +
                "              69.4905089,\n" +
                "              125.568781,\n" +
                "              75.3958878\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              125.198199,\n" +
                "              77.7818123,\n" +
                "              124.213775,\n" +
                "              81.3677932,\n" +
                "              122.615509,\n" +
                "              86.1538304\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -44.21,\n" +
                "          \"top\": -13.31,\n" +
                "          \"width\": 9.59,\n" +
                "          \"height\": 45.52,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": -4,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              67.9041982,\n" +
                "              41.4759666\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              73.2215566,\n" +
                "              48.8677336,\n" +
                "              76.2416887,\n" +
                "              55.3342215,\n" +
                "              76.9645946,\n" +
                "              60.8754303\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              77.414712,\n" +
                "              64.3256645,\n" +
                "              77.8818186,\n" +
                "              69.6510543,\n" +
                "              76.9645946,\n" +
                "              75.5564332\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              76.5940126,\n" +
                "              77.9423577,\n" +
                "              75.553486,\n" +
                "              81.7562494,\n" +
                "              73.8430147,\n" +
                "              86.9981082\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": 60.58,\n" +
                "          \"top\": -15.33,\n" +
                "          \"width\": 8.8,\n" +
                "          \"height\": 45.72,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": -4,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              172.696975,\n" +
                "              39.4857016\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              177.487223,\n" +
                "              48.1889597,\n" +
                "              180.2438,\n" +
                "              55.3111932,\n" +
                "              180.966706,\n" +
                "              60.8524021\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              181.416823,\n" +
                "              64.3026363,\n" +
                "              181.88393,\n" +
                "              69.628026,\n" +
                "              180.966706,\n" +
                "              75.533405\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              180.596124,\n" +
                "              77.9193295,\n" +
                "              179.674761,\n" +
                "              81.1440721,\n" +
                "              178.202617,\n" +
                "              85.2076328\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -51.46,\n" +
                "          \"top\": -27.66,\n" +
                "          \"width\": 7.44,\n" +
                "          \"height\": 14.32,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              59.039463,\n" +
                "              27.5496674\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              62.6982502,\n" +
                "              30.2828632,\n" +
                "              64.8987937,\n" +
                "              32.5217319,\n" +
                "              65.6410936,\n" +
                "              34.2662736\n" +
                "            ],\n" +
                "            [\n" +
                "              \"C\",\n" +
                "              66.3833934,\n" +
                "              36.0108153,\n" +
                "              66.6301863,\n" +
                "              38.5438278,\n" +
                "              66.3814721,\n" +
                "              41.8653112\n" +
                "            ]\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"polyline\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -31.58,\n" +
                "          \"top\": -53.13,\n" +
                "          \"width\": 76.3,\n" +
                "          \"height\": 8.79,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"points\": [\n" +
                "            {\n" +
                "              \"x\": 78.916274,\n" +
                "              \"y\": 2.08552275\n" +
                "            },\n" +
                "            {\n" +
                "              \"x\": 88.195073,\n" +
                "              \"y\": 10.8731982\n" +
                "            },\n" +
                "            {\n" +
                "              \"x\": 155.216517,\n" +
                "              \"y\": 9.42106601\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"path\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"left\",\n" +
                "          \"originY\": \"top\",\n" +
                "          \"left\": -22.3,\n" +
                "          \"top\": -44.52,\n" +
                "          \"width\": 0.26,\n" +
                "          \"height\": 38.8,\n" +
                "          \"fill\": \"\",\n" +
                "          \"stroke\": \"#474747\",\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"evenodd\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"path\": [\n" +
                "            [\n" +
                "              \"M\",\n" +
                "              88.461225,\n" +
                "              10.6953428\n" +
                "            ],\n" +
                "            [\n" +
                "              \"L\",\n" +
                "              88.2003278,\n" +
                "              49.4997613\n" +
                "            ]\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 220,\n" +
                "      \"top\": 350,\n" +
                "      \"width\": 31.64,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"--\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"${currentAsset.reading.condenserWaterFlowStatus}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k694q1znav03d\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 540,\n" +
                "      \"top\": 100,\n" +
                "      \"width\": 62.32,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" On\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.runStatus}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7eb96riduc1u\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        },\n" +
                "        \"conditionalFormatting\": [\n" +
                "          {\n" +
                "            \"criteria\": {\n" +
                "              \"pattern\": \"(1)\",\n" +
                "              \"conditions\": {\n" +
                "                \"1\": {}\n" +
                "              },\n" +
                "              \"resourceOperator\": false\n" +
                "            },\n" +
                "            \"actions\": {\n" +
                "              \"blink\": false,\n" +
                "              \"hide\": false,\n" +
                "              \"animateEffect\": \"none\",\n" +
                "              \"styles\": {\n" +
                "                \"fill\": \"#0C8412\",\n" +
                "                \"backgroundColor\": \"\",\n" +
                "                \"fontWeight\": \"\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 560,\n" +
                "      \"top\": 70,\n" +
                "      \"width\": 40.56,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" --\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.runCommand}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7eb9t4iij6wj\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 250,\n" +
                "      \"top\": 230,\n" +
                "      \"width\": 157.48,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" 86.54 °F\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.condenserEnteringWaterTemperature}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebcwmogmyie\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 250,\n" +
                "      \"top\": 300,\n" +
                "      \"width\": 157.48,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" 92.41 °F\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.condenserLeavingWaterTemperature}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebdkdticjae\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 210,\n" +
                "      \"top\": 390,\n" +
                "      \"width\": 40.56,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" --\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.condenservalveprotection}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebhfksw3sf2\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 580,\n" +
                "      \"top\": 400,\n" +
                "      \"width\": 40.56,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" --\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.tripStatus}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebil1skimj2\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        },\n" +
                "        \"conditionalFormatting\": [\n" +
                "          {\n" +
                "            \"criteria\": {\n" +
                "              \"pattern\": \"(1)\",\n" +
                "              \"conditions\": {\n" +
                "                \"1\": {\n" +
                "                  \"fieldName\": \"currentAsset.reading.tripStatus\",\n" +
                "                  \"value\": \"true\",\n" +
                "                  \"operatorId\": 15,\n" +
                "                  \"isResourceOperator\": false,\n" +
                "                  \"parseLabel\": null,\n" +
                "                  \"valueArray\": [],\n" +
                "                  \"operatorLabel\": \"is\",\n" +
                "                  \"active\": true,\n" +
                "                  \"operatorsDataType\": {\n" +
                "                    \"dataType\": \"BOOLEAN\",\n" +
                "                    \"displayType\": \"TEXTBOX\"\n" +
                "                  },\n" +
                "                  \"isSpacePicker\": false\n" +
                "                }\n" +
                "              },\n" +
                "              \"resourceOperator\": false\n" +
                "            },\n" +
                "            \"actions\": {\n" +
                "              \"blink\": true,\n" +
                "              \"hide\": false,\n" +
                "              \"animateEffect\": \"none\",\n" +
                "              \"styles\": {\n" +
                "                \"fill\": \"#327014\",\n" +
                "                \"backgroundColor\": \"\",\n" +
                "                \"fontWeight\": \"\"\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 1020,\n" +
                "      \"top\": 260,\n" +
                "      \"width\": 157.48,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" 41.45 °F\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.chilledWaterSupplyTemperature}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebjiqt8w84x\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 940,\n" +
                "      \"top\": 300,\n" +
                "      \"width\": 157.48,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" 46.18 °F\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.chilledWaterReturn}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebjoqn8tmaz\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 970,\n" +
                "      \"top\": 350,\n" +
                "      \"width\": 40.56,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" --\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.chilledWaterFlowStatus}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebkub5scz5y\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 240,\n" +
                "      \"top\": 170,\n" +
                "      \"width\": 40.56,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" --\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.condenserButterflyValveCommand}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebmchr5ixx2\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 960,\n" +
                "      \"top\": 200,\n" +
                "      \"width\": 31.64,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"--\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"${currentAsset.reading.chilledWaterValveOpenStatus}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebnj2rxxca5\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 1030,\n" +
                "      \"top\": 390,\n" +
                "      \"width\": 31.64,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#0c0c0c\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"--\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"${currentAsset.reading.evaporatorButterflyValveCommand}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k7ebov5xkjv4x\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 890,\n" +
                "      \"top\": 150,\n" +
                "      \"width\": 109,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#F1AC3C\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \" Open\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \" ${currentAsset.reading.butterflyvalvestatus}\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k92dbeb0yk422\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#F1AC3C\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 470,\n" +
                "      \"top\": 70,\n" +
                "      \"width\": 277.52,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#2E52AD\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Run Command:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Run Command:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k97zz0h2hmiu2\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#2E52AD\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 470,\n" +
                "      \"top\": 100,\n" +
                "      \"width\": 206.4,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#2E52AD\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Run Status:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Run Status:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k97zzzet8gozz\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#2E52AD\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"group\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 580,\n" +
                "      \"top\": 60,\n" +
                "      \"width\": 39.17,\n" +
                "      \"height\": 28.97,\n" +
                "      \"fill\": \"rgb(0,0,0)\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 0,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 1.02,\n" +
                "      \"scaleY\": 1.04,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableButtonBinding\": true,\n" +
                "        \"type\": \"button\",\n" +
                "        \"formatText\": \"Set\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k98018g6wp7fc\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\",\n" +
                "          \"fontColor\": \"#FFFFFF\",\n" +
                "          \"backgroundColor\": \"#C95DB4\",\n" +
                "          \"padding\": 10,\n" +
                "          \"radius\": 5\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"objects\": [\n" +
                "        {\n" +
                "          \"type\": \"rect\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"center\",\n" +
                "          \"originY\": \"center\",\n" +
                "          \"left\": 0,\n" +
                "          \"top\": 0,\n" +
                "          \"width\": 39.17,\n" +
                "          \"height\": 36.95,\n" +
                "          \"fill\": \"#C95DB4\",\n" +
                "          \"stroke\": null,\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 0.5,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"nonzero\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"rx\": 5,\n" +
                "          \"ry\": 5,\n" +
                "          \"fgraphic\": {\n" +
                "            \"id\": \"button_rect\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"center\",\n" +
                "          \"originY\": \"center\",\n" +
                "          \"left\": 0,\n" +
                "          \"top\": 0,\n" +
                "          \"width\": 19.17,\n" +
                "          \"height\": 16.95,\n" +
                "          \"fill\": \"#FFFFFF\",\n" +
                "          \"stroke\": null,\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"nonzero\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"text\": \"Set\",\n" +
                "          \"fontSize\": 15,\n" +
                "          \"fontWeight\": \"normal\",\n" +
                "          \"fontFamily\": \"Times New Roman\",\n" +
                "          \"fontStyle\": \"normal\",\n" +
                "          \"lineHeight\": 1.16,\n" +
                "          \"underline\": false,\n" +
                "          \"overline\": false,\n" +
                "          \"linethrough\": false,\n" +
                "          \"textAlign\": \"left\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"charSpacing\": 0,\n" +
                "          \"fgraphic\": {\n" +
                "            \"id\": \"button_text\"\n" +
                "          },\n" +
                "          \"styles\": {}\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 30,\n" +
                "      \"top\": 170,\n" +
                "      \"width\": 682.84,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#EE8171\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Condenser Butterfly Valve Command:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Condenser Butterfly Valve Command:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k9808k970u2lt\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#EE8171\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"group\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 260,\n" +
                "      \"top\": 160,\n" +
                "      \"width\": 39.17,\n" +
                "      \"height\": 28.97,\n" +
                "      \"fill\": \"rgb(0,0,0)\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 0,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 1.02,\n" +
                "      \"scaleY\": 1.04,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableButtonBinding\": true,\n" +
                "        \"type\": \"button\",\n" +
                "        \"formatText\": \"Set\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k9809mfojavzp\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\",\n" +
                "          \"fontColor\": \"#FFFFFF\",\n" +
                "          \"backgroundColor\": \"#C95DB4\",\n" +
                "          \"padding\": 10,\n" +
                "          \"radius\": 5\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"objects\": [\n" +
                "        {\n" +
                "          \"type\": \"rect\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"center\",\n" +
                "          \"originY\": \"center\",\n" +
                "          \"left\": 0,\n" +
                "          \"top\": 0,\n" +
                "          \"width\": 39.17,\n" +
                "          \"height\": 36.95,\n" +
                "          \"fill\": \"#C95DB4\",\n" +
                "          \"stroke\": null,\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 0.5,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"nonzero\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"rx\": 5,\n" +
                "          \"ry\": 5,\n" +
                "          \"fgraphic\": {\n" +
                "            \"id\": \"button_rect\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"center\",\n" +
                "          \"originY\": \"center\",\n" +
                "          \"left\": 0,\n" +
                "          \"top\": 0,\n" +
                "          \"width\": 19.17,\n" +
                "          \"height\": 16.95,\n" +
                "          \"fill\": \"#FFFFFF\",\n" +
                "          \"stroke\": null,\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"nonzero\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"text\": \"Set\",\n" +
                "          \"fontSize\": 15,\n" +
                "          \"fontWeight\": \"normal\",\n" +
                "          \"fontFamily\": \"Times New Roman\",\n" +
                "          \"fontStyle\": \"normal\",\n" +
                "          \"lineHeight\": 1.16,\n" +
                "          \"underline\": false,\n" +
                "          \"overline\": false,\n" +
                "          \"linethrough\": false,\n" +
                "          \"textAlign\": \"left\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"charSpacing\": 0,\n" +
                "          \"fgraphic\": {\n" +
                "            \"id\": \"button_text\"\n" +
                "          },\n" +
                "          \"styles\": {}\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 30,\n" +
                "      \"top\": 230,\n" +
                "      \"width\": 730.16,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#1F95DA\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Condenser Entering Water Temperature:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Condenser Entering Water Temperature:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980b1jhh5e3s\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#1F95DA\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 30,\n" +
                "      \"top\": 300,\n" +
                "      \"width\": 720.28,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#1F95DA\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Condenser Leaving Water Temperature:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Condenser Leaving Water Temperature:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980cftfwdizm\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#1F95DA\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 510,\n" +
                "      \"top\": 400,\n" +
                "      \"width\": 203.12,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#EC598C\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Trip Status:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Trip Status:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980eploh4gwy\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#EC598C\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 770,\n" +
                "      \"top\": 150,\n" +
                "      \"width\": 391.68,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#3964C5\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Butterfly valve status:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Butterfly valve status:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980if1f3dl32\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#3964C5\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 770,\n" +
                "      \"top\": 200,\n" +
                "      \"width\": 603.08,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#3964C5\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Chilled Water Valve Open Status: \",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Chilled Water Valve Open Status: \",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980lb3iwtu0a\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#3964C5\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 40,\n" +
                "      \"top\": 350,\n" +
                "      \"width\": 558.56,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#663383\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Condenser Water Flow Status: \",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Condenser Water Flow Status: \",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980mv1onnd1v\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#663383\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 40,\n" +
                "      \"top\": 390,\n" +
                "      \"width\": 517.6,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#663383\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Condenser Valve Protection:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Condenser Valve Protection:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980n07xen30r\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#663383\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"group\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 1050,\n" +
                "      \"top\": 380,\n" +
                "      \"width\": 39.17,\n" +
                "      \"height\": 28.97,\n" +
                "      \"fill\": \"rgb(0,0,0)\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 0,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 1.02,\n" +
                "      \"scaleY\": 1.04,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableButtonBinding\": true,\n" +
                "        \"type\": \"button\",\n" +
                "        \"formatText\": \"Set\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980pes7qqoln\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\",\n" +
                "          \"fontColor\": \"#FFFFFF\",\n" +
                "          \"backgroundColor\": \"#C95DB4\",\n" +
                "          \"padding\": 10,\n" +
                "          \"radius\": 5\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": true,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"objects\": [\n" +
                "        {\n" +
                "          \"type\": \"rect\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"center\",\n" +
                "          \"originY\": \"center\",\n" +
                "          \"left\": 0,\n" +
                "          \"top\": 0,\n" +
                "          \"width\": 39.17,\n" +
                "          \"height\": 36.95,\n" +
                "          \"fill\": \"#C95DB4\",\n" +
                "          \"stroke\": null,\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 0.5,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"nonzero\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"rx\": 5,\n" +
                "          \"ry\": 5,\n" +
                "          \"fgraphic\": {\n" +
                "            \"id\": \"button_rect\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"version\": \"3.6.2\",\n" +
                "          \"originX\": \"center\",\n" +
                "          \"originY\": \"center\",\n" +
                "          \"left\": 0,\n" +
                "          \"top\": 0,\n" +
                "          \"width\": 19.17,\n" +
                "          \"height\": 16.95,\n" +
                "          \"fill\": \"#FFFFFF\",\n" +
                "          \"stroke\": null,\n" +
                "          \"strokeWidth\": 1,\n" +
                "          \"strokeDashArray\": null,\n" +
                "          \"strokeLineCap\": \"butt\",\n" +
                "          \"strokeDashOffset\": 0,\n" +
                "          \"strokeLineJoin\": \"miter\",\n" +
                "          \"strokeMiterLimit\": 4,\n" +
                "          \"scaleX\": 1,\n" +
                "          \"scaleY\": 1,\n" +
                "          \"angle\": 0,\n" +
                "          \"flipX\": false,\n" +
                "          \"flipY\": false,\n" +
                "          \"opacity\": 1,\n" +
                "          \"shadow\": null,\n" +
                "          \"visible\": true,\n" +
                "          \"clipTo\": null,\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fillRule\": \"nonzero\",\n" +
                "          \"paintFirst\": \"fill\",\n" +
                "          \"globalCompositeOperation\": \"source-over\",\n" +
                "          \"transformMatrix\": null,\n" +
                "          \"skewX\": 0,\n" +
                "          \"skewY\": 0,\n" +
                "          \"text\": \"Set\",\n" +
                "          \"fontSize\": 15,\n" +
                "          \"fontWeight\": \"normal\",\n" +
                "          \"fontFamily\": \"Times New Roman\",\n" +
                "          \"fontStyle\": \"normal\",\n" +
                "          \"lineHeight\": 1.16,\n" +
                "          \"underline\": false,\n" +
                "          \"overline\": false,\n" +
                "          \"linethrough\": false,\n" +
                "          \"textAlign\": \"left\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"charSpacing\": 0,\n" +
                "          \"fgraphic\": {\n" +
                "            \"id\": \"button_text\"\n" +
                "          },\n" +
                "          \"styles\": {}\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 820,\n" +
                "      \"top\": 390,\n" +
                "      \"width\": 690.44,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#EC598C\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Evaporator Butterfly Valve Command: \",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Evaporator Butterfly Valve Command: \",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980pq43g6h8n\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#EC598C\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 820,\n" +
                "      \"top\": 260,\n" +
                "      \"width\": 625.44,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#EE8171\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Chilled Water Supply Temperature:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Chilled Water Supply Temperature:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980ra56wbwc4\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#EE8171\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 820,\n" +
                "      \"top\": 350,\n" +
                "      \"width\": 471.84,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#2E52AD\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Chilled Water Flow Status:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Chilled Water Flow Status:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980rkhex38gm\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#2E52AD\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"text\",\n" +
                "      \"version\": \"3.6.2\",\n" +
                "      \"originX\": \"left\",\n" +
                "      \"originY\": \"top\",\n" +
                "      \"left\": 820,\n" +
                "      \"top\": 300,\n" +
                "      \"width\": 380.44,\n" +
                "      \"height\": 45.2,\n" +
                "      \"fill\": \"#EE8171\",\n" +
                "      \"stroke\": null,\n" +
                "      \"strokeWidth\": 1,\n" +
                "      \"strokeDashArray\": null,\n" +
                "      \"strokeLineCap\": \"butt\",\n" +
                "      \"strokeDashOffset\": 0,\n" +
                "      \"strokeLineJoin\": \"miter\",\n" +
                "      \"strokeMiterLimit\": 4,\n" +
                "      \"scaleX\": 0.3,\n" +
                "      \"scaleY\": 0.3,\n" +
                "      \"angle\": 0,\n" +
                "      \"flipX\": false,\n" +
                "      \"flipY\": false,\n" +
                "      \"opacity\": 1,\n" +
                "      \"shadow\": null,\n" +
                "      \"visible\": true,\n" +
                "      \"clipTo\": null,\n" +
                "      \"backgroundColor\": \"\",\n" +
                "      \"fillRule\": \"nonzero\",\n" +
                "      \"paintFirst\": \"fill\",\n" +
                "      \"globalCompositeOperation\": \"source-over\",\n" +
                "      \"transformMatrix\": null,\n" +
                "      \"skewX\": 0,\n" +
                "      \"skewY\": 0,\n" +
                "      \"text\": \"Chilled Water Return:\",\n" +
                "      \"fontSize\": 40,\n" +
                "      \"fontWeight\": \"normal\",\n" +
                "      \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "      \"fontStyle\": \"normal\",\n" +
                "      \"lineHeight\": 1.16,\n" +
                "      \"underline\": false,\n" +
                "      \"overline\": false,\n" +
                "      \"linethrough\": false,\n" +
                "      \"textAlign\": \"left\",\n" +
                "      \"textBackgroundColor\": \"\",\n" +
                "      \"charSpacing\": 0,\n" +
                "      \"fgraphic\": {\n" +
                "        \"enableTextBinding\": true,\n" +
                "        \"formatText\": \"Chilled Water Return:\",\n" +
                "        \"blink\": false,\n" +
                "        \"hide\": false,\n" +
                "        \"animateEffect\": \"none\",\n" +
                "        \"id\": \"k980ud22ytr1f\",\n" +
                "        \"type\": \"text\",\n" +
                "        \"styles\": {\n" +
                "          \"fill\": \"#EE8171\",\n" +
                "          \"textBackgroundColor\": \"\",\n" +
                "          \"backgroundColor\": \"\",\n" +
                "          \"fontFamily\": \"Aktiv-Grotesk\",\n" +
                "          \"textAlign\": \"\",\n" +
                "          \"lineHeight\": null,\n" +
                "          \"charSpacing\": null,\n" +
                "          \"fontWeight\": \"\",\n" +
                "          \"fontStyle\": \"\"\n" +
                "        },\n" +
                "        \"actions\": {\n" +
                "          \"showTrend\": {\n" +
                "            \"enable\": true\n" +
                "          },\n" +
                "          \"controlAction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"control_list\": [\n" +
                "              {\n" +
                "                \"type\": \"control_point\",\n" +
                "                \"actionName\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"assetCategoryId\": null,\n" +
                "                \"fieldId\": null,\n" +
                "                \"pointId\": null,\n" +
                "                \"groupId\": null\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"hyperLink\": {\n" +
                "            \"enable\": false,\n" +
                "            \"link_list\": [\n" +
                "              {\n" +
                "                \"actionName\": null,\n" +
                "                \"linkType\": null,\n" +
                "                \"assetId\": null,\n" +
                "                \"id\": null,\n" +
                "                \"url\": null,\n" +
                "                \"target\": \"_blank\"\n" +
                "              }\n" +
                "            ]\n" +
                "          },\n" +
                "          \"invokeFunction\": {\n" +
                "            \"enable\": false,\n" +
                "            \"function_list\": []\n" +
                "          }\n" +
                "        }\n" +
                "      },\n" +
                "      \"styles\": {}\n" +
                "    }\n" +
                "  ]\n").toString();
        JSONArray array = new JSONArray(jsonString);
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
//            throw new IllegalArgumentException("unknown type: " + type);
            return null;
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
