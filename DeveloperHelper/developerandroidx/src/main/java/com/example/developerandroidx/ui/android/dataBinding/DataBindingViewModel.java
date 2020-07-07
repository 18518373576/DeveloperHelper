package com.example.developerandroidx.ui.android.dataBinding;

import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.City;
import com.example.developerandroidx.model.WeatherModel;
import com.example.developerandroidx.realize.SampleTextWatcher;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.projectInterface.CallBack;
import com.example.developerandroidx.utils.httpRequest.rxJavaRequest.RxJavaRequestUtil;
import com.example.developerandroidx.view.loadingView.LoadingState;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/6/23 10:19 AM
 * 参考：
 * 描述：
 */
public class DataBindingViewModel extends BaseViewModel<WeatherModel> {
    /**
     * loadingView,自定义属性
     * {@link com.example.developerandroidx.utils.bindingAdapter.ViewBindingAdapter}
     */
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

    /**
     * dialog元素,弹框和activity公用viewModel
     * {@link com.example.developerandroidx.ui.android.dataBinding.dialog.ChooseCityDialog}
     */
    public MutableLiveData<List<City>> searchCities = new MutableLiveData<>();
    public TextWatcher watcher;

    @Override
    protected void initData(@Nullable String... param) {
        /**
         * 为{@link com.example.developerandroidx.ui.android.dataBinding.dialog.ChooseCityDialog}的
         * 搜索框添加TextWatcher,监听输入数据
         */
        initTextWatcher();
        //默认加载cityId(郑州)
        getWeatherData("101180101");
    }

    /**
     * 定义editText输入监听
     */
    private void initTextWatcher() {
        watcher = new SampleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtils.e("onTextChanged", String.valueOf(s));
                if (TextUtils.isEmpty(s.toString())) {
                    searchCities.setValue(new ArrayList<>());
                    return;
                }
                //根据输入数据查询数据库
                DB_utils.getInstance().getCityDB().getCitiesDao().queryByCityName(String.valueOf(s))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<City>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<City> cities) {
                                searchCities.setValue(cities);
                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtils.e(DataBindingViewModel.this.getClass().getName(), e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        };
    }

    /**
     * 请求天气数据
     */
    private void getWeatherData(String cityId) {
        //请求数据
        RxJavaRequestUtil.getInstance().getWeather(cityId, new CallBack<WeatherModel>() {
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

    public void reLoadWeatherData(String cityId) {
        loadingState.setValue(LoadingState.ON_LOADING);
        getWeatherData(cityId);
    }
}
