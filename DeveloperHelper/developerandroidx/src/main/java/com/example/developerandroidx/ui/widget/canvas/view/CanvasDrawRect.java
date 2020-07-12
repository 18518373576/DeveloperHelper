package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * 作者： zjf 2020/7/12 10:51 AM
 * 参考：
 * 描述：
 */
public class CanvasDrawRect extends View {
    private Paint paint;

    public CanvasDrawRect(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.argb(100, 255, 0, 0));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRect(new Rect());
    }
}
