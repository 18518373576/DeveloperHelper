package com.example.developerandroidx.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.developerandroidx.R;
import com.example.developerandroidx.utils.enumPkg.TipType;
import com.example.developerandroidx.view.ExtensibleScrollView.ExtensibleScrollView;
import com.kongzue.dialog.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.FullScreenDialog;
import com.kongzue.dialog.v3.InputDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.TipDialog;
import com.kongzue.dialog.v3.WaitDialog;

/**
 * @作者： zjf 2020/5/15 9:17
 * @描述： 弹框工具类 使用单例模式减少过多的使用静态方法
 * <p>
 * 错误：Context上下文变量在构造函数进行了赋值，导致上下文不会改变，弹窗失败
 */
public class DialogUtils {

    private DialogUtils() {
    }

    private static class DialogUtilsInstance {
        public static final DialogUtils INSTANCE = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return DialogUtilsInstance.INSTANCE;
    }

    public interface OnFullScreenDialogBindView {
        void onBind(FullScreenDialog dialog, View rootView);
    }

    //全屏对话框展示,需要处理回调
    public void showFullScreenDialog(Context context, int layoutId, OnFullScreenDialogBindView onFullScreenDialogBindView) {
        FullScreenDialog.show((AppCompatActivity) context, layoutId, new FullScreenDialog.OnBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                if (onFullScreenDialogBindView != null)
                    onFullScreenDialogBindView.onBind(dialog, rootView);
            }
        });
    }

    public interface OnEsvDialogBindView {
        void onBind(@NonNull FullScreenDialog dialog, @NonNull TextView title, @NonNull ExtensibleScrollView esv_content);
    }

    /**
     * 展示扩展scrollView的dialog
     *
     * @param context
     * @param onEsvDialogBindView
     */
    public void showEsvDialog(Context context, OnEsvDialogBindView onEsvDialogBindView) {

        FullScreenDialog.show((AppCompatActivity) context, R.layout.dialog_esv, new FullScreenDialog.OnBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {

                TextView tv_title = new TextView(context);
                int padding = PixelTransformForAppUtil.dip2px(10);
                tv_title.setPadding(padding, 0, padding, padding);
                tv_title.setGravity(Gravity.CENTER);
                tv_title.setText("标题");
                tv_title.setTextColor(context.getResources().getColor(R.color.textColorBlack));
                tv_title.setTypeface(Typeface.DEFAULT_BOLD);
                tv_title.setTextSize(22);
                ExtensibleScrollView esv_content = (ExtensibleScrollView) rootView;
                esv_content.addMyView(tv_title, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                onEsvDialogBindView.onBind(dialog, tv_title, esv_content);
            }
        });
    }

    //全屏对话框展示,不需要处理回调
    public void showFullScreenDialog(Context context, int layoutId) {
        showFullScreenDialog(context, layoutId, null);
    }

    /**
     * 显示提醒消息
     *
     * @param context
     */
    public void showWarningTip(Context context, String msg) {
        showTip(context, TipType.WARNING, msg);
    }

    public void showTip(Context context, TipType tipType, String msg) {
        //默认为成功提示框
        TipDialog.TYPE type = TipDialog.TYPE.SUCCESS;
        switch (tipType) {
            case ERR:
                type = TipDialog.TYPE.ERROR;
                break;
            case WARNING:
                type = TipDialog.TYPE.WARNING;
                break;
        }

        TipDialog.build((AppCompatActivity) context)
                .setTipTime(2000)
                .setMessage(msg)
                .setTip(type)
                .show();
    }

    public void showInputDialog(Context context, String desc, String okButton, OnButtonClickedListener listener) {
        InputDialog.build((AppCompatActivity) context)
                .setCancelButton("取消")
                .setOkButton(okButton, new OnInputDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v, String inputStr) {
                        listener.onClick(inputStr, true);
                        return false;
                    }
                })
                .setTitle("提示")
                .setMessage("写入log数据")
                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                .show();
    }

    /**
     * 显示底部menu
     *
     * @param context
     * @param items
     * @param listener
     */
    public BottomMenu showBottomMenu(Context context, String[] items, OnItemClickListener listener) {
        return BottomMenu.show((AppCompatActivity) context, items, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                listener.onClick(text, index);
            }
        });
    }

    public void showBottomMenu(Context context, String title, String[] items, OnItemClickListener listener) {
        BottomMenu.show((AppCompatActivity) context, title, items, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                listener.onClick(text, index);
            }
        });
    }

    public interface OnItemClickListener {
        void onClick(String text, int index);
    }

    /**
     * 展示消息对话框
     *
     * @param context
     * @param title
     * @param msg
     */
    public void showMessageDialog(Context context, String title, String msg) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle(title)
                .setMessage(msg)
                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                .setOkButton("确定")
                .show();
    }

    public interface OnButtonClickedListener {
        void onClick(String msg, boolean isOkButton);
    }

    public void showMessageDialog(Context context, String title, String msg,
                                  String okButton, String cancelButton, OnButtonClickedListener clickedListener) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle(title)
                .setMessage(msg)
                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                .setOkButton(okButton)
                .setCancelButton(cancelButton)
                .setOnOkButtonClickListener((baseDialog, v) -> {
                    clickedListener.onClick(msg, true);
                    return false;
                })
                .show();
    }

    /**
     * 展示消息对话框
     *
     * @param context
     * @param msg
     */
    public void showMessageDialog(Context context, String msg) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle("提示")
                .setMessage(msg)
                .setOkButton("确定")
                .setStyle(DialogSettings.STYLE.STYLE_IOS)
                .show();
    }

    /**
     * 展示loading框
     *
     * @param context
     * @param loadingMsg
     */
    public void showLoadingDialog(Context context, String loadingMsg) {
        WaitDialog.build((AppCompatActivity) context)
                .setTipTime(1000 * 30)
                .setMessage(loadingMsg)
                .show();
    }

    public void dismissLoadingDialog() {
        WaitDialog.dismiss();
    }
}
