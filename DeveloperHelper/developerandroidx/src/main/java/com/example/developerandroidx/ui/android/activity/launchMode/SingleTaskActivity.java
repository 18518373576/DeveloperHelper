package com.example.developerandroidx.ui.android.activity.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.RouteUtil;
import com.kongzue.dialog.v3.Notification;

public class SingleTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private String param;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_model);
        findViewById(R.id.iv_back).setOnClickListener(this);
        param = getIntent().getStringExtra(Constant.IntentParams.INTENT_PARAM);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(param);
        findViewById(R.id.btn_jump).setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        App.showNotify("还是我", "onNewIntent()被调用：" +
                intent.getStringExtra(Constant.IntentParams.INTENT_PARAM));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_jump:
                switch (param) {
                    case "singleTask_activity_1":
                        RouteUtil.goTo(this, RouteUtil.getDestination(StandardActivity.class), "form_singleTask");
                        break;
                }
                break;
        }
    }
}
