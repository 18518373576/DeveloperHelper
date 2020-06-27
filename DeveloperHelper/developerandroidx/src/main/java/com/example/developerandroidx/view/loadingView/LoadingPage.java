package com.example.developerandroidx.view.loadingView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 2020/6/6 3:14 PM
 * 参考：
 * 描述：页面加载页面，目标提供功能
 * onLoading()正在加载，显示加载动画
 * loadingSuc()加载成功，隐藏LoadingView展示内容View
 * loadingFail()加载失败，展示加载失败提醒
 * noData()无数据
 */
public class LoadingPage extends RelativeLayout {

    private Context context;
    private String noDataMsg = "暂无数据";
    private int imageId = R.mipmap.icon_404;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        this.setVisibility(GONE);
        this.setBackgroundColor(Color.rgb(245, 245, 245));
    }

    public void setLoadingFailImage(Integer imageId) {
        this.imageId = imageId;
    }

    public void setNoDataMsg(String msg) {
        noDataMsg = msg;
    }

    /**
     * 配合databing使用
     *
     * @param state
     */
    public void setLoadingState(LoadingState state) {
        switch (state) {
            case LOADING_FAIL:
                loadingFail(imageId);
                break;
            case LOADING_SUC:
                loadingSuc();
                break;
            case ON_LOADING:
                onLoading();
                break;
            case NO_DATA:
                noData(noDataMsg);
                break;
        }
    }

    /**
     * 加载失败
     *
     * @param contentView 内容展示的view，失败后隐藏
     * @imageId 加载失败展示的图片ID
     */
    public void loadingFail(View contentView, int imageId) {
        this.removeAllViews();
        if (contentView != null) {
            contentView.setVisibility(GONE);
        }
        this.setVisibility(VISIBLE);
        ImageView loadingFailImage = new ImageView(context);
        loadingFailImage.setImageResource(imageId);
        LayoutParams params = new LayoutParams(PixelTransformForAppUtil.dip2px(100), PixelTransformForAppUtil.dip2px(100));
        params.addRule(CENTER_IN_PARENT);
        this.addView(loadingFailImage, params);
    }

    public void loadingFail(int imageId) {
        loadingFail(null, imageId);
    }

    /**
     * 正在加载
     *
     * @param contentView
     */
    public void onLoading(View contentView) {
        this.removeAllViews();
        if (contentView != null) {
            contentView.setVisibility(GONE);
        }
        this.setVisibility(VISIBLE);
        LayoutParams params = new LayoutParams(PixelTransformForAppUtil.dip2px(100), PixelTransformForAppUtil.dip2px(100));
        params.addRule(CENTER_IN_PARENT);
        this.addView(new LoadingView(context), params);
    }

    public void onLoading() {
        onLoading(null);
    }

    /**
     * 加载成功
     *
     * @param contentView
     */
    public void loadingSuc(View contentView) {
        if (contentView.getVisibility() == VISIBLE) {
            return;
        }
        contentView.setAlpha(0);
        contentView.setVisibility(View.VISIBLE);
        contentView.animate().alpha(1f).setDuration(500);

        this.setVisibility(GONE);
        this.removeAllViews();
    }

    public void loadingSuc() {
        //把其余元素remove掉,只留背景,看着像要展示的内容淡出一样
        this.removeAllViews();
        animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                LoadingPage.this.setVisibility(GONE);
            }
        });
    }

    /**
     * 无数据
     *
     * @param msg
     * @param isNoData
     * @param contentView
     */
    public void noData(String msg, boolean isNoData, View contentView) {
        if (isNoData) {
            this.removeAllViews();
            TextView tv_noData = new TextView(context);
            tv_noData.setText(msg);
            tv_noData.setTextColor(Color.rgb(190, 190, 190));
            tv_noData.setTextSize(16);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(CENTER_IN_PARENT);
            this.addView(tv_noData, params);
            if (contentView != null) {
                contentView.setVisibility(GONE);
            }
            this.setVisibility(VISIBLE);
        } else {
            if (contentView.getVisibility() == VISIBLE) {
                return;
            }
            this.setVisibility(GONE);
            contentView.setVisibility(VISIBLE);
        }
    }

    public void noData(String msg) {
        noData(msg, true, null);
    }
}
