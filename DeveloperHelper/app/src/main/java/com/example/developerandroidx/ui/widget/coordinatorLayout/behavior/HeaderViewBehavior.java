package com.example.developerandroidx.ui.widget.coordinatorLayout.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 8/4/20 11:05 AM
 * 参考：
 * 描述：
 */
public class HeaderViewBehavior extends CoordinatorLayout.Behavior<TextView> {
    /**
     * 处于中心时候原始X轴
     */
    private int mOriginalHeaderX = 0;
    /**
     * 处于中心时候原始Y轴
     */
    private int mOriginalHeaderY = 0;

    /**
     * 移动的view最终停留的点
     */
    private final int finalX = PixelTransformForAppUtil.dip2px(30);
    private final int finalY = PixelTransformForAppUtil.getStatusBarHeight() - PixelTransformForAppUtil.dip2px(15 / 2f);

    private float mPercent;
    private float scale;


    public HeaderViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
//        LogUtils.e("dependency", dependency.getWidth() + "##" + dependency.getHeight());
        // 计算X轴坐标
        if (mOriginalHeaderX == 0) {
            this.mOriginalHeaderX = dependency.getWidth() / 2 - child.getWidth() / 2;
        }
        // 计算Y轴坐标
        if (mOriginalHeaderY == 0) {
            mOriginalHeaderY = (int) (dependency.getHeight() - child.getHeight() * 1.5);
        }
        //计算百分比,这里dependency.getY()最大值236.0,依此计算运行百分比
        mPercent = dependency.getY() / 236f;
//        LogUtils.e("dependency", mPercent + "");

        scale = 1 - 0.5f * mPercent;

        child.setScaleX(scale);
        child.setScaleY(scale);

        child.setX(mOriginalHeaderX - (mOriginalHeaderX - finalX) * mPercent);
        child.setY(mOriginalHeaderY - (mOriginalHeaderY - finalY) * mPercent);
        return true;
    }
}
