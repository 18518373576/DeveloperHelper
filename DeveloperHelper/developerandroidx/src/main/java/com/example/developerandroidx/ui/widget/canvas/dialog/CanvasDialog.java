package com.example.developerandroidx.ui.widget.canvas.dialog;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;

/**
 * 作者： zjf 2020/7/10 2:39 PM
 * 参考：
 * 描述：
 */
public class CanvasDialog implements FunctionDialogInterface {
    private int layoutId;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, layoutId);
    }

    public void show(Context context, int layoutId) {
        this.layoutId = layoutId;
        show(context);
    }
}
