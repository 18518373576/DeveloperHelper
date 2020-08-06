package com.example.developerandroidx.utils.httpRequest.rxJavaRequest;

import com.example.developerandroidx.model.WeatherModel;
import com.example.developerandroidx.projectInterface.CallBack;
import com.example.developerandroidx.utils.api.Api;
import com.example.developerandroidx.utils.api.RxJavaApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： zjf 2020/6/23 2:26 PM
 * 参考：
 * 描述：
 */
public class RxJavaRequestUtil {
    private final Retrofit retrofit;
    private final RxJavaApi rxJavaApi;

    private RxJavaRequestUtil() {
        //创建Retrofit对象
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //获取接口请求实例
        rxJavaApi = retrofit.create(RxJavaApi.class);
    }

    private static class Instance {
        public static RxJavaRequestUtil INSTANCE = new RxJavaRequestUtil();
    }

    public static RxJavaRequestUtil getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * 获取天气信息
     *
     * @param callBack
     */
    public void getWeather(String cityId, CallBack<WeatherModel> callBack) {
        rxJavaApi.getWeather(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherModel weatherModel) {
                        callBack.onSuc(weatherModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
