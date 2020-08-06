package com.example.developerandroidx.ui.android.activity.actionIntent;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;

import butterknife.BindView;

public class ActionIntentActivity extends BaseActivityWithButterKnife implements View.OnClickListener {

    @BindView(R.id.tv_message)
    TextView tv_message;

    @Override
    protected int bindLayout() {
        return R.layout.activity_action_intent;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Action intent");
        Uri data = getIntent().getData();
        if (data != null) {
            tv_message.setText("");
            tv_message.append(data.getScheme() + "\n");
            tv_message.append(data.getHost() + "\n");
            tv_message.append(data.getPort() + "\n");
            tv_message.append(data.getPath() + "\n");
            tv_message.append(data.getQueryParameter("message") + "\n");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
