package com.example.developerandroidx.ui.android.map.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.SportHistoryRcvAdapter;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.service.MapSportService;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.enumPkg.SportType;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/7/3 9:36 AM
 * 参考：
 * 描述：
 */
public class HistoryDialog implements FunctionDialogInterface {
    private MapSportService sportService;

    @Override
    public void show(Context context) {
        DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_sport_history, new DialogUtils.OnFullScreenDialogBindView() {
            @Override
            public void onBind(FullScreenDialog dialog, View rootView) {
                RecyclerView rcv_sport_history = rootView.findViewById(R.id.rcv_sport_history);
                rcv_sport_history.setLayoutManager(new LinearLayoutManager(context));

                SportHistoryRcvAdapter adapter = new SportHistoryRcvAdapter();
                rcv_sport_history.setAdapter(adapter);
//模拟插入数据
//                DB_utils.getInstance().getSportHistoryDB()
//                        .getSportHistoryDao()
//                        .insert(new SportHistory("2020-07-03", new Date().getTime() - 60 * 60 * 1000, new Date().getTime(), 2890, 0, Constant.Common.RIDING))
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new CompletableObserver() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                LogUtils.e("运动数据插入", "完成");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//                        });

                DB_utils.getInstance().getSportHistoryDB().getSportHistoryDao().getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<SportHistory>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<SportHistory> sportHistoryList) {
                                adapter.setList(sportHistoryList);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                        List<SportHistory> sportHistoryList = (List<SportHistory>) adapter.getData();
                        //清空上次查询数据
                        sportService.points.clear();
                        sportService.initProcessOption(
                                sportHistoryList.get(position).sportType == Constant.Common.RIDING ? SportType.RIDING : SportType.STEP);
                        sportService.queryHistoryTrace(sportHistoryList.get(position).startTime, sportHistoryList.get(position).endTime, 1);
                        dialog.doDismiss();
                    }
                });
            }
        });
    }

    public void show(Context context, MapSportService sportService) {
        this.sportService = sportService;
        show(context);
    }
}
