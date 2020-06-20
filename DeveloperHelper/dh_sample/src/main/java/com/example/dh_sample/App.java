package com.example.dh_sample;

import android.app.Application;
import android.content.Context;

/**
 * 作者： zjf 2020/6/19 3:50 PM
 * 参考：
 * 描述：
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
