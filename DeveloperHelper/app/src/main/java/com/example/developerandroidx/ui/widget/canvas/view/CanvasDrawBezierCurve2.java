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
public class CanvasDrawBezierCurve2 extends View {
    private Paint paint;
    private Path path;
    private int width;
    private float bezierPointX;
    private float bezierPointY;

    public CanvasDrawBezierCurve2(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        width = PixelTransformForAppUtil.getDiaplayWidth();
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        path = new Path();

        bezierPointX = 300;
        bezierPointY = width / 2f + 300;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xAAAAAAAA);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(100, width / 2f);
        path.quadTo(bezierPointX, bezierPointY, width - 100, width / 2f);
        canvas.drawPath(path, paint);

        paint.setColor(0xAA0000FF);
        paint.setStrokeWidth(20);
        canvas.drawPoint(100, width / 2f, paint);
        canvas.drawPoint(width - 100, width / 2f, paint);

        paint.setColor(0xAAFF0000);
        paint.setStrokeWidth(50);
        canvas.drawPoint(bezierPointX, bezierPointY, paint);
    }

    private float touchX;
    private float touchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                if (touchX < bezierPointX - 100 || touchX > bezierPointX + 100) {
                    return false;
                }
                if (touchY < bezierPointY - 100 || touchY > bezierPointY + 100) {
                    return false;
                }
        }
        bezierPointX = event.getX();
        bezierPointY = event.getY();
        path = new Path();
        invalidate();
        return true;
    }
}
