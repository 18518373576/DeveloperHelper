package com.example.developerandroidx.ui.widget.canvas;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.ui.widget.canvas.dialog.CanvasDialog;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasCoordinate;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawARGB;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawCircle;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawPoint;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawRect;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawText;

import butterknife.OnClick;

public class CanvasActivity extends BaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_canvas;
    }

    @OnClick({R.id.tv_01, R.id.tv_02, R.id.tv_03, R.id.tv_04, R.id.tv_05, R.id.tv_06})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_01://画布坐标系
                new CanvasDialog().show(context, new CanvasCoordinate(context));
                break;
            case R.id.tv_02://drawARGB
                new CanvasDialog().show(context, new CanvasDrawARGB(context));
                break;
            case R.id.tv_03://drawText
                new CanvasDialog().show(context, new CanvasDrawText(context));
                break;
            case R.id.tv_04://drawPoint
                new CanvasDialog().show(context, new CanvasDrawPoint(context));
                break;
            case R.id.tv_05://drawRect
                new CanvasDialog().show(context, new CanvasDrawRect(context));
                break;
            case R.id.tv_06://drawCircle
                new CanvasDialog().show(context, new CanvasDrawCircle(context));
                break;
        }
    }
}