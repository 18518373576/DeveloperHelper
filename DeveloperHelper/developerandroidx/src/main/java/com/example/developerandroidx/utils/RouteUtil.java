package com.example.developerandroidx.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.ui.widget.codeView.CodeViewActivity;

import java.util.Map;

/**
 * activity跳转类
 */
public class RouteUtil {
    private static Intent intent;

    /**
     * 获取要路由的activity类名
     *
     * @param activityClass
     * @return
     */
    public static String getDestination(Class<? extends AppCompatActivity> activityClass) {
        return activityClass.getName();
    }

    /**
     * 路由到哪里去,通用
     *
     * @param context
     * @param activityClassName
     */
    public static void goTo(Context context, String activityClassName) {
        try {
            intent = new Intent(context, Class.forName(activityClassName));
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 路由到哪里去,一个字符串参数
     *
     * @param context
     * @param activityClassName
     * @param paramStr
     */
    public static void goTo(Context context, String activityClassName, String paramStr) {
        try {
            intent = new Intent(context, Class.forName(activityClassName));
            intent.putExtra(Constant.IntentParams.INTENT_PARAM, paramStr);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * paramsMap为空，或paramStr为空
     *
     * @param context
     * @param activityClassName
     * @param paramsMap
     * @param paramStr
     * @throws ClassNotFoundException
     */
    public static void goTo(Context context, String activityClassName, Map<String, String> paramsMap, String paramStr) {

        if (paramsMap == null && TextUtils.isEmpty(paramStr)) {
            goTo(context, activityClassName);
        } else if (paramsMap == null) {
            goTo(context, activityClassName, paramStr);
        } else {

        }
    }

    /**
     * 经常返给的界面，增加一个方法，方便替换
     *
     * @param context
     * @param codeStr
     */
    public static void goToCodeViewActivity(Context context, String codeStr) {
        intent = new Intent(context, CodeViewActivity.class);
        intent.putExtra(Constant.IntentParams.INTENT_PARAM, codeStr);
        context.startActivity(intent);
    }
}
