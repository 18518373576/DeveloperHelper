package com.example.developerandroidx.db;

import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.projectInterface.CallBack;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/7/9 9:26 AM
 * 参考：
 * 描述：
 */
public class DaoUtils {

    public static void getAllSportData(CallBack<List<SportHistory>> callBack) {
        DB_utils.getInstance().getSportHistoryDB().getSportHistoryDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SportHistory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SportHistory> sportHistoryList) {
                        callBack.onSuc(sportHistoryList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
