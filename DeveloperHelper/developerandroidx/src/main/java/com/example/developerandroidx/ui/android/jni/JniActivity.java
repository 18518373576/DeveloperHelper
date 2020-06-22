package com.example.developerandroidx.ui.android.jni;

import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;

import butterknife.BindView;

public class JniActivity extends BaseActivity {

    @BindView(R.id.tv_hello)
    TextView tvHello;

    @Override
    protected int bindLayout() {
        return R.layout.activity_jni;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Hello JNI");
        tvHello.setText(stringFromJNI());
    }

    static {
        System.loadLibrary("hello-jni");
    }

    public native String stringFromJNI();
}