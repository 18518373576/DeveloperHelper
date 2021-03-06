package com.example.developerandroidx.ui.widget.gesturePassword;

import android.widget.TextView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.view.gesturePassword.GesturePasswordView;
import com.example.developerandroidx.view.gesturePassword.OnPasswordCallBack;

import butterknife.BindView;

public class GesturePasswordActivity extends BaseActivityWithButterKnife {

    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.gpv_password)
    GesturePasswordView gpvPassword;

    @Override
    protected int bindLayout() {
        return R.layout.activity_gesture_password;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("手势密码");
        gpvPassword.setOnPasswordCallBack(new OnPasswordCallBack() {
            @Override
            public void callBack(String password) {
                DialogUtils.getInstance().showMessageDialog(context, "密码", password);
            }
        });
    }
}