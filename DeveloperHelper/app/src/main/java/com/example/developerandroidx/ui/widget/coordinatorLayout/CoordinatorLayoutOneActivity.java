package com.example.developerandroidx.ui.widget.coordinatorLayout;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.ArithmeticRcvAdapter;
import com.example.developerandroidx.base.BaseActivity;
import com.example.developerandroidx.model.ArithMeticModel;
import com.example.developerandroidx.ui.java.arithmetic.ArithmeticViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考:https://blog.csdn.net/shanshui911587154/article/details/85115340
 */
public class CoordinatorLayoutOneActivity extends BaseActivity {


    private ArithmeticRcvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_one);
        initView();

        initData();
    }

    private void initView() {
        RecyclerView rcv_coordinator = findViewById(R.id.rcv_coordinator);
        rcv_coordinator.setLayoutManager(new LinearLayoutManager(context));
        //使用算法里面的adapter填充
        adapter = new ArithmeticRcvAdapter(new ArrayList<>());
        rcv_coordinator.setAdapter(adapter);
//        findViewById(R.id.iv_back).setOnClickListener(v -> finish());
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