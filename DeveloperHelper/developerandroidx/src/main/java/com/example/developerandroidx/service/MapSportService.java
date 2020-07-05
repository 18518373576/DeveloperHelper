package com.example.developerandroidx.service;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.api.track.HistoryTrackRequest;
import com.baidu.trace.api.track.HistoryTrackResponse;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.api.track.SupplementMode;
import com.baidu.trace.api.track.TrackPoint;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.ProcessOption;
import com.baidu.trace.model.PushMessage;
import com.baidu.trace.model.TransportMode;
import com.example.developerandroidx.App;
import com.example.developerandroidx.R;
import com.example.developerandroidx.db.DB_utils;
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.model.GpsEnentBusMsg;
import com.example.developerandroidx.model.SportDescEventBusMsg;
import com.example.developerandroidx.ui.android.map.BaiDuMapActivity;
import com.example.developerandroidx.utils.Constant;
import com.example.developerandroidx.utils.LogUtils;
import com.example.developerandroidx.utils.PreferenceUtils;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.utils.enumPkg.SportType;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用于在后台记录位置信息,运动信息
 */
public class MapSportService extends Service {
    private final MyBinder binder = new MyBinder();
    private LocationClient mLocClient;
    private MyLocationListener locationListener;
    private boolean isFirstLoc = true;

    //方向传感器相关
    private float mCurrentDirection = 0;
    private Double lastX = 0.0;
    private MySensorEventListener sensorEventListener;
    private SensorManager mSensorManager;

