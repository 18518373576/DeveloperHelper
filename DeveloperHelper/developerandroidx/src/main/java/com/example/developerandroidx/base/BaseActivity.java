package com.example.developerandroidx.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 7/18/20 2:07 PM
 * 参考：
 * 描述：activity类即基础类
 */
public class BaseActivity extends AppCompatActivity {
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //设置顶部状态栏字体颜色为黑色
        initStatusBar();
    }

    /**
     * 初始化顶部状态栏
     */
    protected void initStatusBar() {
        //默认顶部状态栏字体为黑色,如需改变颜色,子类覆写此方法,不使用super,从新调用setNativeStatusBar
        //这样是为了解决setNativeStatusBar方法重复调用的问题
        setNativeStatusBar(StateBarType.DARK);
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
        Window window = this.getWindow();
        View decor = window.getDecorView();
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
    public <VM extends ViewModel> VM getViewModel(Class<VM> modelClass) {
        return new ViewModelProvider(this).get(modelClass);
    }
}
