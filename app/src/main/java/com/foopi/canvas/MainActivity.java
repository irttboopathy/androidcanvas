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

        List<Component> components = new ArrayList<>();

        canvasView.setTotalPartWidth(300, 250);

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
//            parseJSONArray(components);

//            ComponentGroup c = new ComponentGroup();
//            c.setTop(80);
//            c.setLeft(390);
//
//            Path p1 = new Path();
//            p1.setLeft(-48.3f);
//            p1.setTop(16.5f);
//            p1.setPathPoints(new JSONArray("[[\"M\",48.5663229,89.9751023],[\"L\",24.3337401,66.6963207],[\"L\",2.17359617,66.6963207],[\"L\",2.17359617,75.6081971],[\"L\",24.7186931,97.8206515],[\"L\",24.7186931,89.9751023],[\"L\",48.5663229,89.9751023],[\"Z\"]]"));
//            p1.setFillColor("#b3c1e6");
//            p1.setOpacity(0.5f);
//            c.addComponent(p1);

            Path p2 = new Path();
            p2.setPathPoints(new JSONArray("[[\"M\",80.9399304,58.3186943],[\"L\",80.9399304,58.3186943],[\"C\",85.4108069,58.3186943,89.0351685,46.3967103,89.0351685,31.6901977],[\"C\",89.0351685,16.9836852,85.3920064,4.51869429,80.9211299,4.51869429]]"));
            p2.setTop(42.06f);
            p2.setLeft(45.69f);
            p2.setFillColor("#b3c1e6");
            p2.setStrokeColor("#ff0000");
//            c.addComponent(p2);

            Ellipse e = new Ellipse();
            e.setTop(10);
            e.setLeft(10);
            e.setRx(150);
            e.setRy(100);
            e.setFillColor("#ff0000");
            e.setOpacity(0.5f);
            components.add(e);

//            Circle c = new Circle();
//            c.setTop(10);
//            c.setLeft(10);
//            c.setRadius(100);
//            c.setFillColor("#00ff00");
//            c.setOpacity(0.5f);
//            components.add(c);

            Rectangle r = new Rectangle();
            r.setTop(10);
            r.setLeft(10);
            r.setWidth(100);
            r.setHeight(100);
            r.setFillColor("#ff0fff");
            r.setOpacity(0.5f);
            components.add(r);

