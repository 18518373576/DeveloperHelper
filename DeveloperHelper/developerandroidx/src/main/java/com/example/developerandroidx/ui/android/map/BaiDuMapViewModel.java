package com.example.developerandroidx.ui.android.map;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.example.developerandroidx.base.BaseViewModel;
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

    //初始化地图位置
    public MutableLiveData<MapStatusUpdate> mapStatusUpdate = new MutableLiveData<>();
    //设置自定义主题路径
    public MutableLiveData<String> mapCustomStylePath = new MutableLiveData<>();
    //是否展示交通图
    public MutableLiveData<Boolean> trafficEnabled = new MutableLiveData<>(false);

    @Override
    protected void initData(@Nullable String... param) {
        initMapStatusUpdate();
        initMapCustomStylePath();

    }

    /**
     * 初始化自定义样式路径
     */
    private void initMapCustomStylePath() {
        StringUtils.getInstance().getCustomStyleFilePath(CUSTOM_FILE_NAME_LIGHT, new StringUtils.CallBack<String>() {
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
    private void initMapStatusUpdate() {
        // 构建地图状态
        MapStatus.Builder builder = new MapStatus.Builder();
        // 中心点设置为颐和园
        LatLng center = new LatLng(39.998152, 116.276973);
        // 默认缩放级别14.5级
        float zoom = 16f;
        builder.target(center).zoom(zoom);
        // 更新地图
        mapStatusUpdate.setValue(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }
}
