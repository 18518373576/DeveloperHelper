package com.example.developerandroidx.ui.android.dataBinding;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.WeatherRcvAdapter;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityDataBindingBinding;
import com.example.developerandroidx.ui.android.dataBinding.dialog.ChooseCityDialog;

public class DataBindingActivity extends BaseActivityWithDataBinding<ActivityDataBindingBinding> {

    @Override
    protected int bindLayout() {
        return R.layout.activity_data_binding;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("DataBinding");
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setOnClickListener(v -> new ChooseCityDialog().show(context));
    }

    @Override
    protected void initData() {
        super.initData();
        DataBindingViewModel viewModel = getViewModel(DataBindingViewModel.class);
        binding.setDataModel(viewModel);
        binding.setLinearLayoutManager(new LinearLayoutManager(context));
        WeatherRcvAdapter adapter = new WeatherRcvAdapter();
        binding.setAdapter(adapter);
        viewModel.getData().observe(this, weatherModel -> adapter.setList(weatherModel.getData()));
    }
}