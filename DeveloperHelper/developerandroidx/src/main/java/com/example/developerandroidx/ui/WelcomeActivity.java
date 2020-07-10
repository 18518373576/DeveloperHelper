package com.example.developerandroidx.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.utils.RouteUtil;

/**
 * 作者： zjf 2020/7/10 5:31 PM
 * 参考：
 * 描述：
 */
public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RouteUtil.goTo(this, RouteUtil.getDestination(MainActivity.class));
        this.finish();
    }
}
