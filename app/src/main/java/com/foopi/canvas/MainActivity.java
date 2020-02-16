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

        canvasView.setTotalPartWidth(600, 500);

        Circle c = new Circle();
        c.setTop(100);
        c.setLeft(100);
        c.setRadius(50);
        c.setFillColor("#ff0f0f");
        components.add(c);
//        try {
//            parseJSONArray(components);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        canvasView.setComponents(components);
    }

    private void parseJSONArray(List<Component> components) throws JSONException {
        JSONArray array = new JSONArray("[{\"top\":80,\"left\":390,\"objects\":[{\"strokeWidth\":1,\"path\":[[\"M\",48.5663229,89.9751023],[\"L\",24.3337401,66.6963207],[\"L\",2.17359617,66.6963207],[\"L\",2.17359617,75.6081971],[\"L\",24.7186931,97.8206515],[\"L\",24.7186931,89.9751023],[\"L\",48.5663229,89.9751023],[\"Z\"]],\"top\":16.5,\"left\":-48.3,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-2.17359617,\\\"offsetY\\\":-66.6963207,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":68.70888922162565,\\\"x1\\\":25.369959535,\\\"y2\\\":86.77928409956256,\\\"x2\\\":25.369959535}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",4.78416841,8.13840464],[\"L\",25.9067098,27.4778137],[\"L\",95.7014044,29.2153802],[\"L\",71.1691577,8.13840464],[\"L\",4.78416841,8.13840464],[\"Z\"]],\"top\":-42.06,\"left\":-45.69,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-4.78416841,\\\"offsetY\\\":-8.13840464,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":9.50128880338029,\\\"x1\\\":50.242786405,\\\"y2\\\":21.738315637763677,\\\"x2\\\":50.242786405}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",32.4533652,65.5653371],[\"L\",24.8424454,65.5653371],[\"L\",24.8424454,67.0806856],[\"L\",45.8611511,87.5238422],[\"L\",53.3015774,86.7711412],[\"L\",32.4533652,65.5653371],[\"Z\"]],\"top\":15.37,\"left\":-25.63,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-24.8424454,\\\"offsetY\\\":-65.5653371,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":66.9852229259859,\\\"x1\\\":39.0720114,\\\"y2\\\":79.7340547205057,\\\"x2\\\":39.0720114}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",28.6072113,56.9114909],[\"L\",20.9962916,56.9114909],[\"L\",20.9962916,58.4268394],[\"L\",42.0149972,78.869996],[\"L\",49.4554235,78.1172951],[\"L\",28.6072113,56.9114909],[\"Z\"]],\"top\":6.71,\"left\":-29.48,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-20.9962916,\\\"offsetY\\\":-56.9114909,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":58.331376725985905,\\\"x1\\\":35.22585755,\\\"y2\\\":71.08020852050569,\\\"x2\\\":35.22585755}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",23.799519,47.2961063],[\"L\",16.1885993,47.2961063],[\"L\",16.1885993,48.8114548],[\"L\",37.2073049,69.2546114],[\"L\",44.6477312,68.5019104],[\"L\",23.799519,47.2961063],[\"Z\"]],\"top\":-2.9,\"left\":-34.29,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-16.1885993,\\\"offsetY\\\":-47.2961063,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":48.715992125985906,\\\"x1\\\":30.41816525,\\\"y2\\\":61.46482392050569,\\\"x2\\\":30.41816525}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",18.9918267,37.6807217],[\"L\",11.380907,37.6807217],[\"L\",11.380907,39.1960702],[\"L\",32.3996126,59.6392268],[\"L\",39.8400389,58.8865258],[\"L\",18.9918267,37.6807217],[\"Z\"]],\"top\":-12.52,\"left\":-39.09,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-11.380907,\\\"offsetY\\\":-37.6807217,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":39.10060752598591,\\\"x1\\\":25.610472950000002,\\\"y2\\\":51.84943932050569,\\\"x2\\\":25.610472950000002}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",14.1841344,28.0653371],[\"L\",6.57321465,28.0653371],[\"L\",6.57321465,29.5806856],[\"L\",27.5919203,50.0238422],[\"L\",35.0323466,49.2711412],[\"L\",14.1841344,28.0653371],[\"Z\"]],\"top\":-22.13,\"left\":-43.9,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-6.57321465,\\\"offsetY\\\":-28.0653371,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":29.485222925985905,\\\"x1\\\":20.802780625,\\\"y2\\\":42.23405472050569,\\\"x2\\\":20.802780625}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",8.41490365,18.4499524],[\"L\",0.803983878,18.4499524],[\"L\",0.803983878,19.965301],[\"L\",21.8226895,40.4084575],[\"L\",29.2631158,39.6557566],[\"L\",8.41490365,18.4499524],[\"Z\"]],\"top\":-31.75,\"left\":-49.67,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-0.803983878,\\\"offsetY\\\":-18.4499524,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":19.869838225985905,\\\"x1\\\":15.033549839000003,\\\"y2\\\":32.61867002050569,\\\"x2\\\":15.033549839000003}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",97.0997501,41.369181],[\"L\",97.0997501,43.5555442],[\"L\",99.1474738,43.5555442],[\"L\",99.1474738,42.7914133],[\"L\",97.0997501,41.369181],[\"Z\"]],\"top\":-8.83,\"left\":46.62,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-97.0997501,\\\"offsetY\\\":-41.369181,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":41.720590552655096,\\\"x1\\\":98.89076681579375,\\\"y2\\\":43.315111220677544,\\\"x2\\\":97.57932874748693}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",92.2920578,50.9845656],[\"L\",92.2920578,53.1709288],[\"L\",94.3397815,53.1709288],[\"L\",94.3397815,52.4067979],[\"L\",92.2920578,50.9845656],[\"Z\"]],\"top\":0.78,\"left\":41.82,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-92.2920578,\\\"offsetY\\\":-50.9845656,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":51.3359751526551,\\\"x1\\\":94.08307451579375,\\\"y2\\\":52.93049582067754,\\\"x2\\\":92.77163644748693}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",87.4843655,59.6384118],[\"L\",87.4843655,61.8247749],[\"L\",89.5320892,61.8247749],[\"L\",89.5320892,61.060644],[\"L\",87.4843655,59.6384118],[\"Z\"]],\"top\":9.44,\"left\":37.01,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-87.4843655,\\\"offsetY\\\":-59.6384118,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":59.98982133658231,\\\"x1\\\":89.27538221579375,\\\"y2\\\":61.58434193167448,\\\"x2\\\":87.96394414748693}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",81.6766732,69.2537964],[\"L\",81.6766732,71.4401596],[\"L\",83.7243968,71.4401596],[\"L\",83.7243968,70.6760287],[\"L\",81.6766732,69.2537964],[\"Z\"]],\"top\":19.05,\"left\":31.2,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-81.6766732,\\\"offsetY\\\":-69.2537964,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":69.6052059526551,\\\"x1\\\":83.46768982832997,\\\"y2\\\":71.19972662067754,\\\"x2\\\":82.15625182406684}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",76.8689809,77.9076425],[\"L\",76.8689809,80.0940057],[\"L\",78.9167045,80.0940057],[\"L\",78.9167045,79.3298748],[\"L\",76.8689809,77.9076425],[\"Z\"]],\"top\":27.71,\"left\":26.39,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-76.8689809,\\\"offsetY\\\":-77.9076425,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(129,135,131)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(247,255,252)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":77.9076425,\\\"x1\\\":77.89284269999999,\\\"y2\\\":80.0940057,\\\"x2\\\":77.89284269999999}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",73.022827,85.5999502],[\"L\",73.022827,87.7863134],[\"L\",75.0705507,87.7863134],[\"L\",75.0705507,87.0221825],[\"L\",73.022827,85.5999502],[\"Z\"]],\"top\":35.4,\"left\":22.55,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-73.022827,\\\"offsetY\\\":-85.5999502,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(129,135,131)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(247,255,252)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":85.5999502,\\\"x1\\\":74.04668885000001,\\\"y2\\\":87.7863134,\\\"x2\\\":74.04668885000001}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",94.7332105,89.6432342],[\"L\",78.2196583,72.1703199],[\"L\",68.2509171,92.5939816],[\"L\",94.7332105,89.6432342],[\"Z\"]],\"top\":21.97,\"left\":17.78,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-68.2509171,\\\"offsetY\\\":-72.1703199,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":73.49095933472005,\\\"x1\\\":81.4920638,\\\"y2\\\":85.3486803615251,\\\"x2\\\":81.4920638}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",4.01095251,8.68178379],[\"L\",4.01095251,15.6831229],[\"L\",25.5379312,34.5827397],[\"L\",48.8512156,90.1595479],[\"L\",25.6214385,90.307316],[\"L\",25.6214385,98.7538885],[\"L\",95.4902982,98.7538885],[\"L\",95.4902982,90.3075523],[\"L\",70.2927473,90.1592489],[\"L\",96.7039678,44.1992414],[\"L\",96.7039678,30.1933892],[\"L\",25.4683741,28.306044],[\"L\",4.01095251,8.68178379],[\"Z\"]],\"top\":-41.52,\"left\":-46.46,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-4.01095251,\\\"offsetY\\\":-8.68178379,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":14.506046608075318,\\\"x1\\\":50.357460155000005,\\\"y2\\\":66.80077737853033,\\\"x2\\\":50.357460155000005}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",27.4760458,8.08567585],[\"L\",27.493228,8.21562563],[\"L\",27.493228,15.8228359],[\"L\",27.4760458,15.9527857],[\"C\",27.4406698,16.0842224,27.4230769,16.2150777,27.4230769,16.3461538],[\"C\",27.4230769,19.6201112,37.7719183,22.5769231,50.4807692,22.5769231],[\"C\",63.1896201,22.5769231,73.5384615,19.6201112,73.5384615,16.3461538],[\"C\",73.5384615,16.2193112,73.5219872,16.0926924,73.4888757,15.9655614],[\"L\",73.4727336,15.8395396],[\"L\",73.4727336,8.19892197],[\"L\",73.4888757,8.07290011],[\"C\",73.5219872,7.94576912,73.5384615,7.81915034,73.5384615,7.69230769],[\"C\",73.5384615,4.41835029,63.1896201,1.46153846,50.4807692,1.46153846],[\"C\",37.7719183,1.46153846,27.4230769,4.41835029,27.4230769,7.69230769],[\"C\",27.4230769,7.82338387,27.4406698,7.95423909,27.4760458,8.08567585],[\"Z\"]],\"top\":-49.55,\"left\":-22.65,\"type\":\"path\",\"fill\":\"{\\\"offsetX\\\":-27.4230769,\\\"offsetY\\\":-1.46153846,\\\"colorStops\\\":[{\\\"offset\\\":1,\\\"color\\\":\\\"rgb(219,225,230)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0.279376385,\\\"color\\\":\\\"rgb(179,193,203)\\\",\\\"opacity\\\":1},{\\\"offset\\\":0,\\\"color\\\":\\\"rgb(153,172,185)\\\",\\\"opacity\\\":1}],\\\"type\\\":\\\"linear\\\",\\\"coords\\\":{\\\"y1\\\":2.826906240003224,\\\"x1\\\":50.4807692,\\\"y2\\\":15.08623290299845,\\\"x2\\\":50.4807692}}\",\"stroke\":\"#474747\"},{\"strokeWidth\":1,\"path\":[[\"M\",27.5183675,7.77746356],[\"C\",28.5055697,10.9582512,38.6455296,13.9232215,50.4844347,13.9232215],[\"C\",62.5493676,13.9232215,72.4873811,11.2584441,73.4634655,8.18731027],[\"L\",27.5183675,7.77746356],[\"Z\"]],\"top\":-42.83,\"left\":-22.89,\"type\":\"path\",\"fill\":\"\",\"stroke\":\"#474747\"}],\"type\":\"group\"},{\"top\":170,\"left\":210,\"fontSize\":\"40\",\"text\":\"Auto/Manual Status: Off\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":210,\"left\":240,\"fontSize\":\"40\",\"text\":\"Command: 0.00\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":250,\"left\":240,\"fontSize\":\"40\",\"text\":\"Fail Status: Off\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":140,\"left\":690,\"fontSize\":\"40\",\"text\":\"Inlet Valve Command: 0.00\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":180,\"left\":690,\"fontSize\":\"40\",\"text\":\"Inlet Valve Open Status: Off\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":210,\"left\":690,\"fontSize\":\"40\",\"text\":\"Outlet Temperature: 34.37\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":250,\"left\":690,\"fontSize\":\"40\",\"text\":\"Outlet Valve Command: 0.00\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":300,\"left\":450,\"fontSize\":\"40\",\"text\":\"Outlet Valve Open Status: Off\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":70,\"left\":690,\"fontSize\":\"40\",\"text\":\"Run Status: Off\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":130,\"left\":210,\"fontSize\":\"40\",\"text\":\"VFD Command Status: Off\",\"type\":\"text\",\"fill\":\"#0c0c0c\"},{\"top\":100,\"left\":690,\"fontSize\":\"40\",\"text\":\"VFD Feedback: -29.59\",\"type\":\"text\",\"fill\":\"#0c0c0c\"}]");
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
