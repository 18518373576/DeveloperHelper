package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 作者： zjf 2020/7/12 10:32 AM
 * 参考：
 * 描述：
 */
public class CanvasDrawPoint extends View {
    private Paint paint;

    public CanvasDrawPoint(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.argb(100, 255, 0, 0));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.translate(getWidth() / 2f, 100);
        canvas.drawPoint(0, 0, paint);

        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.translate(0, 400);
        canvas.drawPoint(0, 0, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.translate(0, 400);
        canvas.drawPoint(0, 0, paint);
    }
}
