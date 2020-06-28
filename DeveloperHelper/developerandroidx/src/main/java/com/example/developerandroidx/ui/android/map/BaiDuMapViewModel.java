package com.example.developerandroidx.ui.android.map;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseViewModel;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
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
    public MutableLiveData<Boolean> trafficEnabled = new MutableLiveData<>(false);
    //是否可以旋转
    public MutableLiveData<Boolean> rotateEnable = new MutableLiveData<>(false);
    //是否开启俯视
    public MutableLiveData<Boolean> overlookEnable = new MutableLiveData<>(false);
    //是否开启3D建筑
    public MutableLiveData<Boolean> buildingsEnabled = new MutableLiveData<>(false);
    //交通图是否展示图标
    public MutableLiveData<Integer> trafficLightIcon = new MutableLiveData<>(R.mipmap.icon_traffic_light_gray);

    /**
     * 初始化数据
     *
     * @param param 数据类型，根据此参数确认获取的数据，和访问接口的参数
     */
    @Override
    protected void initData(@Nullable String... param) {
        setMapStatusUpdate(16f, 0);
        setMapCustomStylePath(Constant.Common.LIGHT_STYLE);
    }

    /**
     * 实时交通状况切换
     */
    public void toggleTrafficEnabled() {
        boolean isTrafficEnabled = trafficEnabled.getValue();
        trafficLightIcon.setValue(isTrafficEnabled ? R.mipmap.icon_traffic_light_gray : R.mipmap.icon_traffic_light);
        trafficEnabled.setValue(!isTrafficEnabled);
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
                loadDefaultSetting();
                fileName = CUSTOM_FILE_NAME_LIGHT;
                break;
            case Constant.Common.NIGHT_STYLE:
                loadDefaultSetting();
                fileName = CUSTOM_FILE_NAME_NIGHT;
                break;
            case Constant.Common.DEFAULT_STYLE:
                loadDefaultSetting();
                mapCustomStylePath.setValue("");
                return;
            case Constant.Common.MAP_3D:
                setMapStatusUpdate(16.0f, -45.0f);
                overlookEnable.setValue(true);
                buildingsEnabled.setValue(true);
                break;
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
     * 默认加载选项
     */
    private void loadDefaultSetting() {
        setMapStatusUpdate(16.0f, 0f);
        overlookEnable.setValue(false);
        buildingsEnabled.setValue(false);
    }

    /**
     * 初始化地图数据,展示圆心位置和缩放比例
     */
    private void setMapStatusUpdate(float zoom, float overlook) {
//        float zoom = 19.0f;
//        float overlook = -45.0f;
        // 构建地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        // 中心点设置为颐和园
        LatLng center = new LatLng(39.998152, 116.276973);
        // 默认缩放级别14.5级
        builder.target(center).zoom(zoom).overlook(overlook);
        // 更新地图
        mapStatusUpdate.setValue(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }
}
