package com.example.developerandroidx.ui.widget.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/7/12 10:51 AM
 * 参考：
 * 描述：
 */
public class CanvasDrawRect extends View {
    private Paint paint;
    private int screenWidth;

    public CanvasDrawRect(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        screenWidth = PixelTransformForAppUtil.getDiaplayWidth();
        paint = new Paint();
//        参数说明
//        (x0,y0)：渐变起始点坐标
//                (x1,y1):渐变结束点坐标
//        color0:渐变开始点颜色,16进制的颜色表示，必须要带有透明度
//        color1:渐变结束颜色
//        colors:渐变数组
//        positions:位置数组，position的取值范围[0,1],作用是指定某个位置的颜色值，如果传null，渐变就线性变化。
//        tile:用于指定控件区域大于指定的渐变区域时，空白区域的颜色填充方法。
//
//        CLAMP边缘拉伸，为被shader覆盖区域，使用shader边界颜色进行填充
//                -REPEAT 在水平和垂直两个方向上重复，相邻图像没有间隙
//                -MIRROR以镜像的方式在水平和垂直两个方向上重复，相邻图像有间隙
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        LinearGradient linearGradient = new LinearGradient(50, screenWidth / 2f, 50, 20, 0xAAFF0000, 0x22FF0000, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(50, 20, screenWidth - 50, screenWidth / 2f, paint);
    }
}
