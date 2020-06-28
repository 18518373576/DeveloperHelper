package com.example.developerandroidx.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.developerandroidx.R;

/**
 * 所有activity的基类,使用DataBinding
 */
public abstract class BaseActivityWithDataBinding<T extends ViewDataBinding> extends AppCompatActivity {

    protected Context context;
    protected T binding;

    protected TextView tv_title;
    protected ImageView iv_back;
    protected ImageView iv_right;
    protected View decor;
    private Window window;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setNativeStatusBar(StateBarType.DARK);

        binding = DataBindingUtil.setContentView(this, bindLayout());
        binding.setLifecycleOwner(this);

        tv_title = binding.getRoot().findViewById(R.id.tv_title);
        iv_back = binding.getRoot().findViewById(R.id.iv_back);
        iv_right = binding.getRoot().findViewById(R.id.iv_right);
        if (iv_back != null) {
            iv_back.setOnClickListener(v -> finish());
        }

        initView();

        initData();
    }

    /**
     * 设置顶栏文字为浅色，页面背景为深色时使用
     */
    protected void setTopBarTextLight() {
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
     * 状态栏(展示信号区域)状态
     */
    protected enum StateBarType {
        LIGHT, DARK, TRAN
    }

    /**
     * 设置顶栏文字颜色
     */
    protected void setNativeStatusBar(StateBarType type) {
        window = this.getWindow();
        decor = window.getDecorView();
        switch (type) {
            case DARK:
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                break;
            case LIGHT:
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                break;
            case TRAN:
                //https://www.jianshu.com/p/e89ee0a77bb5
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN设置全屏，顶栏展示图片的时候会用到
//                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    //虚拟键盘也透明
                    //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
                break;
        }
    }


    /**
     * 获取ViewModel
     *
     * @param modelClass 自己定义的viewModel类
     * @return
     */
    public <V extends ViewModel> V getViewModel(Class<V> modelClass) {
        return new ViewModelProvider(this).get(modelClass);
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
