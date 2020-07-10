package com.example.developerandroidx.view.ExtensibleScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.developerandroidx.utils.LogUtils;

/**
 * @作者： zjf 2020/5/15 11:16
 * @需求： 参考网页的文章展示，想要构建一个可扩展的scrollView可添加图片和文字，按照添加的顺序排列
 * @描述： 提供方法：addText(String text, InsertTextType textType, int colorId) addImage(int imageResourceId, int height_dp)
 */
public class ExtensibleScrollView extends ScrollView {

    private Context context;
    private LinearLayout contentLayout;
    private OnScrollChangedListener listener;
    private final int DEFAULT_COLOR = 0;
    private final int ALERT_COLOR = 1;
    public final String tab = "\n        ";
    public final String tabDouble = "\n        " + "        ";

    /**
     * 可添加的文类型
     * TITLE_1一级标题
     * TITLE_2二级标题
     * BODY正文
     */
    public enum InsertTextType {
        TITLE_1, TITLE_2, BODY, BOLD_BODY
    }

    public ExtensibleScrollView(Context context) {
        super(context);
        initView(context);
    }

    public ExtensibleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExtensibleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        setOverScrollMode(OVER_SCROLL_NEVER);
        setVerticalScrollBarEnabled(false);
        ViewGroup.LayoutParams contentParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentLayout = new LinearLayout(context);
        int padding = PixelTransformUtil.dip2px(context, 10);
        contentLayout.setPadding(padding, padding, padding, padding);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        this.addView(contentLayout, contentParam);
    }

    /**
     * 添加一条分割线
     */
    public void addLine(int lineColorResId) {

        View view = new View(context);
        LinearLayout.LayoutParams lineParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
        int margin = PixelTransformUtil.dip2px(context, 10);
        lineParam.setMargins(margin, margin, margin, margin);
        view.setBackgroundResource(lineColorResId);
        contentLayout.addView(view, lineParam);
    }

    /**
     * 添加内容，带着意图，如点击可以跳转
     *
     * @param intent
     */
    public void addBodyWithIntent(String text, int colorId, Intent intent) {
        TextView body = new TextView(context);
        body.setText("        " + text);
        body.setTextSize(14);
        body.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        int padding = PixelTransformUtil.dip2px(context, 5);
        body.setPadding(0, padding, 0, padding);
        //add增加的间距，mult增加的间距倍数
        body.setLineSpacing(0, 1.5f);
        body.setTextColor(context.getResources().getColor(colorId));
        body.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intent);
            }
        });
        contentLayout.addView(body);
    }

    public interface OnBodyClickListener {
        void onclick(View view);
    }

    public void addBodyWithClick(String text, int colorId, OnBodyClickListener clickListener) {
        TextView body = new TextView(context);
        body.setText("        " + text);
        body.setTextSize(14);
        body.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        int padding = PixelTransformUtil.dip2px(context, 5);
        body.setPadding(0, padding, 0, padding);
        //add增加的间距，mult增加的间距倍数
        body.setLineSpacing(0, 1.5f);
        body.setTextColor(context.getResources().getColor(colorId));
        body.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onclick(view);
            }
        });
        contentLayout.addView(body);
    }

    /**
     * 添加body使用默认颜色
     *
     * @param body
     */
    public void addBody(String body) {
        addText(body, InsertTextType.BODY, DEFAULT_COLOR);
    }

    public void addBoldBody(String body) {
        addText(body, InsertTextType.BOLD_BODY, DEFAULT_COLOR);
    }

    //添加提示语句,颜色比较醒目
    public void addAlertBody(String body) {
        addText(body, InsertTextType.BODY, ALERT_COLOR);
    }

    public void addAlertBody(String body, int colorId) {
        addText(body, InsertTextType.BODY, colorId);
    }

    /**
     * 添加一级标题，使用默认颜色
     *
     * @param title
     */
    public void addTitle_1(String title) {
        addText(title, InsertTextType.TITLE_1, DEFAULT_COLOR);
    }

    /**
     * 添加二级标题，使用默认颜色
     *
     * @param title
     */
    public void addTitle_2(String title) {

        addText(title, InsertTextType.TITLE_2, DEFAULT_COLOR);
    }

    /**
     * 添加文本
     *
     * @param text     文本内容
     * @param textType 文本类型
     * @param colorId  字体颜色,为0的时候使用默认颜色
     */
    public void addText(String text, InsertTextType textType, int colorId) {
        switch (textType) {
            case BOLD_BODY: {
                TextView body = new TextView(context);
                body.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                body.setTextIsSelectable(true);
                body.setText("        " + text);
                body.setTextSize(14);
                int padding = PixelTransformUtil.dip2px(context, 5);
                body.setPadding(0, padding, 0, padding);
                //add增加的间距，mult增加的间距倍数
                body.setLineSpacing(0, 1.5f);
                if (colorId == DEFAULT_COLOR) {
                    body.setTextColor(Color.rgb(110, 110, 110));
                } else if (colorId == ALERT_COLOR) {
                    body.setTextColor(Color.rgb(205, 85, 85));
                } else {
                    body.setTextColor(context.getResources().getColor(colorId));
                }

                contentLayout.addView(body);
            }
            break;
            case BODY: {
                TextView body = new TextView(context);
                body.setTextIsSelectable(true);
                body.setText("        " + text);
                body.setTextSize(14);
                int padding = PixelTransformUtil.dip2px(context, 5);
                body.setPadding(0, padding, 0, padding);
                //add增加的间距，mult增加的间距倍数
                body.setLineSpacing(0, 1.5f);
                if (colorId == DEFAULT_COLOR) {
                    body.setTextColor(Color.rgb(110, 110, 110));
                } else if (colorId == ALERT_COLOR) {
                    body.setTextColor(Color.rgb(205, 85, 85));
                } else {
                    body.setTextColor(context.getResources().getColor(colorId));
                }

                contentLayout.addView(body);
            }
            break;
            case TITLE_1: {
                TextView title_1 = new TextView(context);
                title_1.setText("    " + text);
                title_1.setTextSize(18);
                //设置字体加粗
                title_1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //add增加的间距，mult增加的间距倍数
                title_1.setLineSpacing(0, 1.5f);
                int padding = PixelTransformUtil.dip2px(context, 5);
                title_1.setPadding(0, padding, 0, padding);
                if (colorId == DEFAULT_COLOR) {
                    title_1.setTextColor(Color.rgb(54, 54, 54));
                } else {
                    title_1.setTextColor(context.getResources().getColor(colorId));
                }

                contentLayout.addView(title_1);
            }
            break;
            case TITLE_2: {
                TextView title_2 = new TextView(context);
                title_2.setText("    " + text);
                title_2.setTextSize(16);
                //设置字体加粗
                title_2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //add增加的间距，mult增加的间距倍数
                title_2.setLineSpacing(0, 1.5f);
                int padding = PixelTransformUtil.dip2px(context, 5);
                title_2.setPadding(0, padding, 0, padding);
                if (colorId == DEFAULT_COLOR) {
                    title_2.setTextColor(Color.rgb(54, 54, 54));
                } else {
                    title_2.setTextColor(context.getResources().getColor(colorId));
                }

                contentLayout.addView(title_2);
            }
            break;
        }
    }

    /**
     * 添加图片
     *
     * @param imageResourceId 图片的资源id
     * @param height_dp       图片的高度，宽度默认MATCH_PARENT
     */
    public void addImage(int imageResourceId, int height_dp) {

        ImageView image = new ImageView(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelTransformUtil.dip2px(context, height_dp));
        params.gravity = Gravity.CENTER_HORIZONTAL;
        int margin = PixelTransformUtil.dip2px(context, 10);
        params.setMargins(0, margin, 0, margin);
        image.setImageResource(imageResourceId);

        contentLayout.addView(image, params);
    }

    public interface OnScrollChangedListener {
        /**
         * 滑动状态
         *
         * @param dy 小于0为手指上滑
         */
        void onScrolled(int dy);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        LogUtils.e("滑动监听", (oldt - t) + "");
        if (listener != null) {
            listener.onScrolled(t - oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.listener = onScrollChangedListener;
    }
}
