package com.example.developerandroidx.ui.android.map;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.utils.enumPkg.SportType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    //默认经纬度
    private double lat = Double.parseDouble(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.LOCATION_LAT, "34.78084"));
    private double lon = Double.parseDouble(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.LOCATION_LON, "113.702818"));
    //地图位置展示模式图标
    private int[] locationIcons = new int[]{R.mipmap.icon_current_location_gray, R.mipmap.icon_current_location, R.mipmap.icon_compass};
    private MyLocationConfiguration.LocationMode[] locationModes =
            new MyLocationConfiguration.LocationMode[]{MyLocationConfiguration.LocationMode.NORMAL,
                    MyLocationConfiguration.LocationMode.FOLLOWING,
                    MyLocationConfiguration.LocationMode.COMPASS};
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
    //地图初始化设置
    public MutableLiveData<Boolean> init = new MutableLiveData<>(true);
    //当前位置信息
    public MutableLiveData<MyLocationData> myLocation = new MutableLiveData<>();
    //地图上画线的点
    public MutableLiveData<List<LatLng>> overlayPoints = new MutableLiveData<>();
    //当前位置模式图标
    public MutableLiveData<Integer> myLocationIcon = new MutableLiveData<>(
            locationIcons[PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.LOCATION_MODE, 0)]);
    public MutableLiveData<MyLocationConfiguration.LocationMode> myLocationIconMode = new MutableLiveData<>(
            locationModes[PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.LOCATION_MODE, 0)]);
    //底部按钮图标资源
    public MutableLiveData<Integer> playAndStopIcon = new MutableLiveData<>(R.mipmap.icon_play);
    //计时字段展示内容
    public MutableLiveData<String> time = new MutableLiveData<>("00:00:00");
    //当为骑行是为运动距离,步行时为运动步数
    public MutableLiveData<String> sportTitle = new MutableLiveData<>("运动距离");
    //运动的距离或步数
    public MutableLiveData<String> stepOrDistance = new MutableLiveData<>("0000");
    //信号强度,精度<20米为强
    public MutableLiveData<Boolean> gpsStrength = new MutableLiveData<>(false);

    /**
     * 初始化数据
     *
     * @param param 数据类型，根据此参数确认获取的数据，和访问接口的参数
     */
    @Override
    protected void initData(@Nullable String... param) {
        //展示默认位置
        LogUtils.e("默认的点", lat + "#" + lon);
        if (PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.IS_SPORTING)) {
            setMapStatusUpdate(19f, -45f, lat, lon);
        } else {
            setMapStatusUpdate(16f, -45f, lat, lon);
        }
        setMapCustomStylePath(Constant.Common.LIGHT_STYLE);
    }

    public void setSportTitle(SportType sportType) {
        switch (sportType) {
            case STEP:
                sportTitle.setValue("运动步数");
                break;
            case RIDING:
                sportTitle.setValue("运动距离");
                break;
        }
    }

    public void setStepOrDistance(String stepOrDistanceNum) {
        stepOrDistance.setValue(stepOrDistanceNum);
    }

    /**
     * 设置playAndStopIcon
     */
    public void setPlayAndStopIcon(boolean isPalying) {
        playAndStopIcon.setValue(isPalying ? R.mipmap.icon_stop : R.mipmap.icon_play);
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
     * 含义为,以哪个点为圆心展示地图
     */
    public void setMapStatusUpdate(float zoom, float overlook, double centerLat, double centerLon) {
        // 构建地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        // 中心点设置为颐和园
        LatLng center = new LatLng(centerLat, centerLon);
        // 缩放级别
        builder.target(center).zoom(zoom).overlook(overlook);
        // 更新地图
        mapStatusUpdate.setValue(MapStatusUpdateFactory.newMapStatus(builder.build()));
        //开启3D建筑
        buildingsEnabled.setValue(true);
    }

    /**
     * 设置地图缩放比例
     */
    public void setMapStatusZoomUpdate(float zoom) {
        // 构建地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        // 缩放级别
        builder.zoom(zoom);
        // 更新地图
        mapStatusUpdate.setValue(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    /**
     * 设置地图旋转角度
     *
     * @param rotate
     */
    public void setMapStatusRotateUpdate(float rotate) {
        // 构建地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        // 缩放级别
        builder.rotate(rotate);
        // 更新地图
        mapStatusUpdate.setValue(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    /**
     * 更新地图上的轨迹
     */
    public void setOverlayPoints(List<LatLng> points) {
        overlayPoints.setValue(points);
    }

    /**
     * 设置和更新当前位置,当前位置图标在地图上的展示的位置
     * 含义为,设置地图上展示当前位置的圆点在地图上的展示位置
     *
     * @param mCurrentLat
     * @param mCurrentLon
     * @param mCurrentAccracy
     * @param mCurrentDirection
     */
    public void setMyLocation(double mCurrentLat, double mCurrentLon, float mCurrentAccracy, float mCurrentDirection) {

        if (mCurrentAccracy < 20) {
            gpsStrength.setValue(true);
        } else {
            gpsStrength.setValue(false);
        }
        MyLocationData myLocationData = new MyLocationData.Builder()
                .accuracy(mCurrentAccracy)// 设置定位数据的精度信息，单位：米
                .direction(mCurrentDirection)// 此处设置开发者获取到的方向信息，顺时针0-360
                .latitude(mCurrentLat)
                .longitude(mCurrentLon)
                .build();
        myLocation.setValue(myLocationData);
    }

    /**
     * 回到当前位置,以当前位置为圆心的地图展示
     * 此方法的正确含义应为设置当前位置模式,普通模式,跟随模式,罗盘模式
     * 此方法为android:id="@+id/iv_my_location"的点击事件,点击的时候切换下一个展示模式
     */
    public void showMyLocation() {
        //默认为普通模式
        //0,普通模式,1跟随模式,2罗盘模式
        int locationMode = PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.LOCATION_MODE, 0);
        int nextMode = (locationMode + 1) % 3;
        if (nextMode != 2) {
            //不是罗盘模式的时候,恢复旋转为0,就是上北下南
            setMapStatusRotateUpdate(0f);
        }
        myLocationIcon.setValue(locationIcons[nextMode]);
        myLocationIconMode.setValue(locationModes[nextMode]);
        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.LOCATION_MODE, nextMode);
    }

    /**
     * 以运动轨迹的起点为圆心展示地图
     * 此时,当前位置模式必须为普通模式
     */
    public void showSportStartPoint(double centerLat, double centerLon) {
        setMapStatusUpdate(16f, -45f, centerLat, centerLon);

        //设置当前位置模式为普通模式
        myLocationIcon.setValue(locationIcons[0]);
        myLocationIconMode.setValue(locationModes[0]);
        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.LOCATION_MODE, 0);
    }

    /**
     * 更新计时时间
     */
    public void setTimer(String timer) {
        time.setValue(timer);
    }
}
