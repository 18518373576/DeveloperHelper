package com.example.developerandroidx.ui.widget.canvas;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithButterKnife;
import com.example.developerandroidx.ui.widget.canvas.dialog.CanvasDialog;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasCoordinate;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawARGB;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawBezierCurve2;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawBezierCurve3;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawBitmap;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawCircle;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawPath;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawPoint;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawRect;
import com.example.developerandroidx.ui.widget.canvas.view.CanvasDrawText;
import com.example.developerandroidx.ui.widget.canvas.view.GoUpBalloonView;
import com.example.developerandroidx.ui.widget.canvas.view.ScrollBarView;
import com.example.developerandroidx.view.loadingView.LoadingView;

import butterknife.OnClick;

public class CanvasActivity extends BaseActivityWithButterKnife {

    @Override
    protected int bindLayout() {
        return R.layout.activity_canvas;
    }

    @OnClick({R.id.tv_01, R.id.tv_02, R.id.tv_03, R.id.tv_04, R.id.tv_05, R.id.tv_06
            , R.id.tv_07, R.id.tv_08, R.id.tv_09, R.id.tv_10, R.id.tv_11, R.id.tv_12,
            R.id.tv_13})
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
            case R.id.tv_07:
                new CanvasDialog().show(context, new CanvasDrawPath(context));
                break;
            case R.id.tv_08:
                new CanvasDialog().show(context, new LoadingView(context));
                break;
            case R.id.tv_09:
                new CanvasDialog().show(context, new CanvasDrawBezierCurve2(context));
                break;
            case R.id.tv_10:
                new CanvasDialog().show(context, new CanvasDrawBezierCurve3(context));
                break;
            case R.id.tv_11:
                new CanvasDialog().show(context, new ScrollBarView(context));
                break;
            case R.id.tv_12:
                new CanvasDialog().show(context, new CanvasDrawBitmap(context));
                break;
            case R.id.tv_13:
                new CanvasDialog().show(context, new GoUpBalloonView(context));
                break;
        }
    }
}