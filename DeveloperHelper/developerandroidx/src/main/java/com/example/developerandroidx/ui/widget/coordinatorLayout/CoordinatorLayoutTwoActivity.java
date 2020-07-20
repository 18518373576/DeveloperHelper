package com.example.developerandroidx.ui.widget.coordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.ArithmeticRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.model.ArithMeticModel;
import com.example.developerandroidx.ui.java.arithmetic.ArithmeticViewModel;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考:https://blog.csdn.net/shanshui911587154/article/details/85115340
 */
public class CoordinatorLayoutTwoActivity extends BaseActivity {


    private ArithmeticRcvAdapter adapter;
    private View toolbar;
    private View iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_two);
        initView();
        initData();
    }

    //设置系统状态栏透明
    @Override
    protected void initStatusBar() {
        setNativeStatusBar(StateBarType.TRAN);
    }

    private void initView() {
        RecyclerView rcv_coordinator = findViewById(R.id.rcv_coordinator);
        rcv_coordinator.setLayoutManager(new LinearLayoutManager(context));
        //使用算法里面的adapter填充
        adapter = new ArithmeticRcvAdapter(new ArrayList<>());
        rcv_coordinator.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);

        iv_back = findViewById(R.id.iv_back);

        setViewOffset();

        CollapsingToolbarLayout toolbar_layout = findViewById(R.id.toolbar_layout);

        //设置展开时的字体
        toolbar_layout.setExpandedTitleTextAppearance(R.style.TitleText_big);
        toolbar_layout.setCollapsedTitleTextAppearance(R.style.TitleText);
    }

    private void setViewOffset() {
        //设置toolbar的margin值,为系统状态栏的高度
        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.titleBarHeight));
        params.topMargin = PixelTransformForAppUtil.getStatusBarHeight();
        toolbar.setLayoutParams(params);
        //返回按钮下移状态栏的高度
        iv_back.setTranslationY(PixelTransformForAppUtil.getStatusBarHeight());
    }

    protected void initData() {
        ArithmeticViewModel viewModel = getViewModel(ArithmeticViewModel.class);
        viewModel.getData().observe(this, new Observer<List<ArithMeticModel>>() {
            @Override
            public void onChanged(List<ArithMeticModel> meticModels) {
                adapter.notifyDataChanged(meticModels);
            }
        });
    }
}