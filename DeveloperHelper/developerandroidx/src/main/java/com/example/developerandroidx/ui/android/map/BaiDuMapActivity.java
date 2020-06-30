package com.example.developerandroidx.ui.android.map;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityBaiDuMapBinding;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;

public class BaiDuMapActivity extends BaseActivityWithDataBinding<ActivityBaiDuMapBinding> implements View.OnClickListener, View.OnTouchListener {


    private BaiDuMapViewModel viewModel;
    private float mCurrentDirection = 0;
    private Double lastX = 0.0;
    //是否首次加载位置,如果是则以当前位置为圆心展示地图
    private boolean isFirstLoc = true;
    private double mCurrentLat = 34.78084;
    private double mCurrentLon = 113.702818;
    private float mCurrentAccracy;
    //配合底部开始和暂停的图标,默认为未开始
    private boolean isPlaying = false;

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
        //绑定触摸事件
        binding.setOnTouchListener(this);
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
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
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
            LogUtils.e("经纬度mCurrentLat", String.valueOf(mCurrentLat));
            LogUtils.e("经纬度mCurrentLon", String.valueOf(mCurrentLon));
            viewModel.setMyLocation(mCurrentLat, mCurrentLon, mCurrentAccracy, mCurrentDirection);
            if (isFirstLoc) {
                isFirstLoc = false;
                viewModel.setMapStatusUpdate(16f, -45f, mCurrentLat, mCurrentLon);
            }
        }
    }

    /**
     * 触摸事件
     *
     * @param v
     * @param event
     * @return
     */
    float down_x;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_x = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (v.getVisibility() != View.VISIBLE) {
                    return true;
                }
                float translate_x = event.getX() - down_x;
                LogUtils.e("手指位移", String.valueOf(translate_x));
                switch (v.getId()) {
                    case R.id.iv_riding:
                        if (translate_x > 100) {
                            startOrCancelSport(R.id.iv_riding, true);
                        }
                        break;
                    case R.id.iv_step:
                        if (translate_x < -100) {
                            startOrCancelSport(R.id.iv_step, true);
                        }
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                float translate_up_x = event.getX() - down_x;
                switch (v.getId()) {
                    case R.id.iv_riding:
                        if (translate_up_x < 50) {
                            startOrCancelSport(R.id.iv_riding, false);
                        }
                        break;
                    case R.id.iv_step:
                        if (translate_up_x > -50) {
                            startOrCancelSport(R.id.iv_step, false);
                        }
                        break;
                }
                break;
        }

        return true;
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_coverage:
                showStyleDialog();
                break;
            case R.id.iv_play:
                //如果不在运动,开启运动选项
                if (!isPlaying) {
                    //此选项为开启运动选择,骑行或步行
                    startOrCancelSport(R.id.iv_play, false);
                } else {
                    //如果在运动,弹框提示是否要结束
                    showAlertDialog();
                }
                break;
        }
    }

    /**
     * 展示提示对话框
     */
    private void showAlertDialog() {
        DialogUtils.getInstance().showMessageDialog(context, "提示", "确定是否结束运动", "结束", "继续", new DialogUtils.OnButtonClickedListener() {
            @Override
            public void onClick(String msg, boolean isOkButton) {
                if (isOkButton) {
                    //结束运动
                    isPlaying = false;
                    viewModel.stopTimer();
                    viewModel.setPlayAndStopIcon(isPlaying);
                    AnimUtil.getInstance().startAlphaAnimator(binding.llSportDesc, true, 500, 1f, 0f);
                }
            }
        });
    }

    /**
     * 选择样式的弹框
     */
    private void showStyleDialog() {
        DialogUtils.getInstance().showBottomMenu(context,
                new String[]{Constant.Common.LIGHT_STYLE, Constant.Common.NIGHT_STYLE, Constant.Common.DEFAULT_STYLE},
                new DialogUtils.OnItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        viewModel.setMapCustomStylePath(text);
                    }
                });
    }

    /**
     * 开启运动,执行一些动画效果,非主要逻辑可略过
     *
     * @param id
     */
    private void startOrCancelSport(int id, boolean isCancel) {
        switch (id) {
            //使用动画
            case R.id.iv_play:
                AnimUtil.getInstance().startScaleAnimator(binding.ivPlay, 300, true, 1f, 0f);
                AnimUtil.getInstance().startTranslateAndScaleAnimator(binding.ivStep, 1000,
                        true, false, PixelTransformForAppUtil.dip2px(80), 0f, 1f);
                AnimUtil.getInstance().startTranslateAndScaleAnimator(binding.ivRiding, 1000,
                        true, false, -PixelTransformForAppUtil.dip2px(80), 0f, 1f);
                break;
            case R.id.iv_riding:
            case R.id.iv_step:
                if (!isCancel) {
                    isPlaying = true;
                    viewModel.startTimer();
                    viewModel.setPlayAndStopIcon(isPlaying);
                    AnimUtil.getInstance().startAlphaAnimator(binding.llSportDesc, false, 500, 0f, 1f);
                }
                AnimUtil.getInstance().startSpringScaleAnimator(binding.ivPlay, 1000, 0f, 1f);
                AnimUtil.getInstance().startTranslateAndScaleAnimator(binding.ivStep, 300, false, true, 0f, 1f, 0f);
                AnimUtil.getInstance().startTranslateAndScaleAnimator(binding.ivRiding, 300, false, true, 0f, 1f, 0f);
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
        viewModel.stopTimer();
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        binding.mvMap.onDestroy();
        super.onDestroy();
    }
}