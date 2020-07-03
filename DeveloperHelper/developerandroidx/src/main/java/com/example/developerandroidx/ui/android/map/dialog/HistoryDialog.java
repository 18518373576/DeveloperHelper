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
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.projectInterface.FunctionDialogInterface;
import com.example.developerandroidx.service.MapSportService;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                List<SportHistory> sportHistoryList = new ArrayList<>();
                long start = (new Date().getTime()) - 60 * 60 * 3 * 1000;
                long end = (new Date().getTime()) - 60 * 60 * 1000;
                sportHistoryList.add(new SportHistory(0, "2020-07-03", start, end, 1000, 1000, Constant.Common.RIDING, 1000));
                sportHistoryList.add(new SportHistory(0, "2020-07-03", start, end, 1000, 1000, Constant.Common.STEP, 1000));
                sportHistoryList.add(new SportHistory(0, "2020-07-03", start, end, 1000, 1000, Constant.Common.RIDING, 1000));
                SportHistoryRcvAdapter adapter = new SportHistoryRcvAdapter(sportHistoryList);
                rcv_sport_history.setAdapter(adapter);

                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
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
