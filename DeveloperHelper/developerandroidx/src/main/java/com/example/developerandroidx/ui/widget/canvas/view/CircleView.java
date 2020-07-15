package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/7/15 10:44 AM
 * 参考：
 * 描述：
 */
public class CircleView extends View {
    private float width;
    private float height;
    private Paint paint;

    public CircleView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        int sizeOffset = (int) (Math.random() * PixelTransformForAppUtil.dip2px(40));
        width = PixelTransformForAppUtil.dip2px(80) - sizeOffset;
        height = width;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        paint.setColor(Color.rgb(r, g, b));
    }

    /**
     * 获取view的宽度
     *
     * @return
     */
    public float getViewWidth() {
        return width;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) width, (int) height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width / 2f, height / 2f, width / 2f - 30, paint);
    }
}
