package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.StringUtils;

/**
 * 作者： zjf 2020/7/13 8:48 AM
 * 参考：
 * 描述：
 */
public class CanvasDrawPath extends View {
    private Paint paint;
    private Path path;
    private int radius;
    private RectF arc;
    private int width;
    private RectF arc2;
    private RectF arc3;
    private float progress;
    private int margin;

    public CanvasDrawPath(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        //定义进度
        progress = 0.5f;
        //屏幕宽度,这里控件宽度为屏幕宽度减去margin值
        width = PixelTransformForAppUtil.getDiaplayWidth();
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        //定义路径
        path = new Path();
        //滑块的半径
        radius = 30;
        //实际计算进度progress的宽度为width-margin,由于滑块占用控件,且不能滑出控件外
        margin = 100 + radius * 2;
        //设置被滑块顶起的圆,使用三个圆拼接而成
        setRect();
    }

    private void setRect() {
        //Rect的参数为int类型，而RectF的参数类型为float类型，从这一点上来看，RectF的精度更高一些
        arc = new RectF(margin + (width - margin * 2) * progress - radius * 3, width / 2f - radius * 2, margin + (width - margin * 2) * progress - radius, width / 2f);
        arc2 = new RectF(margin + (width - margin * 2) * progress - radius, width / 2f - radius * 2, margin + (width - margin * 2) * progress + radius, width / 2f);
        arc3 = new RectF(margin + (width - margin * 2) * progress + radius, width / 2f - radius * 2, margin + (width - margin * 2) * progress + radius * 3, width / 2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xAAAAAAAA);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        path.moveTo(margin - radius * 2, width / 2f);
        path.lineTo(margin + (width - margin * 2) * progress - radius * 2, width / 2f);

        path.arcTo(arc, 0, 90, true);

        path.arcTo(arc2, 0, -180, true);

        path.arcTo(arc3, 90, 90, true);

        path.moveTo(margin + (width - margin * 2) * progress + radius * 2, width / 2f);
        path.lineTo(width - margin + radius * 2, width / 2f);
        canvas.drawPath(path, paint);
        paint.setStrokeWidth(radius * 2 - 20);
        paint.setColor(0xAA0000FF);
        canvas.drawPoint(margin + (width - margin * 2) * progress, width / 2f - radius, paint);

        paint.setColor(0xAAAAAAAA);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(PixelTransformForAppUtil.dip2px(20));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(StringUtils.getInstance().getFloat_2(progress * 100) + "%",
                margin + (width - margin * 2) * progress, width / 2f - radius * 3, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getY() > width / 2f || event.getY() < width / 2f - radius * 2)
                    return false;
            case MotionEvent.ACTION_UP:
                if (event.getX() < margin - 10) {
                    progress = 0;
                    setRect();
                    path = new Path();
                    invalidate();
                    return false;
                } else if (event.getX() > width - margin + 10) {
                    progress = 1;
                    setRect();
                    path = new Path();
                    invalidate();
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                if (event.getX() < margin - 10 || event.getX() > width - margin + 10)
                    return false;
        }
        progress = (event.getX() - margin) / (width - margin * 2);
        if (progress > 1) {
            progress = 1;
        }
        if (progress < 0) {
            progress = 0;
        }
        setRect();
        path = new Path();
        invalidate();
        return true;
    }
}
