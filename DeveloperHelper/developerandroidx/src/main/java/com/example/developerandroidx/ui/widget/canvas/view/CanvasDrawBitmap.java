package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.example.developerandroidx.R;

/**
 * 作者： zjf 2020/7/15 9:02 AM
 * 参考：
 * 描述：
 */
public class CanvasDrawBitmap extends View {
    private Paint paint;
    private Bitmap bitmap;
    private Rect srcRect;
    private RectF dstRecF;

    public CanvasDrawBitmap(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.navigation_android);

        //截取bitmap
        srcRect = new Rect();
        srcRect.left = 0;
        srcRect.right = bitmap.getWidth();
        srcRect.top = 0;
        srcRect.bottom = (int) (bitmap.getHeight() * 0.55);

        //bitmap展示的位置
        dstRecF = new RectF();
        dstRecF.left = 100 + bitmap.getHeight();
        dstRecF.right = dstRecF.left + bitmap.getWidth();
        dstRecF.top = 100;
        dstRecF.bottom = dstRecF.top + bitmap.getHeight() * 0.55f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 100, 100, paint);

        canvas.drawBitmap(bitmap, srcRect, dstRecF, paint);
    }
}
