package com.example.developerandroidx.ui.android.map;

import android.view.View;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityBaiDuMapBinding;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;

public class BaiDuMapActivity extends BaseActivityWithDataBinding<ActivityBaiDuMapBinding> implements View.OnClickListener {


    private BaiDuMapViewModel viewModel;

    @Override
    protected int bindLayout() {
        return R.layout.activity_bai_du_map;
    }

    @Override
    protected void initView() {
        super.initView();
        setNativeStatusBar(StateBarType.TRAN);
    }

    @Override
    protected void initData() {
        super.initData();
        //绑定viewModel
        viewModel = getViewModel(BaiDuMapViewModel.class);
        viewModel.initData();
        binding.setModel(viewModel);
        binding.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时必须调用mMapView. onResume ()
        binding.mvMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时必须调用mMapView. onPause ()
        binding.mvMap.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        binding.mvMap.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_coverage:
                DialogUtils.getInstance().showBottomMenu(context,
                        new String[]{Constant.Common.LIGHT_STYLE, Constant.Common.NIGHT_STYLE,
                                Constant.Common.DEFAULT_STYLE, Constant.Common.MAP_3D},
                        new DialogUtils.OnItemClickListener() {
                            @Override
                            public void onClick(String text, int index) {
                                viewModel.setMapCustomStylePath(text);
                            }
                        });
                break;
        }
    }
}