package com.example.developerandroidx.ui.android.map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.mapapi.model.LatLng;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.BaseActivityWithDataBinding;
import com.example.developerandroidx.databinding.ActivityBaiDuMapBinding;
import com.example.developerandroidx.model.GpsEnentBusMsg;
import com.example.developerandroidx.model.SportDescEventBusMsg;
import com.example.developerandroidx.service.MapSportService;
import com.example.developerandroidx.utils.AnimUtil;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.DialogUtils;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.enumPkg.SportType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class BaiDuMapActivity extends BaseActivityWithDataBinding<ActivityBaiDuMapBinding> implements View.OnClickListener, View.OnTouchListener {


    private BaiDuMapViewModel viewModel;

    //配合底部开始和暂停的图标,默认为未开始
    private boolean isSporting = PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.IS_SPORTING);
    //服务实例
    private MapSportService sportService;
    //服务连接
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MapSportService.MyBinder binder = (MapSportService.MyBinder) service;
            sportService = binder.getService();
            sportService.initLocation();
            if (isSporting) {
                if (PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.SPORT_TYPE, 0) == R.id.iv_riding) {
                    sportService.startSport(SportType.RIDING);
                } else {
                    sportService.startSport(SportType.STEP);
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected int bindLayout() {
        return R.layout.activity_bai_du_map;
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        setNativeStatusBar(StateBarType.TRAN);
    }

    @Override
    protected void initData() {
        super.initData();
        //绑定viewModel
        viewModel = getViewModel(BaiDuMapViewModel.class);
        viewModel.initData();
        //定位服务开启
        bindService(new Intent(context, MapSportService.class), connection, Context.BIND_AUTO_CREATE);

        binding.setModel(viewModel);
        //绑定点击事件监听
        binding.setOnClickListener(this);
        //绑定触摸事件
        binding.setOnTouchListener(this);
        //如果运动没有结束,则恢复的运动状态
        recoverSporting(isSporting);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveLocationMsg(GpsEnentBusMsg msg) {
        viewModel.setMyLocation(msg.currentLat, msg.currentLon, msg.currentAccracy, msg.currentDirection);
        if (msg.isFirstLoc) {
            if (isSporting) {
                viewModel.setMapStatusUpdate(19f, -45f, msg.currentLat, msg.currentLon);
            } else {
                viewModel.setMapStatusUpdate(16f, -45f, msg.currentLat, msg.currentLon);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveSportMsg(SportDescEventBusMsg msg) {
        viewModel.setTimer(msg.time);
        switch (msg.sportType) {
            case RIDING:
                viewModel.setStepOrDistance(msg.distance + "KM");
                break;
            case STEP:
                viewModel.setStepOrDistance(msg.steps);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivePoints(List<LatLng> points) {
        viewModel.setOverlayPoints(points);
    }

    /**
     * 运动没有结束退出界面后再进来,恢复运动
     *
     * @param isSporting
     */
    private void recoverSporting(boolean isSporting) {
        if (isSporting) {
            startService(new Intent(this, MapSportService.class));
            //开启运动,主要作用是更新地图画线的标志
            viewModel.startSport();
            if (PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.SPORT_TYPE, 0) == R.id.iv_riding) {
                viewModel.setSportTitle(SportType.RIDING);
            } else {
                viewModel.setSportTitle(SportType.STEP);
            }
            viewModel.setPlayAndStopIcon(true);
            AnimUtil.getInstance().startAlphaAnimator(binding.llSportDesc, false, 500, 0f, 1f);
        }
    }

    /**
     * 触摸事件,执行底部按钮的动画效果,非主要逻辑
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
//                LogUtils.e("手指位移", String.valueOf(translate_x));
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
                if (translate_up_x > -10 && translate_up_x < 10) {
                    startOrCancelSport(v.getId(), false);
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
                if (!isSporting) {
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
                    isSporting = false;
                    viewModel.stopSport();
                    sportService.stopSport();
                    viewModel.setPlayAndStopIcon(isSporting);
                    AnimUtil.getInstance().startAlphaAnimator(binding.llSportDesc, true, 500, 1f, 0f);
                    //结束运动时把地图缩小到默认值
                    viewModel.setMapStatusZoomUpdate(16f);
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
                //运动开启标志,运动结束逻辑在提示框showAlertDialog
                if (!isCancel) {
                    isSporting = true;
                    //保存正在运动的项目
                    PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.SPORT_TYPE, id);
                    //开启运动时执行startService,避免退出界面后服务销毁,当没有开启运动时使用bindService,退出界面服务自动销毁
                    //在服务的stopSport方法中执行了stopSelf(),运动结束结束服务,绑定取消服务销毁
                    startService(new Intent(context, MapSportService.class));

                    //开启运动,主要作用是更新地图画线的标志
                    viewModel.startSport();
                    if (id == R.id.iv_riding) {
                        viewModel.setSportTitle(SportType.RIDING);
                        //服务运动开启,主要作用是开启运动计时,计算步数和距离
                        sportService.startSport(SportType.RIDING);
                    } else {
                        viewModel.setSportTitle(SportType.STEP);
                        sportService.startSport(SportType.STEP);
                    }
                    viewModel.setPlayAndStopIcon(isSporting);
                    AnimUtil.getInstance().startAlphaAnimator(binding.llSportDesc, false, 500, 0f, 1f);
                    //开始运动时,把地图放大
                    viewModel.setMapStatusZoomUpdate(19f);
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
        EventBus.getDefault().unregister(this);
        unbindService(connection);
        viewModel.stopSport();
        // 在activity执行onDestroy时必须调用mMapView.onDestroy()
        binding.mvMap.onDestroy();
        super.onDestroy();
    }
}