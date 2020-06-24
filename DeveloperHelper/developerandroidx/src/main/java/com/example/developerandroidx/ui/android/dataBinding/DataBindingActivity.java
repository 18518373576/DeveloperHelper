package com.example.developerandroidx.ui.android.dataBinding;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.developerandroidx.R;
import com.example.developerandroidx.adapter.quickAdapter.WeatherRcvAdapter;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityDataBindingBinding;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.City;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        DB_utils.getInstance().getCitiesDatabase().getCitiesDao().queryByCityName("北京")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<City>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<City> cities) {
                        DialogUtils.getInstance().showMessageDialog(context, cities.size()+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        DialogUtils.getInstance().showMessageDialog(context, e.getMessage());
                        LogUtils.e("异常", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        DataBindingViewModel viewModel = getViewModel(DataBindingViewModel.class);
        binding.setDataModel(viewModel);
        binding.setLinearLayoutManager(new LinearLayoutManager(context));
        WeatherRcvAdapter adapter = new WeatherRcvAdapter();
        binding.setAdapter(adapter);
        viewModel.getData().observe(this, weatherModel -> adapter.setList(weatherModel.getData()));
    }
}