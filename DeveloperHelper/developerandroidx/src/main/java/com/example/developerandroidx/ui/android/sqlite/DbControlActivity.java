package com.example.developerandroidx.ui.android.sqlite;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.MessageRcvAdapter;
import com.example.developerandroidx.base.App;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.Message;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.view.swipeAction.QMUIRVItemSwipeAction;
import com.example.developerandroidx.view.swipeAction.QMUISwipeAction;
import com.kongzue.dialog.v3.FullScreenDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DbControlActivity extends BaseActivity {

    @BindView(R.id.rcv_message)
    RecyclerView rcvMessage;

    @Override
    protected int bindLayout() {
        return R.layout.activity_db_control;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("Database Control");
        rcvMessage.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData() {
        super.initData();
        MessageRcvAdapter adapter = new MessageRcvAdapter();
        rcvMessage.setAdapter(adapter);
        DbControlViewModel viewModel = (DbControlViewModel) getViewModel(this, DbControlViewModel.class);
        viewModel.getData().observe(this, adapter::setList);

        //item滑动action
        QMUIRVItemSwipeAction swipeAction = new QMUIRVItemSwipeAction(true, new QMUIRVItemSwipeAction.Callback() {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                adapter.removeAt(viewHolder.getAdapterPosition());
            }

            @Override
            public int getSwipeDirection(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return QMUIRVItemSwipeAction.SWIPE_LEFT;
            }

            @Override
            public void onClickAction(QMUIRVItemSwipeAction swipeAction, RecyclerView.ViewHolder selected, QMUISwipeAction action) {
                super.onClickAction(swipeAction, selected, action);
                Toast.makeText(context,
                        "你点击了第 " + selected.getAdapterPosition() + " 个 item 的" + action.getText(),
                        Toast.LENGTH_SHORT).show();
                if (action == adapter.mDeleteAction) {
                    adapter.removeAt(selected.getAdapterPosition());
                } else {
                    swipeAction.clear();
                }
            }
        });
        swipeAction.attachToRecyclerView(rcvMessage);
    }

    @OnClick({R.id.iv_add})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                DialogUtils.getInstance().showFullScreenDialog(context, R.layout.dialog_add_message, new DialogUtils.OnFullScreenDialogBindView() {
                    @Override
                    public void onBind(FullScreenDialog dialog, View rootView) {

                    }
                });
                break;
        }
    }
}