package com.example.developerandroidx.ui.widget.canvas.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.developerandroidx.R;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

/**
 * 作者： zjf 2020/7/10 2:39 PM
 * 参考：
 * 描述：
 */
public class CanvasDialog implements FunctionDialogInterface {
    private View view;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_canvas, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ((LinearLayout) rootView).addView(view, params);
            }
        });
    }

    public void show(Context context, View view) {
        this.view = view;
        show(context);
    }

}
