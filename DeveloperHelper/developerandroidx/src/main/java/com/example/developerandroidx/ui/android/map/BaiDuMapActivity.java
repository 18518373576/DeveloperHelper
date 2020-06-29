package com.example.developerandroidx.ui.android.map;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityBaiDuMapBinding;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;

public class BaiDuMapActivity extends BaseActivityWithDataBinding<ActivityBaiDuMapBinding> implements View.OnClickListener {


    private BaiDuMapViewModel viewModel;
    private float mCurrentDirection = 0;
    private Double lastX = 0.0;
    private boolean isFirstLoc = true;
    private double mCurrentLat;
    private double mCurrentLon;
    private float mCurrentAccracy;
    private SensorManager mSensorManager;

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
        //绑定点击事件监听
        binding.setOnClickListener(this);
        //定位初始化
        initLocation();
        //初始化传感器,用于确定方向
        initSensor();
    }

    /**
     * 初始化传感器
     */
    private void initSensor() {
        // 获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener(new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double x = sensorEvent.values[SensorManager.DATA_X];
                if (Math.abs(x - lastX) > 1.0) {
                    mCurrentDirection = (int) x;
                    viewModel.setMyLocation(mCurrentLat, mCurrentLon, mCurrentAccracy, mCurrentDirection);
                }
                lastX = x;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
    }

    /**
     * 定位初始化
     */
    public void initLocation() {
        // 开启定位图层,通过DataBinding进行数据绑定
//        binding.mvMap.getMap().setMyLocationEnabled(true);
        // 定位初始化
        LocationClient mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        // 打开gps
        option.setOpenGps(true);
        // 设置坐标类型
        option.setCoorType("bd09ll");
        option.setScanSpan(2000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 定位SDK监听函数
     */
    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // MapView 销毁后不在处理新接收的位置
            if (location == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
//            LogUtils.e("经纬度mCurrentLat", String.valueOf(mCurrentLat));
//            LogUtils.e("经纬度mCurrentLon", String.valueOf(mCurrentLon));
            viewModel.setMyLocation(mCurrentLat, mCurrentLon, mCurrentAccracy, mCurrentDirection);
            if (isFirstLoc) {
                isFirstLoc = false;
                viewModel.setMapStatusUpdate(16f, -45f, mCurrentLat, mCurrentLon);
            }
        }
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_coverage:
                DialogUtils.getInstance().showBottomMenu(context,
                        new String[]{Constant.Common.LIGHT_STYLE, Constant.Common.NIGHT_STYLE, Constant.Common.DEFAULT_STYLE},
                        new DialogUtils.OnItemClickListener() {
                            @Override
                            public void onClick(String text, int index) {
                                viewModel.setMapCustomStylePath(text);
                            }
                        });
                break;
            case R.id.iv_my_location:
                viewModel.setMapStatusUpdate(16f, -45f, mCurrentLat, mCurrentLon);
                break;
        }
    }

    @Override
    protected void onResume() {
        // 在activity执行onResume时必须调用mMapView. onResume ()
        binding.mvMap.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        //在activity执行onPause时必须调用mMapView. onPause ()
        binding.mvMap.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        binding.mvMap.onDestroy();
        super.onDestroy();
    }
}