package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.StringUtils;

/**
 * 作者： zjf 2020/7/14 11:48 AM
 * 参考：
 * 描述：
 */
public class ScrollBarView extends View {
    private Paint paint;
    private Path path;
    private int radius;
    private int width;
    private float progress;
    private int margin;
    private float textOffsetY;
    private int textX;
    private int textWidth;
    private Rect rect;
    String progressStr;

    public ScrollBarView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        //定义进度
        progress = 0.0f;
        //屏幕宽度,这里控件宽度为屏幕宽度减去margin值
        width = PixelTransformForAppUtil.getDiaplayWidth();
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        //定义路径
        path = new Path();
        //滑块的半径
        radius = 40;
        //实际计算进度progress的宽度为width-margin,由于滑块占用控件,且不能滑出控件外
        margin = 100 + radius * 3;

        //画的text高度插值
        textOffsetY = 0;
        textX = margin + radius * 3;
        textWidth = 0;

        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xAAAAAAAA);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        //使用贝塞尔曲线画圆弧,都是计算开始点确定弧线的点
        //这里使用了两条贝塞尔曲线进行绘制
        path.moveTo(margin - radius * 3, width / 2f);
        path.lineTo(margin + (width - margin * 2) * progress - radius * 3, width / 2f);
        path.moveTo(margin + (width - margin * 2) * progress - radius * 3, width / 2f);
        path.cubicTo(margin + (width - margin * 2) * progress - radius, width / 2f,
                margin + (width - margin * 2) * progress - radius, width / 2f - radius,
                margin + (width - margin * 2) * progress, width / 2f - radius);

        path.moveTo(width - margin + radius * 3, width / 2f);
        path.lineTo(margin + (width - margin * 2) * progress + radius * 3, width / 2f);
        path.moveTo(margin + (width - margin * 2) * progress + radius * 3, width / 2f);
        path.cubicTo(margin + (width - margin * 2) * progress + radius, width / 2f,
                margin + (width - margin * 2) * progress + radius, width / 2f - radius,
                margin + (width - margin * 2) * progress, width / 2f - radius);
        canvas.drawPath(path, paint);

        //画滑块,一个圆点¬
        paint.setStrokeWidth(radius * 2 - 20);
        paint.setColor(0xAA0000FF);
        canvas.drawPoint(margin + (width - margin * 2) * progress, width / 2f, paint);

        //画进度描述text文字
        paint.setColor(0xAAAAAAAA);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(PixelTransformForAppUtil.dip2px(20));
        //计算文字偏移
        progressStr = StringUtils.getInstance().getFloat_2(progress * 100) + "%";
        paint.getTextBounds(progressStr, 0, progressStr.length(), rect);
        textWidth = rect.width();

        //当滑块右端接触到文字的左端时文字的y轴偏移
        if (progress > 0 &&
                (width - margin * 2) * progress < radius * 3) {
            textOffsetY = (width - margin * 2) * progress / 3f;
        } else if (margin + (width - margin * 2) * progress > margin + radius * 3 + textWidth &&
                margin + (width - margin * 2) * progress - radius * 3 < margin + radius * 3 + textWidth) {
            //当滑块的左端接触到文字的右端时文字的y轴偏移,所以需要加上文字宽度的x轴偏移
            textOffsetY = (radius * 6 + textWidth - (width - margin * 2) * progress) / 3f;
        } else if (margin + (width - margin * 2) * progress > textX && margin + (width - margin * 2) * progress < textX + textWidth) {
            //当滑块位于文字中间时
            textOffsetY = radius;
        } else {
            textOffsetY = 0;
        }
        canvas.drawText(progressStr,
                textX, width / 2f - radius / 2f - textOffsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getY() > width / 2f + radius || event.getY() < width / 2f - radius)
                    return false;
            case MotionEvent.ACTION_UP:
                if (event.getX() < margin - 10) {
                    progress = 0;
                    path = new Path();
                    invalidate();
                    return false;
                } else if (event.getX() > width - margin + 10) {
                    progress = 1;
                    path = new Path();
                    invalidate();
                    return false;
                }
            case MotionEvent.ACTION_MOVE:
                if (event.getX() < margin - 50 || event.getX() > width - margin + 50)
                    return false;
        }
        progress = (event.getX() - margin) / (width - margin * 2);
        if (progress > 1) {
            progress = 1;
        }
        if (progress < 0) {
            progress = 0;
        }

        path = new Path();
        invalidate();
        return true;
    }
}
