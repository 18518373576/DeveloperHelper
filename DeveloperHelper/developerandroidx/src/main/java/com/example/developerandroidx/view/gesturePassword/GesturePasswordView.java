package com.example.developerandroidx.view.gesturePassword;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/6/16 3:40 PM
 * 参考：
 * 描述：手势密码解锁
 */
public class GesturePasswordView extends View {

    private Context context;
    private Paint passwordPaint;
    //控件宽高
    private int width;
    private int height;
    //密码圆点半径
    private float pointRadius;

    public GesturePasswordView(Context context) {
        this(context, null);
    }

    public GesturePasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        passwordPaint = new Paint();
        //控件宽高,保留与边框的距离20dp
        width = PixelTransformForAppUtil.getDiaplayWidth() - PixelTransformForAppUtil.dip2px(20) * 2;
        height = width;

        pointRadius = width / 3f;
        passwordPaint.setStrokeWidth(pointRadius);
        passwordPaint.setAntiAlias(true);
        passwordPaint.setStrokeCap(Paint.Cap.ROUND);
        passwordPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawPoint(pointRadius/2f, pointRadius/2f, passwordPaint);
    }
}
