package com.example.developerandroidx.ui.android.dataBinding;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends BaseActivityWithDataBinding<ActivityDataBindingBinding> {

    @Override
    protected int bindLayout() {
        return R.layout.activity_data_binding;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("DataBinding");
    }

    @Override
    protected void initData() {
        super.initData();
        DataBindingViewModel viewModel = getViewModel(DataBindingViewModel.class);
        binding.setDataModel(viewModel);
        viewModel.initData();
    }
}