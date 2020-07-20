package com.example.developerandroidx.ui.java.thread;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_01;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_02;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_03;

import butterknife.OnClick;

public class ThreadActivity extends BaseActivityWithButterKnife {

    @Override
    protected int bindLayout() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("线程");
    }

    @OnClick({R.id.tv_thread_01, R.id.tv_thread_02, R.id.tv_thread_03})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_thread_01:
                new ThreadDialog_01().show(context);
                break;
            case R.id.tv_thread_02:
                new ThreadDialog_02().show(context);
                break;
            case R.id.tv_thread_03:
                new ThreadDialog_03().show(context);
                break;
        }
    }
}