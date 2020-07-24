package com.example.developerandroidx.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者： zjf 2020/7/17 9:59 AM
 * 参考：
 * 描述：使用kotlin创建activity的基类
 */
abstract class BaseActivityForKotlin : AppCompatActivity() {

    protected lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStatusBar()
        context = this
    }

    /**
     * 子类去实现
     */
    protected abstract fun initView()

    /**
     * 子类实现如果需要的话
     */
    protected fun initData() {

    }

    /**
     * 初始化顶部状态栏
     */
    protected open fun initStatusBar() {
        //默认顶部状态栏字体为黑色,如需改变颜色,子类覆写此方法,不使用super,从新调用setNativeStatusBar
        //这样是为了解决setNativeStatusBar方法重复调用的问题
        setNativeStatusBar(StateBarType.DARK)
    }

    /**
     * 状态栏(展示信号区域)状态
     */
    protected enum class StateBarType {
        LIGHT, DARK, TRAN
    }

    /**
     * 设置顶栏文字颜色
     */
    protected open fun setNativeStatusBar(type: StateBarType?) {
        var window = this.window
        var decor = window.decorView
        when (type) {
            StateBarType.DARK -> decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            StateBarType.LIGHT -> decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            StateBarType.TRAN ->                 //https://www.jianshu.com/p/e89ee0a77bb5
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN设置全屏，顶栏展示图片的时候会用到
//                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                if (Build.VERSION.SDK_INT >= 21) { //21表示5.0
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    decor.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.statusBarColor = Color.TRANSPARENT
                } else if (Build.VERSION.SDK_INT >= 19) { //19表示4.4
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    //虚拟键盘也透明
                    //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
        }
    }
}