package com.example.developerandroidx.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.developerandroidx.base.App;

/**
 * Date: 2020/5/23 7:39
 * 参考:
 * 描述:
 */
public class PreferenceUtils {
    private SharedPreferences preferences;

    private PreferenceUtils() {
        /**
         * name：命名
         * mode：模式，包括
         * MODE_PRIVATE（只能被自己的应用程序访问）
         * MODE_WORLD_READABLE（除了自己访问外还可以被其它应该程序读取）
         * MODE_WORLD_WRITEABLE（除了自己访问外还可以被其它应该程序读取和写入）
         *
         */
        preferences = App.context.getSharedPreferences(Constant.PreferenceKeys.MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    private static class PreferenceUtilsInstance {
        public static final PreferenceUtils INSTANCE = new PreferenceUtils();
    }

    public static PreferenceUtils getInstance() {
        return PreferenceUtilsInstance.INSTANCE;
    }

    /**
     * 存储布尔类型值
     *
     * @param value
     */
    public void putBooleanValue(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    /**
     * @param key
     * @return
     */
    public boolean getBooleanValue(String key) {
        return preferences.getBoolean(key, false);
    }

    /**
     * 存储字符串
     *
     * @param key
     * @param value
     */
    public void putStringValue(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    /**
     * 获取存储的字符串
     *
     * @param key
     * @return
     */
    public String getStringValue(String key) {
        return preferences.getString(key, "");
    }

    public String getStringValue(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    /**
     * 存储整形
     *
     * @param key
     * @param value
     */
    public void putIntValue(String key, Integer value) {
        preferences.edit().putInt(key, value).apply();
    }

    /**
     * 获取存储的字符串
     *
     * @param key
     * @return
     */
    public Integer getIntValue(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }
}
