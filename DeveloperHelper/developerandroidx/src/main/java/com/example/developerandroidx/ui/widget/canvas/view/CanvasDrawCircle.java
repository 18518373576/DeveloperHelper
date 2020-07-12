package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/7/12 6:21 PM
 * 参考：
 * 描述：
 */
public class CanvasDrawCircle extends View {
    private int width;
    private Paint paint;
    private float radius;
    private float x;
    private float y;
    private int height;
    private float progress;

    public CanvasDrawCircle(Context context) {
        super(context);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    private void initView() {
        progress = 0.5f;
        width = PixelTransformForAppUtil.getDiaplayWidth();
        height = width;
        radius = width / 4f;//圆的半径
        x = width / 2f;//圆心x
        y = height / 3f;//圆心y
        paint = new Paint();
        paint.setAntiAlias(true);
//        当style为FILL时，绘制是填充面，FILL是Paint默认的style；
//        当style为STROKE时，绘制的是图形的轮廓线；
//        当style为FILL_AND_STROKE时，同时绘制填充面和轮廓线;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(0xAAAAAAAA);
        paint.setStrokeWidth(30);
        paint.setColor(0xAAAAAAAA);
        //画圆
        canvas.drawCircle(x, y, radius, paint);
        //画圆弧
        paint.setColor(0xAAFF0000);
        canvas.drawArc(x - radius, y - radius, x + radius, y + radius, -90, 360 * progress, false, paint);
        //画圆弧终点
        canvas.save();//保存画布当前状态
        canvas.rotate(360 * progress, x, y);//围绕圆心旋转画布180°,使与圆弧行走的角度一致
        paint.setColor(0xFF0000FF);
        paint.setStrokeWidth(50);
        canvas.drawPoint(x, y - radius, paint);
        canvas.restore();//回复画布状态

        //画一条进度条的线
        paint.setStrokeWidth(20);
        paint.setColor(0xAAAAAAAA);
        canvas.drawLine(50, height - 50, width - 50, height - 50, paint);

        //画进度点
        paint.setColor(0xFF0000FF);
        paint.setStrokeWidth(50);
        canvas.drawPoint(50 + ((width - 100) * progress), height - 50, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        LogUtils.e("onTouchEvent", width + "#" + height);
//        LogUtils.e("onTouchEvent", event.getX() + "#" + event.getY());
//
//        LogUtils.e("onTouchEvent", width + "#" + height);
//        LogUtils.e("onTouchEvent", event.getX() + "#" + event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getY() > height || event.getY() < height - 100) {
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if (event.getX() < 50 || event.getX() > width - 50) {
                    return false;
                }
        }
        float downX = event.getX();
        progress = (downX - 50) / (width - 100);
        invalidate();
        return true;
    }
}
