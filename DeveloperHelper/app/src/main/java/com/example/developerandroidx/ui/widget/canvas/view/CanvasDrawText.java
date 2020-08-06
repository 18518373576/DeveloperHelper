package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/7/11 6:53 PM
 * 参考：
 * 描述：
 */
public class CanvasDrawText extends View {
    private Paint paint;

    public CanvasDrawText(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setTextSize(PixelTransformForAppUtil.dip2px(15));
        paint.setColor(Color.rgb(110, 110, 110));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();

        canvas.drawText("字体大小15dp,颜色(110, 110, 110)",
                PixelTransformForAppUtil.dip2px(20), PixelTransformForAppUtil.dip2px(20), paint);

        paint.setAntiAlias(true);
        canvas.drawText("字体大小15dp,开启抗锯齿",
                PixelTransformForAppUtil.dip2px(20), PixelTransformForAppUtil.dip2px(50), paint);

        paint.setFakeBoldText(true);
        canvas.drawText("字体大小15dp,设置加粗",
                PixelTransformForAppUtil.dip2px(20), PixelTransformForAppUtil.dip2px(80), paint);

        paint.setUnderlineText(true);
        canvas.drawText("字体大小15dp,添加下划线",
                PixelTransformForAppUtil.dip2px(20), PixelTransformForAppUtil.dip2px(110), paint);

        canvas.save();
        canvas.translate(width / 2, 0);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("字体大小15dp,文字居中",
                0, PixelTransformForAppUtil.dip2px(140), paint);
        canvas.restore();

        canvas.save();
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.translate(PixelTransformForAppUtil.dip2px(20), PixelTransformForAppUtil.dip2px(170));
        canvas.rotate(30);
//        canvas.drawARGB(100,255,0,0);
        canvas.drawText("字体大小15dp,画布旋转30°",
                0, 0, paint);
    }
}
