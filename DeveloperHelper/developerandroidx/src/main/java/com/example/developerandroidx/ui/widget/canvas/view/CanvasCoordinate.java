package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者： zjf 2020/7/10 2:44 PM
 * 参考：
 * 描述：
 */
public class CanvasCoordinate extends View {
    private Paint paint;

    public CanvasCoordinate(Context context) {
        super(context);
        initView();
    }

    public CanvasCoordinate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setTextSize(25);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();


        canvas.save();
        paint.setColor(Color.rgb(255, 0, 0));
        canvas.drawLine(15, 5, width, 5, paint);
        paint.setColor(Color.rgb(0, 0, 255));
        canvas.drawLine(15, 5, 15, height, paint);
        paint.setColor(Color.rgb(110, 110, 110));
        canvas.drawText("1.原始坐标,之前执行 canvas.save()", 20, 40, paint);

        canvas.rotate(30);
        canvas.translate(100, 0);
        paint.setColor(Color.rgb(255, 0, 0));
        canvas.drawLine(0, 0, width, 0, paint);
        paint.setColor(Color.rgb(0, 0, 255));
        canvas.drawLine(0, 0, 0, height, paint);
        paint.setColor(Color.rgb(110, 110, 110));
        canvas.drawText("2.Canvas旋转30°后平移X:100 Y:0", 20, 40, paint);

        canvas.restore();
        canvas.translate(100, 300);
        paint.setColor(Color.rgb(255, 0, 0));
        canvas.drawLine(0, 0, width, 0, paint);
        paint.setColor(Color.rgb(0, 0, 255));
        canvas.drawLine(0, 0, 0, height, paint);
        paint.setColor(Color.rgb(110, 110, 110));
        canvas.drawText("3.执行canvas.restore()后平移X:100 Y:300", 20, 40, paint);
    }
}
