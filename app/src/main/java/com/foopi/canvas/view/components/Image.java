package com.foopi.canvas.view.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.vividsolutions.jts.geom.Geometry;

public class Image extends Component {

    private Bitmap bitmap;
//    private float scaleX;
//    private float scaleY;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

//    @Override
//    public Geometry getGeometry(float left, float top, double zoomLevel) {
//        return null;
//    }

    @Override
    public GeomProperty getGeomProperty(float left, float top, double zoomLevel) {
        return null;
    }


//    @Override
//    public void draw(float left, float top, float zoomLevel, Canvas canvas, Paint paint) {
//        Matrix matrix = new Matrix();
//        matrix.setScale(scaleX, scaleY, actualLeft(zoomLevel), actualTop(zoomLevel));
//        canvas.drawBitmap(bitmap, matrix, paint);
//    }
}