    //默认当前位置点
    private double mCurrentLat = Double.parseDouble(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.LOCATION_LAT, "34.78084"));
    private double mCurrentLon = Double.parseDouble(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.LOCATION_LON, "113.702818"));
    private float mCurrentAccracy;
    //运动开始标志,true为正在运动
    private boolean sportFlag = PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.IS_SPORTING);
    //计时开始时间
    private long oldTime;
    //当前运动类型
    private SportType currentSportType;

    //运动距离
    private float distance = 0;
    //运动步数
    private int steps = 0;
    //上次时间间隔,主要是记录退出应用没有结束运动,记录运动的时间,再次进来继续计时
    private int lastTimeSpace = 0;
    //通知相关
    private NotificationCompat.Builder builder;
    private PendingIntent pendingIntent;
    private String title;
    private String content;

    //地图上画线的点
    public List<LatLng> points = new ArrayList<>();
    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;

    // 轨迹服务ID
    private long serviceId = 222017;
    // 设备标识
    private String entityName;
    // 是否需要对象存储服务，默认为：false，关闭对象存储服务。注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，
    // 若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
    private boolean isNeedObjectStorage = false;
    // 定位周期(单位:秒)
    int gatherInterval = 5;
    // 打包回传周期(单位:秒)
    int packInterval = 10;

    //百度地图鹰眼服务
    private Trace mTrace;
    private LBSTraceClient mTraceClient;
    //纠偏选项
    private ProcessOption processOption;


    public class MyBinder extends Binder {
        public MapSportService getService() {
            return MapSportService.this;
        }
    }

    /**
     * 方向传感器监听方法
     */
    private class MySensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            switch (sensorEvent.sensor.getType()) {
                case Sensor.TYPE_STEP_DETECTOR:
                    if (sportFlag) {
                        if ((int) sensorEvent.values[0] == 1) {
                            steps++;
                            PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.STEP, steps);
                        }
                    }
                    break;
                case Sensor.TYPE_ORIENTATION:
                    double x = sensorEvent.values[SensorManager.DATA_X];
                    if (Math.abs(x - lastX) > 1.0) {
                        mCurrentDirection = (int) x;
                        EventBus.getDefault().post(new GpsEnentBusMsg(mCurrentLat, mCurrentLon, mCurrentAccracy, mCurrentDirection, isFirstLoc));
                    }
                    lastX = x;
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * 定位初始化
     */
    public void initLocation() {
        if (mLocClient != null) {
            return;
        }
        //初始化方位传感器,确定方向
        initSensor();
        // 定位初始化
        mLocClient = new LocationClient(this);
        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        // 打开gps
        option.setOpenGps(true);

        option.setScanSpan(2000);
        option.setCoorType("bd09ll");

        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 初始化传感器
     */
    private void initSensor() {
        // 获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 为系统的方向传感器注册监听器
        sensorEventListener = new MySensorEventListener();
        mSensorManager.registerListener(sensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(sensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR), SensorManager.SENSOR_DELAY_NORMAL);
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
            LogUtils.e("百度经纬度mCurrentLat", String.valueOf(location.getLatitude()));
            LogUtils.e("百度经纬度mCurrentLon", String.valueOf(location.getLongitude()));
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            EventBus.getDefault().post(new GpsEnentBusMsg(mCurrentLat, mCurrentLon, mCurrentAccracy, mCurrentDirection, isFirstLoc));
            isFirstLoc = false;
        }
    }

    /**
     * 开启前台服务
     */
    private void startForeground() {

        title = "运动数据";
        content = "运动步数:00000步";

        //设置点击通知事件
        Intent intent = new Intent(this, BaiDuMapActivity.class);
        //setFlags() 方法帮助保留用户在通过通知打开应用后的预期导航体验。但您是否要使用这一方法取决于您要启动的 Activity 类型
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        initNotificationBuilder();
//        要显示通知，请调用 NotificationManagerCompat.notify()，并将通知的唯一 ID 和 NotificationCompat.Builder.build() 的结果传递给它
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(100, builder.build());

        startForeground(102, builder.build());
    }

    private void initNotificationBuilder() {
        /**
         * 小图标，通过 setSmallIcon() 设置。这是所必需的唯一一个用户可见内容。
         * 标题，通过 setContentTitle() 设置。
         * 正文文本，通过 setContentText() 设置。
         * 通知优先级，通过 setPriority() 设置。优先级确定通知在 Android 7.1 和更低版本上的干扰程度。（对于 Android 8.0 和更高版本，必须设置渠道重要性，如下一节中所示。）
         */
        builder = new NotificationCompat.Builder(this, App.IMPORTANCE_LOW_CHANNEL_ID)
                .setSmallIcon(currentSportType == SportType.STEP ? R.mipmap.icon_step : R.mipmap.icon_riding)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        currentSportType == SportType.STEP ? R.mipmap.icon_step : R.mipmap.icon_riding))
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
    }

    /**
     * 停止前台服务
     */
    public void stopForeground() {
        stopForeground(true);
    }

    /**
     * 开始运动
     */
    @SuppressLint("InvalidWakeLockTag")
    public void startSport(SportType sportType) {
        currentSportType = sportType;
        sportFlag = true;
        //保存运动状态,正在运动
        PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.IS_SPORTING, true);
        //让进程在后台存活,保持持续定位能力---------特别注意----------------
        if (null == wakeLock) {
            powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "track upload");
            if (!(wakeLock.isHeld())) {
                wakeLock.acquire();
            }
        }
        initProcessOption(currentSportType);
        //开启鹰眼追踪服务
        initTrace();
        //开始计时
        startTimer();
        startForeground();
    }

    /**
     * 初始化纠偏选项
     */
    public void initProcessOption(SportType sportType) {
        // 创建纠偏选项实例
        processOption = new ProcessOption();
        // 设置需要去噪
        processOption.setNeedDenoise(true);
        // 设置需要抽稀
        processOption.setNeedVacuate(true);
        // 设置需要绑路
        processOption.setNeedMapMatch(true);
        // 设置精度过滤值(定位精度大于100米的过滤掉)
        processOption.setRadiusThreshold(100);
        // 设置交通方式为驾车
        switch (sportType) {
            case STEP:
                processOption.setTransportMode(TransportMode.walking);
                break;
            case RIDING:
                processOption.setTransportMode(TransportMode.riding);
                break;
        }
    }

    /**
     * 停止运动
     */
    public void stopSport() {
        //保存运动数据
        if (sportFlag) {
            saveSportData();
        }
        sportFlag = false;
        //关闭鹰眼追踪服务
        stopTrace();
        //释放掉后台存活服务
        if (null != wakeLock && wakeLock.isHeld()) {
            wakeLock.release();
        }
        //清空画线的点
        points.clear();
        //运动距离设置为0
        distance = 0;
        //运动步数设置为0
        steps = 0;
        //还原保存的运动记录数据
        PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.IS_SPORTING, false);
        PreferenceUtils.getInstance().putStringValue(Constant.PreferenceKeys.DISTANCE, "0");
        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.STEP, 0);
        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.TIME_SPACE, 0);
        stopForeground();
        stopSelf();
    }

    /**
     * 保存运动数据
     */
    SportHistory sportHistory = null;

    private void saveSportData() {
        String dateStr = StringUtils.getInstance().getCurrentTime("yyyy-MM-dd");
        long startTime = oldTime - (lastTimeSpace * 1000);
        long endTime = new Date().getTime();
        if (currentSportType.equals(SportType.RIDING)) {
            //大于两公里数据才会保存
            if (distance > 2000) {
                sportHistory = new SportHistory(dateStr, startTime, endTime, distance, steps, Constant.Common.RIDING);

            }
        } else {
            //步数大于500才会保存
            if (steps > 500) {
                sportHistory = new SportHistory(dateStr, startTime, endTime, distance, steps, Constant.Common.STEP);
            }
        }
        DB_utils.getInstance().getSportHistoryDB()
                .getSportHistoryDao()
                .insert(sportHistory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("运动数据插入", "完成");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    // 初始化轨迹服务监听器
    private OnTraceListener mTraceListener = new OnTraceListener() {
        @Override
        public void onBindServiceCallback(int status, String message) {
            LogUtils.e("鹰眼服务onBindServiceCallback", message);
        }

        // 开启服务回调
        @Override
        public void onStartTraceCallback(int status, String message) {
            LogUtils.e("鹰眼服务onStartTraceCallback", message);
        }

        // 停止服务回调
        @Override
        public void onStopTraceCallback(int status, String message) {
            LogUtils.e("鹰眼服务onStopTraceCallback", message);
        }

        // 开启采集回调
        @Override
        public void onStartGatherCallback(int status, String message) {
            LogUtils.e("鹰眼服务onStartGatherCallback", message);
        }

        // 停止采集回调
        @Override
        public void onStopGatherCallback(int status, String message) {
            LogUtils.e("鹰眼服务onStopGatherCallback", message);
        }

        // 推送回调
        @Override
        public void onPushCallback(byte messageNo, PushMessage message) {
            LogUtils.e("鹰眼服务onPushCallback", message.getMessage());
        }

        @Override
        public void onInitBOSCallback(int status, String message) {
            LogUtils.e("鹰眼服务onInitBOSCallback", message);
        }
    };

    /**
     * 开启鹰眼追踪定位
     */
    private void initTrace() {
        // 初始化轨迹服务
        mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);
        // 开启服务
        mTraceClient.startTrace(mTrace, mTraceListener);
        // 开启采集
        mTraceClient.startGather(mTraceListener);
        //轨迹查询,放到定时器里面查询, startTimer()
//        queryTrace();
    }

    private void stopTrace() {
        // 停止服务
        if (mTraceClient != null)
            mTraceClient.stopTrace(mTrace, mTraceListener);
    }

    // 请求标识
    int tag = 1;
    // 创建历史轨迹请求实例
    HistoryTrackRequest historyTrackRequest;
    // 初始化轨迹监听器
    OnTrackListener mTrackListener = new OnTrackListener() {
        // 历史轨迹回调
        @Override
        public void onHistoryTrackCallback(HistoryTrackResponse response) {
            if (sportFlag) {
                distance = (float) response.getDistance();
            }

            if (response.getTotal() > 0) {
                LogUtils.e("鹰眼服务距离测算", distance + "#" + response.getEntityName());
                LogUtils.e("鹰眼服务历史位置点", distance + "#" + response.trackPoints.size() + "#" + response.getTotal());
                //添加位置点
                for (TrackPoint point : response.getTrackPoints()) {
                    points.add(new LatLng(point.getLocation().latitude, point.getLocation().longitude));
                }
                //说明数据没有拿完
                if (pageIndex * pageSize < response.getTotal()) {
                    pageIndex++;
                    queryHistoryTrace(thisStartTime, thisEndTime, pageIndex);
                } else {
                    EventBus.getDefault().post(points);
                }
            }
        }
    };

    /**
     * 查询历史轨迹
     */
    private int pageIndex = 1;
    private int pageSize = 1000;
    private long thisStartTime;
    private long thisEndTime;

    public void queryHistoryTrace(long startTime, long endTime, int pageNum) {
//        Observable.create()
        thisStartTime = startTime;
        thisEndTime = endTime;
        historyTrackRequest = new HistoryTrackRequest(tag, serviceId, entityName);
        // 设置开始时间
        historyTrackRequest.setStartTime(startTime / 1000);
        // 设置结束时间
        historyTrackRequest.setEndTime(endTime / 1000);
        historyTrackRequest.setPageIndex(pageNum);
        historyTrackRequest.setPageSize(pageSize);
        // 设置需要纠偏
        historyTrackRequest.setProcessed(true);

        // 设置纠偏选项
        if (processOption != null) {
            historyTrackRequest.setProcessOption(processOption);
        }

        // 中断里程补偿,riding：使用最短骑行路线距离补充,骑行与步行不再做区分
        historyTrackRequest.setSupplementMode(SupplementMode.riding);

        // 查询历史轨迹
        mTraceClient.queryHistoryTrack(historyTrackRequest, mTrackListener);
    }

    /**
     * 开始计时
     */

    private void startTimer() {
        oldTime = new Date().getTime();
        // takeUntil含义,直到 aLong > 10,也就是11才停止
        //         .takeUntil(new Predicate<Long>() {
        //            @Override
        //            public boolean test(Long aLong) throws Exception {
        //                return aLong > 10;
        //            }
        //        })
        //takeWhile含义,当 aLong < 10,就发送事件,直到 aLong >= 10时停止
        //         .takeWhile(new Predicate<Long>() {
        //            @Override
        //            public boolean test(Long aLong) throws Exception {
        //                return aLong < 10;
        //            }
        //        })
        //开始计时
        Observable
                .interval(0, 1, TimeUnit.SECONDS, Schedulers.newThread())
                .takeWhile(aLong -> sportFlag)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long aLong) {
                        long currentTime = new Date().getTime();
                        //时间间隔,秒
                        int timeSpace = (int) ((currentTime - oldTime) / 1000) + lastTimeSpace;
                        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.TIME_SPACE, timeSpace);
                        String time = StringUtils.getInstance().getTime(timeSpace);
//                        LogUtils.e("倒计时", StringUtils.getInstance().getTime((int) ((currentTime - oldTime) / 1000)));
                        EventBus.getDefault().post(new SportDescEventBusMsg(time, StringUtils.getInstance().getSteps(steps),
                                StringUtils.getInstance().getDistance(distance), currentSportType));

                        //更新通知和查询鹰眼轨迹记录,每隔5秒更新一次
                        if ((timeSpace % 5) == 0) {
                            //查询轨迹
                            points.clear();
                            queryHistoryTrace(oldTime - (lastTimeSpace * 1000), currentTime, pageIndex);
                            //更新通知
                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MapSportService.this);
                            content = currentSportType == SportType.STEP ?
                                    "运动步数:" + StringUtils.getInstance().getSteps(steps) + "步" :
                                    "骑行距离:" + StringUtils.getInstance().getDistance(distance) + "KM";
                            initNotificationBuilder();
                            notificationManager.notify(102, builder.build());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void onCreate() {
        entityName = getEntityName();
        //EVR-AL00_6065d59515f43183
        LogUtils.e("entityName", entityName);
        // 初始化轨迹服务客户端
        mTraceClient = new LBSTraceClient(getApplicationContext());
        if (sportFlag) {
            distance = Float.parseFloat(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.DISTANCE, "0"));
            steps = PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.STEP, 0);
            lastTimeSpace = PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.TIME_SPACE, 0);
            if (PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.SPORT_TYPE, 0).equals(Constant.Common.RIDING)) {
                startSport(SportType.RIDING);
            } else {
                startSport(SportType.STEP);
            }
        }
        super.onCreate();
    }

    /**
     * 获取鹰眼服务设备号,每台设备有一个唯一的设备号,实际可以使用用户的账号作为鹰眼的设备号
     *
     * @return
     */
    private String getEntityName() {
        String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android.os.Build.MODEL + "_" + deviceId;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogUtils.e("onDestroy", "onDestroy");
        if (mLocClient != null) {
            mLocClient.unRegisterLocationListener(locationListener);
            mLocClient.stop();
        }
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(sensorEventListener);
        }
        stopSport();
        //保存退出定位最后的位置信息,作为再次进入时默认展示的点
        PreferenceUtils.getInstance().putStringValue(Constant.PreferenceKeys.LOCATION_LAT, String.valueOf(mCurrentLat));
        PreferenceUtils.getInstance().putStringValue(Constant.PreferenceKeys.LOCATION_LON, String.valueOf(mCurrentLon));
        super.onDestroy();
    }
}
