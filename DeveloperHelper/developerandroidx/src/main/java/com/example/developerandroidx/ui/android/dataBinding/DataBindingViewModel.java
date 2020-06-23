package com.example.developerandroidx.ui.android.dataBinding;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.WeatherModel;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.httpRequest.HttpRequestUtil;
import com.example.developerandroidx.utils.httpRequest.RequestCallBack;
import com.example.developerandroidx.utils.httpRequest.RequestLibrary;
import com.example.developerandroidx.utils.httpRequest.rxJavaRequest.CallBack;
import com.example.developerandroidx.utils.httpRequest.rxJavaRequest.RxJavaRequestUtil;

/**
 * 作者： zjf 2020/6/23 10:19 AM
 * 参考：
 * 描述：
 */
public class DataBindingViewModel extends BaseViewModel {
    public MutableLiveData<String> location = new MutableLiveData<>("郑州");
    public MutableLiveData<String> time = new MutableLiveData<>(StringUtils.getInstance().getCurrentTime("yyyy-MM-dd") + " 星期二");
    public MutableLiveData<Integer> image = new MutableLiveData<>(R.mipmap.icon_qing);

    @Override
    protected void initData(@Nullable String... param) {
        RxJavaRequestUtil.getInstance().getWeather(new CallBack<WeatherModel>() {
            @Override
            public void onFail(String msg) {

            }

            @Override
            public void onSuc(WeatherModel weatherModel) {
                location.setValue("haha");
            }
        });
    }

    public void onClick() {
    }
}
