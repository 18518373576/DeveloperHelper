package com.example.developerandroidx.ui;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.example.developerandroidx.receiver.AppBroadcastReceiver;
import com.example.developerandroidx.utils.RouteUtil;

/**
 * 作者： zjf 2020/7/10 5:31 PM
 * 参考：
 * 描述：
 */
public class WelcomeActivity extends AppCompatActivity {
    private AppBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册 百度地图 SDK 广播监听者,鉴权监测APP_ID是否正确
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        mReceiver = new AppBroadcastReceiver();
        registerReceiver(mReceiver, iFilter);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RouteUtil.goTo(this, RouteUtil.getDestination(MainActivity.class));
        this.finish();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
