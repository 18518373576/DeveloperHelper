package com.example.developerandroidx.ui.android.sqlite.dialog;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.ui.android.sqlite.DbControlActivity;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;

/**
 * 作者： zjf 2020/6/20 1:03 PM
 * 参考：
 * 描述：
 */
public class SelectDbLibraryDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showBottomMenu(context, new String[]{"RxJava+Room"}, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                RouteUtil.goTo(context, RouteUtil.getDestination(DbControlActivity.class));
            }
        });
    }
}
