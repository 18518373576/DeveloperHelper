package com.example.developerandroidx.view.customToast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.developerandroidx.App;
import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

/**
 * 作者： zjf 7/20/20 4:07 PM
 * 参考：
 * 描述：
 */
public enum CustomToast {

    INSTANCE;

    public void show(Context context, String title, String content, int iconId) {

        View layout = View.inflate(context, R.layout.view_custom_toast, null);

        ImageView imageView = layout.findViewById(R.id.iv_icon);
        imageView.setImageResource(iconId);

        View ll_text = layout.findViewById(R.id.ll_text);
        //根据屏幕宽度计算弹框的宽度
        int width = PixelTransformForAppUtil.getDiaplayWidth() - PixelTransformForAppUtil.dip2px(15 + 45 + 20 + 10);
        ll_text.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));


        TextView tv_title = layout.findViewById(R.id.tv_title);
        tv_title.setText(title);

        TextView tv_content = layout.findViewById(R.id.tv_content);
        tv_content.setText(content);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
