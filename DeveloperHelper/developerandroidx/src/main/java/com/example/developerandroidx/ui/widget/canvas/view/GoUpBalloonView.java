package com.example.developerandroidx.ui.widget.canvas.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/7/15 9:34 AM
 * 参考：
 * 描述：上升的气球
 */
public class GoUpBalloonView extends RelativeLayout {
    private Context context;
    private boolean isDetachedFromWindow = false;
    private PointF pointFStart;
    private PointF pointFFirst;
    private PointF pointFSecond;
    private PointF pointFEnd;

    public GoUpBalloonView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .takeUntil((Predicate<Long>) aLong -> isDetachedFromWindow)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.e("添加气球", "" + aLong);
                        addBalloon();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    protected void initPoint(int startX) {
        pointFStart = new PointF();
        pointFFirst = new PointF();
        pointFSecond = new PointF();
        pointFEnd = new PointF();

        pointFStart.x = startX;
        //计算开始位置的y轴,+100主要是想要实现从屏幕底部外进入,和从父view顶部外移出的效果
        pointFStart.y = PixelTransformForAppUtil.getDiaplayHeight() + 100;

        pointFEnd.y = 0;
        pointFEnd.x = startX;

        pointFFirst.x = startX - PixelTransformForAppUtil.getDiaplayWidth() / 2f;
        pointFSecond.x = startX + PixelTransformForAppUtil.getDiaplayWidth() / 2f;

        pointFSecond.y = PixelTransformForAppUtil.getDiaplayHeight() / 4f;
        pointFFirst.y = PixelTransformForAppUtil.getDiaplayHeight() * 3 / 4f;
    }

    private void addBalloon() {
        CircleView circleView = new CircleView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.leftMargin = (int) (Math.random() * (PixelTransformForAppUtil.getDiaplayWidth() - circleView.getViewWidth()));

        initPoint(params.leftMargin);

        addView(circleView, params);
        startAnimator(circleView);
    }

    private void startAnimator(CircleView view) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY",
//                view.getTranslationY() + view.getViewWidth(), -PixelTransformForAppUtil.getDiaplayHeight());
//
//        animator.setDuration(10000);
//        animator.start();
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                removeView(view);
//            }
//        });

        ValueAnimator animator = ValueAnimator.ofObject(new TypeE(pointFFirst, pointFSecond, (int) view.getViewWidth()), pointFStart, pointFEnd);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF value = (PointF) animation.getAnimatedValue();
                view.setX(value.x);
                view.setY(value.y);
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(view);
            }
        });

        animator.setDuration(10000);
        animator.start();
    }


    /**
     * 绘制一个估值器,计算view的移动轨迹
     */
    class TypeE implements TypeEvaluator<PointF> {

        //三阶贝塞尔曲线,出去开始的点和结束的点,另外两个点
        private PointF pointFFirst, pointFSecond;
        private int viewWidth;

        public TypeE(PointF start, PointF end, int viewWidth) {
            this.pointFFirst = start;
            this.pointFSecond = end;
            this.viewWidth = viewWidth;
        }

        /**
         * @param fraction   动画进度
         * @param startValue 开始的点
         * @param endValue   结束的点
         * @return
         */
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            PointF result = new PointF();
            float left = 1 - fraction;
            result.x = (float) (startValue.x * Math.pow(left, 3) + 3 * pointFFirst.x * Math.pow(left, 2) * fraction + 3 * pointFSecond.x * Math.pow(fraction, 2) * left + endValue.x * Math.pow(fraction, 3));
            result.y = (float) (startValue.y * Math.pow(left, 3) + 3 * pointFFirst.y * Math.pow(left, 2) * fraction + 3 * pointFSecond.y * Math.pow(fraction, 2) * left + endValue.y * Math.pow(fraction, 3))
                    - viewWidth;
            return result;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isDetachedFromWindow = true;
    }
}
