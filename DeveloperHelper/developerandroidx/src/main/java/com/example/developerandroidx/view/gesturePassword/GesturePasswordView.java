package com.example.developerandroidx.view.gesturePassword;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者： zjf 2020/6/16 3:40 PM
 * 参考：
 * 描述：手势密码解锁
 */
public class GesturePasswordView extends View {

    private Context context;

    public GesturePasswordView(Context context) {
        this(context, null);
    }

    public GesturePasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
    }

}
