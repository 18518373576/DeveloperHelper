package com.example.developerandroidx.ui.widget.gesturePassword;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

public class GesturePasswordActivity extends BaseActivity {
    @Override
    protected int bindLayout() {
        return R.layout.activity_gesture_password;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("手势密码");
    }
}