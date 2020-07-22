package com.example.developerandroidx.ui.java.thread;

import android.view.View;

import com.example.developerandroidx.App;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_01;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_02;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_03;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_04;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_05;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_06;
import com.example.developerandroidx.ui.java.thread.dialog.ThreadDialog_08;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.StringUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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

    @OnClick({R.id.tv_thread_01, R.id.tv_thread_02, R.id.tv_thread_03, R.id.tv_thread_04,
            R.id.tv_thread_05, R.id.tv_thread_06, R.id.tv_thread_07, R.id.tv_thread_08,
            R.id.tv_thread_07_desc})
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
            case R.id.tv_thread_04:
                new ThreadDialog_04().show(context);
                break;
            case R.id.tv_thread_05:
                new ThreadDialog_05().show(context);
                break;
            case R.id.tv_thread_06:
                new ThreadDialog_06().show(context);
                break;
            case R.id.tv_thread_07_desc:
            case R.id.tv_thread_07:
                //开始运算
                startOperation();
                break;
            case R.id.tv_thread_08:
                new ThreadDialog_08().show(context);
                break;
        }
    }

    private class MyCallable implements Callable<String> {

        @Override
        public String call() {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "这是计算的结果:1+1=2";
        }
    }

    private void startOperation() {
        FutureTask<String> task = new FutureTask(new MyCallable());
        new Thread(task).start();
        try {
            LogUtils.e("获取计算结果", StringUtils.getInstance().getCurrentTime());
            App.showNotify(task.get());
            LogUtils.e("计算结果", StringUtils.getInstance().getCurrentTime());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}