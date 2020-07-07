package com.example.developerandroidx.projectInterface;

/**
 * 作者： zjf 2020/6/23 2:34 PM
 * 参考：
 * 描述：
 */
public interface CallBack<T> {
    void onFail(String msg);

    void onSuc(T t);
}
