package com.example.developerandroidx.view.ExtensibleScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.RouteUtil;

import thereisnospon.codeview.CodeView;
import thereisnospon.codeview.CodeViewTheme;

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
    //默认body字体大小
    private final int BODY_TEXT_SIZE = 15;
    //默认body字体颜色
    private final int BODY_TEXT_COLOR = Color.rgb(79, 79, 79);
    private int defaultPadding;
    /**
     * 换行并带一个缩进
     */
    public final String tab = "\n        ";
    /**
     * 换行并带另个缩进
     */
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

    /**
     * 初始化操作
     *
     * @param context
     */
    private void initView(Context context) {
        this.context = context;
        defaultPadding = PixelTransformUtil.dip2px(context, 10);
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
     * 添加一个View
     *
     * @param view
     * @param params
     */
    public void addMyView(View view, LinearLayout.LayoutParams params) {
        contentLayout.addView(view, params);
    }

    /**
     * 添加view
     *
     * @param view
     */
    public void addMyView(View view) {
        contentLayout.addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 添加code
     *
     * @param code
     */
    public void addCode(String code) {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setOverScrollMode(OVER_SCROLL_NEVER);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setBackgroundResource(R.drawable.bg_dark_gray);
        horizontalScrollView.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding);
        TextView body = new TextView(context);
        body.setText(code);
        body.setTextSize(BODY_TEXT_SIZE);
//        body.setTypeface(Typeface.DEFAULT_BOLD);
        //add增加的间距，mult增加的间距倍数
        body.setLineSpacing(0, 1.5f);
        body.setTextColor(Color.rgb(150, 150, 150));
        horizontalScrollView.addView(body, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contentLayout.addView(horizontalScrollView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
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
        body.setTextSize(BODY_TEXT_SIZE);
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

    public void goTo(String desc, Class<? extends AppCompatActivity> c, String param) {
        TextView body = new TextView(context);
        body.setText("        " + desc);
        body.setTextSize(BODY_TEXT_SIZE);
        body.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        int padding = PixelTransformUtil.dip2px(context, 5);
        body.setPadding(0, padding, 0, padding);
        //add增加的间距，mult增加的间距倍数
        body.setLineSpacing(0, 1.5f);
        body.setTextColor(context.getResources().getColor(R.color.colorMain));
        body.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RouteUtil.goTo(context, c.getName(), param);
            }
        });
        contentLayout.addView(body);
    }

    public interface OnBodyClickListener {
        void onclick(View view);
    }

    /**
     * 添加可以点击的文本
     *
     * @param text
     * @param colorId
     * @param clickListener
     */
    public void addBodyWithClick(String text, int colorId, OnBodyClickListener clickListener) {
        TextView body = new TextView(context);
        body.setText("        " + text);
        body.setTextSize(BODY_TEXT_SIZE);
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

    /**
     * 添加带有着重标记的内容
     */
    public void addLabelBody(String content) {
        TextView body = new TextView(context);
        body.setTextIsSelectable(true);
        body.append("        ");
        body.append(Html.fromHtml(content));
        body.setTextSize(BODY_TEXT_SIZE);
        int padding = PixelTransformUtil.dip2px(context, 5);
        body.setPadding(0, padding, 0, padding);
        //add增加的间距，mult增加的间距倍数
        body.setLineSpacing(0, 1.5f);
        body.setTextColor(BODY_TEXT_COLOR);
        contentLayout.addView(body);
    }

    /**
     * 获取着重显示的内容字符串
     *
     * @param content
     * @return
     */
    public String getBoldLabel(String content) {
        return "<b><font color=#17abe3>" + content + "<font/></b>";
    }


    /**
     * 添加加粗的文本
     *
     * @param body
     */
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
                body.setTextSize(BODY_TEXT_SIZE);
                int padding = PixelTransformUtil.dip2px(context, 5);
                body.setPadding(0, padding, 0, padding);
                //add增加的间距，mult增加的间距倍数
                body.setLineSpacing(0, 1.5f);
                if (colorId == DEFAULT_COLOR) {
                    body.setTextColor(BODY_TEXT_COLOR);
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
                body.setTextSize(BODY_TEXT_SIZE);
                int padding = PixelTransformUtil.dip2px(context, 5);
                body.setPadding(0, padding, 0, padding);
                //add增加的间距，mult增加的间距倍数
                body.setLineSpacing(0, 1.5f);
                if (colorId == DEFAULT_COLOR) {
                    body.setTextColor(BODY_TEXT_COLOR);
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
                title_2.setText(text);
                title_2.setTextSize(16);
                title_2.setGravity(Gravity.CENTER);
                //设置字体加粗
                title_2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //add增加的间距，mult增加的间距倍数
                title_2.setLineSpacing(0, 1.5f);
                int padding = PixelTransformUtil.dip2px(context, 20);
                title_2.setPadding(0, padding, 0, padding);
                if (colorId == DEFAULT_COLOR) {
                    title_2.setTextColor(Color.rgb(255, 114, 86));
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
