package com.example.developerandroidx.ui.android.sqlite;

import androidx.annotation.Nullable;

import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.Message;
import com.example.developerandroidx.utils.LogUtils;

import java.util.Date;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/6/20 7:01 PM
 * 参考：
 * 描述：
 */
public class DbControlViewModel extends BaseViewModel<List<Message>> {
    @Override
    protected void initData(@Nullable String... param) {
        DB_utils.getInstance().getDB().getMessageDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Message>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Message> messageList) {
                        setData(messageList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insert(Message message) {
        DB_utils.getInstance().getDB().getMessageDao()
                .insert(message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("数据插入", "完成");
                        initData((String) null);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
