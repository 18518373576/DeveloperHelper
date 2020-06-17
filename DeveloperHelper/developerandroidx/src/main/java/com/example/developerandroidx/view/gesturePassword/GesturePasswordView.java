package com.example.developerandroidx.view.gesturePassword;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

import java.util.ArrayList;

/**
 * 作者： zjf 2020/6/16 3:40 PM
 * 参考：
 * 描述：手势密码解锁
 */
public class GesturePasswordView extends View {

    private Context context;
    private Paint passwordPaint;
    private Paint linePaint;
    private Paint lineStartPaint;
    //控件宽高
    private int width;
    private int height;
    //手指移动坐标
    private float fingerX;
    private float fingerY;
    //密码圆点半径
    private float pointRadius;

    //选中的颜色
    private int selectedColor = Color.argb(80, 0, 238, 118);
    private int lineColor = Color.rgb(0, 205, 102);
    //保存所有的点
    private ArrayList<Point> points = new ArrayList<>();
    //移动轨迹
    private ArrayList<Point> trackPoints = new ArrayList<>();

    //手势滑动结束回调
    private OnPasswordCallBack callBack;

    //定义每个点的属性
    //9个点的位置为
    //对应的num值¬
    //0 1 2
    //3 4 5
    //6 7 8
    private class Point {
        public final int num;
        public final float x;
        public final float y;
        public boolean isSelect = false;

        @Override
        public String toString() {
            return "Point{" +
                    "num=" + num +
                    ", x=" + x +
                    ", y=" + y +
                    ", isSelect=" + isSelect +
                    '}';
        }

        public Point(int num, float x, float y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public GesturePasswordView(Context context) {
        this(context, null);
    }

    public GesturePasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        //控件宽高,保留与边框的距离20dp
        width = PixelTransformForAppUtil.getDiaplayWidth() - PixelTransformForAppUtil.dip2px(40) * 2;
        //高度等于宽度
        height = width;
        //圆点直径
        float pointDiameter = width / 3f - PixelTransformForAppUtil.dip2px(30);
        pointRadius = pointDiameter / 2f;

        for (int i = 0; i < 3; i++) {
            //计算手势圆点y坐标位置
            float y = pointDiameter * (float) i + pointRadius + PixelTransformForAppUtil.dip2px(15) * (i * 2 + 1);
            for (int j = 0; j < 3; j++) {
                //计算手势圆点x坐标位置
                float x = pointDiameter * (float) j + pointRadius + PixelTransformForAppUtil.dip2px(15) * (j * 2 + 1);
                Point point = new Point(j + i * 3, x, y);
                //保存手势圆点
                points.add(point);
            }
        }
//        LogUtils.e("圆点位置", points.toString());

        passwordPaint = new Paint();
        passwordPaint.setStrokeWidth(pointDiameter);
        passwordPaint.setAntiAlias(true);
        passwordPaint.setStrokeCap(Paint.Cap.ROUND);
        passwordPaint.setColor(Color.LTGRAY);

        linePaint = new Paint();
        linePaint.setStrokeWidth(PixelTransformForAppUtil.dip2px(5));
        linePaint.setAntiAlias(true);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setColor(lineColor);

        lineStartPaint = new Paint();
        lineStartPaint.setStrokeWidth(PixelTransformForAppUtil.dip2px(20));
        lineStartPaint.setAntiAlias(true);
        lineStartPaint.setStrokeCap(Paint.Cap.ROUND);
        lineStartPaint.setColor(lineColor);

        LogUtils.e("init", "init");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    //当前手指所在的点
    Point currentSelectPoint = null;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.RED);
        //根据手势移动画点
        for (int i = 0; i < points.size(); i++) {
            //判断手指移动的位置,画出手指经过的线
            if (fingerX > points.get(i).x - pointRadius && fingerX < points.get(i).x + pointRadius
                    && fingerY > points.get(i).y - pointRadius && fingerY < points.get(i).y + pointRadius) {
                //选中画笔的颜色
                passwordPaint.setColor(selectedColor);
                //画选中的点
                canvas.drawPoint(points.get(i).x, points.get(i).y, passwordPaint);
                //画线的起始点
                canvas.drawPoint(points.get(i).x, points.get(i).y, lineStartPaint);
                points.get(i).isSelect = true;
                //当前手指所在的点,必须是没被选中过得点
                if (!checkContains(points.get(i))) {
                    currentSelectPoint = points.get(i);
                }
            } else {
                //记录选中的点,当手指离开时,保持选中状态
                if (points.get(i).isSelect) {
                    passwordPaint.setColor(selectedColor);
                    canvas.drawPoint(points.get(i).x, points.get(i).y, passwordPaint);
                    canvas.drawPoint(points.get(i).x, points.get(i).y, lineStartPaint);
                } else {
                    //没被选中过的点保持原来的状态
                    passwordPaint.setColor(Color.LTGRAY);
                    canvas.drawPoint(points.get(i).x, points.get(i).y, passwordPaint);
                }
            }
        }
        //处理当前手指所在的点的状态,根据手势移动画线
        if (currentSelectPoint != null) {
            //检查轨迹点是否包含当前点,并连接保存的各个点
            checkContainsAndTrack(currentSelectPoint, canvas);
            //当前点手势状态
            canvas.drawLine(currentSelectPoint.x, currentSelectPoint.y, fingerX, fingerY, linePaint);
        }
    }

    private boolean checkContains(Point point) {
        for (Point p : trackPoints) {
            if (p.num == point.num)
                return true;
        }
        return false;
    }

    //检查保存的轨迹点是否包含当前点,包含的话不再添加,并且连接保存的各个点
    boolean isContains = false;

    private void checkContainsAndTrack(Point point, Canvas canvas) {
        isContains = checkContains(point);

        if (!isContains) {
            trackPoints.add(point);
            LogUtils.e("已连接的点", trackPoints.toString());
        }
        for (int i = 0; i < trackPoints.size() - 1; i++) {
            canvas.drawLine(trackPoints.get(i).x, trackPoints.get(i).y,
                    trackPoints.get(i + 1).x, trackPoints.get(i + 1).y, linePaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                fingerX = event.getX();
                fingerY = event.getY();
                //增加手势移动范围
                if (fingerX < 20) {
                    fingerX = 20;
                }
                if (fingerX > width - 20) {
                    fingerX = width - 20;
                }
                if (fingerY < 20) {
                    fingerY = 20;
                }
                if (fingerY > height - 20) {
                    fingerY = height - 20;
                }
//                LogUtils.e("onTouchEvent", "X:" + event.getX() + "Y:" + event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //清除移动轨迹
                currentSelectPoint = null;
                for (int i = 0; i < points.size(); i++) {
                    points.get(i).isSelect = false;
                }
                fingerX = 0;
                fingerY = 0;
                if (callBack != null) {
                    callBack.callBack(getResult());
                }
                trackPoints.clear();
                isContains = false;
                invalidate();
                break;
        }
        return true;
    }

    private String getResult() {
        StringBuffer buffer = new StringBuffer();
        for (Point p : trackPoints) {
            buffer.append(p.num);
        }
        return buffer.toString();
    }

    public void setOnPasswordCallBack(OnPasswordCallBack callBack) {
        this.callBack = callBack;
    }
}
