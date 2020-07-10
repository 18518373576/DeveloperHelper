package com.example.developerandroidx.utils;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.developerandroidx.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者： zjf 2020/5/18 10:03
 * @参考：
 * @描述：
 */
public class StringUtils {
    private StringUtils() {
    }

    private static class StringUtilsInstance {
        public static final StringUtils INSTANCE = new StringUtils();
    }

    public static StringUtils getInstance() {
        return StringUtilsInstance.INSTANCE;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getCurrentTime() {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyMMdd HH:mm:ss SSS");
        return format.format(new Date());
    }

    public String getCurrentTime(String formatStr) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public String getFormatTime(long time, String format) {
        return new SimpleDateFormat(format).format(time);
    }

    /**
     * 判断一组字符串是否有空值
     *
     * @param strings 检查内容
     * @return
     */
    public boolean isHasNull(String... strings) {
        for (String str : strings) {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public interface CallBack<T> {
        void onFail(String msg);

        void onSuc(T t);
    }

    /**
     * 获取百度地图自定义样式路径
     *
     * @param customStyleFileName 文件名
     * @return
     */
    public void getCustomStyleFilePath(String customStyleFileName, CallBack<String> callBack) {

        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                FileOutputStream outputStream = null;
                InputStream inputStream = null;
                String parentPath = null;
                try {
                    inputStream = App.context.getAssets().open("customConfigdir/" + customStyleFileName);
                    byte[] buffer = new byte[inputStream.available()];
                    inputStream.read(buffer);

                    parentPath = App.context.getFilesDir().getAbsolutePath();
                    File customStyleFile = new File(parentPath + "/" + customStyleFileName);
                    if (customStyleFile.exists()) {
                        customStyleFile.delete();
                    }
                    customStyleFile.createNewFile();

                    outputStream = new FileOutputStream(customStyleFile);
                    outputStream.write(buffer);
                } catch (IOException e) {
                    emitter.onError(e);
                    Log.e("CustomMapDemo", "Copy custom style file failed", e);
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        Log.e("CustomMapDemo", "Close stream failed", e);
                    }
                }
                emitter.onSuccess(parentPath + "/" + customStyleFileName);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(String s) {
                        callBack.onSuc(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }
                });
    }

    /**
     * 获取倒计时
     *
     * @param l 单位秒
     * @return 00:00:00
     */
    public String getTime(int l) {
        StringBuffer buffer = new StringBuffer();

        int hour = l / 3600;
        int minute = (l % 3600) / 60;
        int sec = (l % 3600) % 60;
        buffer.append(hour >= 10 ? hour : "0" + hour).append(":")
                .append(minute >= 10 ? minute : "0" + minute).append(":")
                .append(sec >= 10 ? sec : "0" + sec);
        return buffer.toString();
    }

    /**
     * 获取距离
     *
     * @param distance 单位米
     * @return 单位千米
     */
    public String getDistance(float distance) {
        return String.format("%.2f", distance / 1000f);
    }

    /**
     * 获取步数,前面补0
     *
     * @param steps
     * @return
     */
    public String getSteps(int steps) {
        return String.format("%05d", steps);
    }

    /**
     * 获取app版本名称
     */
    public String getVersionName() {
        String verName = "";
        try {
            verName = App.context.getPackageManager().
                    getPackageInfo(App.context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return verName;
    }

}
