package com.example.developerandroidx.ui.android.dataBinding;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.model.WeatherModel;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.utils.httpRequest.rxJavaRequest.CallBack;
import com.example.developerandroidx.utils.httpRequest.rxJavaRequest.RxJavaRequestUtil;
import com.example.developerandroidx.view.loadingView.LoadingState;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： zjf 2020/6/23 10:19 AM
 * 参考：
 * 描述：
 */
public class DataBindingViewModel extends BaseViewModel<WeatherModel> {
    //loadingView
    public MutableLiveData<LoadingState> loadingState = new MutableLiveData<>(LoadingState.ON_LOADING);
    public MutableLiveData<Integer> failImageId = new MutableLiveData<>(R.mipmap.icon_404);
    public MutableLiveData<String> noDataMsg = new MutableLiveData<>("暂无数据");
    //界面元素
    public MutableLiveData<String> location = new MutableLiveData<>("郑州");
    public MutableLiveData<String> time = new MutableLiveData<>(StringUtils.getInstance().getCurrentTime("yyyy-MM-dd"));
    public MutableLiveData<Integer> image = new MutableLiveData<>(R.mipmap.icon_qing);
    public MutableLiveData<String> airQuality = new MutableLiveData<>("良");
    public MutableLiveData<String> temperature = new MutableLiveData<>("20℃~29℃");
    public MutableLiveData<String> windSpeed = new MutableLiveData<>("<3级");
    public MutableLiveData<String> windDirection = new MutableLiveData<>("东北风");

    @Override
    protected void initData(@Nullable String... param) {
        getWeatherData();
    }

    /**
     * 请求天气数据
     */
    private void getWeatherData() {
        //请求数据
        RxJavaRequestUtil.getInstance().getWeather(new CallBack<WeatherModel>() {
            @Override
            public void onFail(String msg) {
                loadingState.setValue(LoadingState.LOADING_FAIL);
            }

            @Override
            public void onSuc(WeatherModel weatherModel) {
                setData(weatherModel);

                WeatherModel.DataBean today = weatherModel.getData().get(0);
                loadingState.setValue(LoadingState.LOADING_SUC);

                location.setValue(weatherModel.getCity());
                time.setValue(today.getDate() + " " + today.getWeek());
                image.setValue(Constant.getWeatherIconMap().get(today.getWea_img()));
                airQuality.setValue(today.getAir_level());
                temperature.setValue(today.getTem());
                windSpeed.setValue(today.getWin_speed());
                windDirection.setValue(today.getWin().get(0));
            }
        });
    }
}
