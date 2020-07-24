package com.example.developerandroidx.ui.java;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.FunctionRcvAdapter;
import com.example.developerandroidx.base.BaseFragmentWithButterKnife;
import com.example.developerandroidx.base.BaseRcvAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class JavaFragment extends BaseFragmentWithButterKnife {

    @BindView(R.id.rcv_java)
    RecyclerView rcv_java;

    private JavaViewModel viewModel;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_java;
    }

    @Override
    protected void initView() {
        rcv_java.setLayoutManager(new GridLayoutManager(context, 2));
    }

    @Override
    protected void initData() {
        viewModel = getViewModel(this, JavaViewModel.class);
        viewModel.getAdapterList().observe(getViewLifecycleOwner(), functionList ->
                rcv_java.setAdapter(new FunctionRcvAdapter(functionList)));
    }

    @Override
    public void onDestroy() {
        BaseRcvAdapter.releaseAllHolder(rcv_java);
        super.onDestroy();
    }
}
