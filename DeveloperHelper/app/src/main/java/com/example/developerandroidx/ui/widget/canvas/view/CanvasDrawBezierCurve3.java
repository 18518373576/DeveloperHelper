package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/7/14 10:57 AM
 * 参考：
 * 描述：二阶贝塞尔曲线
 */
public class CanvasDrawBezierCurve3 extends View {
    private Paint paint;
    private Path path;
    private int width;
    private float bezierPointX_01;
    private float bezierPointY_01;
    private float bezierPointX_02;
    private float bezierPointY_02;

    public CanvasDrawBezierCurve3(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        width = PixelTransformForAppUtil.getDiaplayWidth();
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        path = new Path();

        bezierPointX_01 = width / 4f;
        bezierPointY_01 = width / 2f + 300;

        bezierPointX_02 = width * 3 / 4f;
        bezierPointY_02 = width / 2f - 300;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xAAAAAAAA);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(100, width / 2f);
//        path.quadTo(bezierPointX, bezierPointY, width - 100, width / 2f);
        path.cubicTo(bezierPointX_01, bezierPointY_01, bezierPointX_02, bezierPointY_02, width - 100, width / 2f);
        canvas.drawPath(path, paint);

        paint.setColor(0xAA0000FF);
        paint.setStrokeWidth(20);
        canvas.drawPoint(100, width / 2f, paint);
        canvas.drawPoint(width - 100, width / 2f, paint);

        paint.setColor(0xAAFF0000);
        paint.setStrokeWidth(50);
        canvas.drawPoint(bezierPointX_01, bezierPointY_01, paint);
    }

    private float touchX;
    private float touchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                if (touchX < bezierPointX_01 - 100 || touchX > bezierPointX_01 + 100) {
                    return false;
                }
                if (touchY < bezierPointY_01 - 100 || touchY > bezierPointY_01 + 100) {
                    return false;
                }
        }
        bezierPointX_01 = event.getX();
        bezierPointY_01 = event.getY();
        path = new Path();
        invalidate();
        return true;
    }
}
