package com.example.developerandroidx.ui.widget.coordinatorLayout;

import android.content.Context;

import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.RouteUtil;

/**
 * 作者： zjf 7/19/20 4:55 PM
 * 参考：
 * 描述：
 */
public class CoordinatorLayoutTypeDialog implements FunctionDialogInterface {
    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showBottomMenu(context, new String[]{"标题栏移动", "结合ToolBar使用"}, new DialogUtils.OnItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                switch (index) {
                    case 0:
                        RouteUtil.goTo(context, RouteUtil.getDestination(CoordinatorLayoutOneActivity.class));
                        break;
                    case 1:
                        RouteUtil.goTo(context, RouteUtil.getDestination(CoordinatorLayoutTwoActivity.class));
                        break;
                }
            }
        });
    }
}
