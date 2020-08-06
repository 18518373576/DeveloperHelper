package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者： zjf 2020/7/11 6:26 PM
 * 参考：
 * 描述：
 */
public class CanvasDrawARGB extends View {
    public CanvasDrawARGB(Context context) {
        super(context);
    }

    public CanvasDrawARGB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(100, 255, 0, 0);
    }
}