//            components.add(p2);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        canvasView.setComponents(components);
    }

    private void parseJSONArray(List<Component> components) throws JSONException {
        JSONArray array = new JSONArray("[\n" +
                "  {\n" +
                "    \"top\": 120,\n" +
                "    \"left\": 30,\n" +
                "    \"type\": \"group\",\n" +
                "    \"objects\": [\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"top\": -10.06,\n" +
                "        \"left\": 34.75,\n" +
                "        \"width\": 14,\n" +
                "        \"type\": \"rect\",\n" +
                "        \"fill\": \"#dbe1e6\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"height\": 19\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            61.4328161,\n" +
                "            4.93370075\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            80.4328161,\n" +
                "            4.93370075\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            80.4328161,\n" +
                "            0.575583899\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            61.4328161,\n" +
                "            0.586492402\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            61.4328161,\n" +
                "            4.93370075\n" +
                "          ],\n" +
                "          [\n" +
                "            \"Z\"\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -29.99,\n" +
                "        \"left\": 10.68,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"#dbe1e6\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"top\": -25.7,\n" +
                "        \"left\": 9.8,\n" +
                "        \"type\": \"polyline\",\n" +
                "        \"fill\": \"#dbe1e6\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"points\": [\n" +
                "          {\n" +
                "            \"x\": 81.7657964,\n" +
                "            \"y\": 59.0448563\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 62.3337698,\n" +
                "            \"y\": 58.584823\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 61.9973112,\n" +
                "            \"y\": 4.27983711\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 61.9973112,\n" +
                "            \"y\": 4.27983711\n" +
                "          },\n" +
                "          {\n" +
                "            \"x\": 80.5636301,\n" +
                "            \"y\": 5.25285665\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"path\": [\n" +
                "          [\n" +
                "            \"M\",\n" +
                "            80.9399304,\n" +
                "            58.3186943\n" +
                "          ],\n" +
                "          [\n" +
                "            \"L\",\n" +
                "            80.9399304,\n" +
                "            58.3186943\n" +
                "          ],\n" +
                "          [\n" +
                "            \"C\",\n" +
                "            85.4108069,\n" +
                "            58.3186943,\n" +
                "            89.0351685,\n" +
                "            46.3967103,\n" +
                "            89.0351685,\n" +
                "            31.6901977\n" +
                "          ],\n" +
                "          [\n" +
                "            \"C\",\n" +
                "            89.0351685,\n" +
                "            16.9836852,\n" +
                "            85.3920064,\n" +
                "            4.51869429,\n" +
                "            80.9211299,\n" +
                "            4.51869429\n" +
                "          ]\n" +
                "        ],\n" +
                "        \"top\": -25.66,\n" +
                "        \"left\": 28.27,\n" +
                "        \"type\": \"path\",\n" +
                "        \"fill\": \"#dbe1e6\",\n" +
                "        \"stroke\": \"#474747\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"strokeWidth\": 1,\n" +
                "        \"top\": -24.67,\n" +
                "        \"left\": 2.11,\n" +
                "        \"rx\": 26.6428571,\n" +
                "        \"ry\": 26.6428571,\n" +
                "        \"width\": 15.19,\n" +
                "        \"type\": \"ellipse\",\n" +
                "        \"fill\": \"#dbe1e6\",\n" +
                "        \"stroke\": \"#474747\",\n" +
                "        \"height\": 53.29\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]");
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            Component component = getObject(jsonObject);

            if (component != null) {
                if (component instanceof Text) {
                    continue;
                }
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
                    componentGroup.addComponent(getObject(object));
                }
                break;
            }

            case "polygon": {
                component = new Polygon();
                Polygon polygon = (Polygon) component;
                polygon.setFillColor(jsonObject.getString("fill"));
                polygon.setStrokeColor(jsonObject.getString("stroke"));
                polygon.setStrokeWidth((float) jsonObject.getDouble("strokeWidth"));
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
                path.setFillColor(jsonObject.getString("fill"));
                path.setStrokeColor(jsonObject.getString("stroke"));
                path.setStrokeWidth((float) jsonObject.getDouble("strokeWidth"));
                break;
            }

            case "circle": {
                component = new Circle();
                Circle circle = (Circle) component;
                circle.setRadius((float) jsonObject.getDouble("radius"));
                circle.setFillColor(jsonObject.getString("fill"));
                circle.setStrokeColor(jsonObject.getString("stroke"));
                circle.setStrokeWidth((float) jsonObject.getDouble("strokeWidth"));

                break;
            }

            case "rect": {
                component = new Rectangle();
                Rectangle rectangle = (Rectangle) component;
                rectangle.setWidth((float) jsonObject.getDouble("width"));
                rectangle.setHeight((float) jsonObject.getDouble("height"));
                rectangle.setFillColor(jsonObject.getString("fill"));
                rectangle.setStrokeColor(jsonObject.getString("stroke"));
                rectangle.setStrokeWidth((float) jsonObject.getDouble("strokeWidth"));

                break;
            }

            case "ellipse": {
                component = new Ellipse();
                Ellipse ellipse = (Ellipse) component;
                ellipse.setRx((float) jsonObject.getDouble("rx"));
                ellipse.setRy((float) jsonObject.getDouble("ry"));

                ellipse.setWidth((float) jsonObject.getDouble("width"));
                ellipse.setHeight((float) jsonObject.getDouble("height"));
                ellipse.setFillColor(jsonObject.getString("fill"));
                ellipse.setStrokeColor(jsonObject.getString("stroke"));
                ellipse.setStrokeWidth((float) jsonObject.getDouble("strokeWidth"));

                break;
            }

            case "polyline": {
                component = new Polyline();
                Polyline polyline = (Polyline) component;

                polyline.setFillColor(jsonObject.optString("fill"));
                polyline.setStrokeColor(jsonObject.getString("stroke"));
                polyline.setStrokeWidth((float) jsonObject.getDouble("strokeWidth"));
                JSONArray points = jsonObject.getJSONArray("points");
                polyline.setPoints(points);
                break;
            }

            case "text": {
                component = new Text();
                Text text = (Text) component;
                text.setFillColor(jsonObject.getString("fill"));
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
        circlePath.addPathPoint(new MovePathPoint(0, 50));
        circlePath.addPathPoint(new CubicPathPoint(0, 0, 100, 0, 100, 50));
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
        firstPath.addPathPoint(new MovePathPoint(99, 100));
        firstPath.addPathPoint(new LinePathPoint(99, 77));
        firstPath.addPathPoint(new LinePathPoint(21.8651008f, 77));
        firstPath.addPathPoint(new LinePathPoint(0.695023702f, 98.294976f));
        firstPath.addPathPoint(new CubicPathPoint(0.305649711f, 98.6866472f, 0.307511978f, 99.3198095f, 0.699183192f, 99.7091835f));
        firstPath.addPathPoint(new CubicPathPoint(0.886547653f, 99.895449f, 1.14000972f, 100, 1.40420717f, 100));
        firstPath.addPathPoint(new LinePathPoint(99, 100));
        firstPath.addPathPoint(new ClosePathPoint());
        firstPath.setFillColor("#00ff00");
        g.addComponent(firstPath);

        Path secondPath = new Path();
        secondPath.setLeft(-49.63f);
        secondPath.setTop(-49.9f);
        secondPath.addPathPoint(new MovePathPoint(0.404207143f, 0));
        secondPath.addPathPoint(new CubicPathPoint(14.4613263f,
                10.4298917f,
                21.6599239f,
                0.630286972f,
                22f,
                0.601185771f));
        secondPath.addPathPoint(new CubicPathPoint(22.3400761f,
                0.572708821f,
                22.4746361f,
                9.75152654f,
                22.4036799f,
                28.1376389f));
        secondPath.addPathPoint(new LinePathPoint(22.0033763f,
                76.5913291f));
        secondPath.addPathPoint(new CubicPathPoint(22.0012123f,
                76.8532613f,
                21.8963606f,
                77.1038874f,
                21.7113617f,
                77.289329f));
        secondPath.addPathPoint(new LinePathPoint(0.404207143f,
                98.6474712f));
        secondPath.addPathPoint(new LinePathPoint(0.404207143f,
                30));
        secondPath.addPathPoint(new ClosePathPoint());
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
