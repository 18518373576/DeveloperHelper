package com.example.developerandroidx.ui.widget.canvas;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.canvas.dialog.CanvasDialog;

import butterknife.OnClick;

public class CanvasActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_canvas;
    }

    @OnClick({R.id.tv_01})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_01:
                new CanvasDialog().show(context, R.layout.dialog_canvas_coordinate);
                break;
        }
    }
}