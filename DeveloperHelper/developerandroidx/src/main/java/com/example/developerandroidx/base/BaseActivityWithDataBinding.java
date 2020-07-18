package com.example.developerandroidx.base;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.developerandroidx.R;

/**
 * 所有activity的基类,使用DataBinding
 */
public abstract class BaseActivityWithDataBinding<T extends ViewDataBinding> extends BaseActivity {

    protected T binding;

    protected TextView tv_title;
    protected ImageView iv_back;
    protected ImageView iv_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, bindLayout());
        binding.setLifecycleOwner(this);

        tv_title = binding.getRoot().findViewById(R.id.tv_title);
        iv_back = binding.getRoot().findViewById(R.id.iv_back);
        iv_right = binding.getRoot().findViewById(R.id.iv_right);

        if (iv_back == null) {
            throw new RuntimeException("布局文件必须 <include layout=\"@layout/title_bar\"/>,如无需使用titleBar请直接继承BaseActivity");
        }

        iv_back.setOnClickListener(v -> finish());

        initView();

        initData();
    }

    /**
     * 设置顶栏文字为浅色，页面背景为深色时使用
     */
    protected void setTitleTextLight() {
        //设置返回按钮的颜色
        iv_back.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
        iv_back.setBackgroundResource(R.drawable.selector_circuler_black);
        iv_right.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
        iv_right.setBackgroundResource(R.drawable.selector_circuler_black);
        //设置顶部信号栏字体颜色
        setNativeStatusBar(StateBarType.LIGHT);
        //设置title文字颜色
        tv_title.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        tv_title.setText(title);
    }

    /**
     * 绑定layout
     *
     * @return layout文件id
     */
    protected abstract int bindLayout();

    /**
     * view的一些操作,有些activity可能用不到,不作抽象处理,根据需要实现
     */
    protected void initView() {
    }

    /**
     * 处理回调数据的操作,有些activity可能用不到,不作抽象处理,根据需要实现
     */
    protected void initData() {
    }

}
