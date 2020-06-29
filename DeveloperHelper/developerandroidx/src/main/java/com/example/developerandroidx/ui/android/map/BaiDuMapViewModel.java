package com.example.developerandroidx.ui.android.map;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.StringUtils;

/**
 * 作者： zjf 2020/6/28 9:35 AM
 * 参考：
 * 描述：
 */
public class BaiDuMapViewModel extends BaseViewModel<String> {
    // 用于设置个性化地图的样式文件
    private static final String CUSTOM_FILE_NAME_LIGHT = "BaiDuMapStyleLight.sty";
    private static final String CUSTOM_FILE_NAME_NIGHT = "BaiDuMapStyleNight.sty";
    private String fileName = CUSTOM_FILE_NAME_LIGHT;
    private String currentStyle = "";

    //----------------界面元素绑定数据-----------------
    //初始化地图位置
    public MutableLiveData<MapStatusUpdate> mapStatusUpdate = new MutableLiveData<>();
    //设置自定义主题路径
    public MutableLiveData<String> mapCustomStylePath = new MutableLiveData<>();
    //是否展示交通图
    public MutableLiveData<Boolean> trafficEnabled = new MutableLiveData<>(PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.TRAFFIC_ENABLED));
    //是否可以旋转
    public MutableLiveData<Boolean> rotateEnable = new MutableLiveData<>(false);
    //是否开启俯视
    public MutableLiveData<Boolean> overlookEnable = new MutableLiveData<>(true);
    //是否开启3D建筑
    public MutableLiveData<Boolean> buildingsEnabled = new MutableLiveData<>(false);
    //交通图是否展示图标
    public MutableLiveData<Integer> trafficLightIcon = new MutableLiveData<>(
            !PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.TRAFFIC_ENABLED) ? R.mipmap.icon_traffic_light_gray : R.mipmap.icon_traffic_light);
    //是否开启当前位置定位图层
    public MutableLiveData<Boolean> myLocationEnabled = new MutableLiveData<>(true);
    //当前位置信息
    public MutableLiveData<MyLocationData> myLocation = new MutableLiveData<>();

    /**
     * 初始化数据
     *
     * @param param 数据类型，根据此参数确认获取的数据，和访问接口的参数
     */
    @Override
    protected void initData(@Nullable String... param) {
        //展示默认位置
        setMapStatusUpdate(16f, -45f, 34.78084, 113.702818);
        setMapCustomStylePath(Constant.Common.LIGHT_STYLE);
    }

    /**
     * 实时交通状况切换
     */
    public void toggleTrafficEnabled() {
        boolean isTrafficEnabled = trafficEnabled.getValue();
        trafficLightIcon.setValue(isTrafficEnabled ? R.mipmap.icon_traffic_light_gray : R.mipmap.icon_traffic_light);
        trafficEnabled.setValue(!isTrafficEnabled);
        PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.TRAFFIC_ENABLED, !isTrafficEnabled);
    }

    /**
     * 设置地图自定义样式
     */
    public void setMapCustomStylePath(String style) {
        if (currentStyle.equals(style)) {
            return;
        } else {
            currentStyle = style;
        }
        switch (style) {
            case Constant.Common.LIGHT_STYLE:
                fileName = CUSTOM_FILE_NAME_LIGHT;
                break;
            case Constant.Common.NIGHT_STYLE:
                fileName = CUSTOM_FILE_NAME_NIGHT;
                break;
            case Constant.Common.DEFAULT_STYLE:
                mapCustomStylePath.setValue("");
                return;
        }
        StringUtils.getInstance().getCustomStyleFilePath(fileName, new StringUtils.CallBack<String>() {
            @Override
            public void onFail(String msg) {
                LogUtils.e("加载自定义样式错误", msg);
            }

            @Override
            public void onSuc(String s) {
                mapCustomStylePath.setValue(s);
            }
        });
    }

    /**
     * 初始化地图数据,展示圆心位置和缩放比例
     */
    public void setMapStatusUpdate(float zoom, float overlook, double lat, double lon) {
        // 构建地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        // 中心点设置为颐和园
        LatLng center = new LatLng(lat, lon);
        // 缩放级别
        builder.target(center).zoom(zoom).overlook(overlook);
        // 更新地图
        mapStatusUpdate.setValue(MapStatusUpdateFactory.newMapStatus(builder.build()));
        //开启3D建筑
        buildingsEnabled.setValue(true);
    }

    /**
     * 设置和更新当前位置
     *
     * @param mCurrentLat
     * @param mCurrentLon
     * @param mCurrentAccracy
     * @param mCurrentDirection
     */
    public void setMyLocation(double mCurrentLat, double mCurrentLon, float mCurrentAccracy, float mCurrentDirection) {
        MyLocationData myLocationData = new MyLocationData.Builder()
                .accuracy(mCurrentAccracy)// 设置定位数据的精度信息，单位：米
                .direction(mCurrentDirection)// 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(mCurrentLat)
                .longitude(mCurrentLon)
                .build();
        myLocation.setValue(myLocationData);
    }
}
