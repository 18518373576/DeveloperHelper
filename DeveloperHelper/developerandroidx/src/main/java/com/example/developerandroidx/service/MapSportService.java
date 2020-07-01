package com.example.developerandroidx.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
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

    private float mCurrentDirection = 0;
    private Double lastX = 0.0;
    private MySensorEventListener sensorEventListener;
    private SensorManager mSensorManager;

    private double mCurrentLat = Double.parseDouble(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.LOCATION_LAT, "34.78084"));
    private double mCurrentLon = Double.parseDouble(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.LOCATION_LON, "113.702818"));
    private float mCurrentAccracy;
    private boolean sportFlag = PreferenceUtils.getInstance().getBooleanValue(Constant.PreferenceKeys.IS_SPORTING);
    private long oldTime;
    private SportType currentSportType;

    //运动距离
    private float distance = 0;
    //运动步数
    private int steps = 0;
    //上次时间间隔
    private int lastTimeSpace = 0;
    private NotificationCompat.Builder builder;
    private PendingIntent pendingIntent;
    private String title;
    private String content;

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
        //初始化方位传感器,确定方向
        initSensor();
        // 定位初始化
        mLocClient = new LocationClient(this);
        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
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
    //地图上画线的点
    private List<LatLng> points = new ArrayList<>();
    double i = 0.001;

    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // MapView 销毁后不在处理新接收的位置
            if (location == null) {
                return;
            }
            if (sportFlag) {
                double pointsDistance = DistanceUtil.getDistance(new LatLng(mCurrentLat, mCurrentLon), new LatLng(location.getLatitude(), location.getLongitude()));
                if (pointsDistance < 5) {
                    distance += pointsDistance;
                    PreferenceUtils.getInstance().putStringValue(Constant.PreferenceKeys.DISTANCE, String.valueOf(distance));
                }
                //测试画线
//                points.add(new LatLng(mCurrentLat + i, mCurrentLon + i));
//                i += 0.0001;
//                EventBus.getDefault().post(points);
                if (pointsDistance < 5 && pointsDistance > 0.1) {
                    points.add(new LatLng(mCurrentLat, mCurrentLon));
                    EventBus.getDefault().post(points);
                }
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            LogUtils.e("经纬度mCurrentLat", String.valueOf(mCurrentLat));
            LogUtils.e("经纬度mCurrentLon", String.valueOf(mCurrentLon));
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
    public void startSport(SportType sportType) {
        currentSportType = sportType;
        sportFlag = true;
        //保存运动状态,正在运动
        PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.IS_SPORTING, true);
        startTimer();
        startForeground();
    }

    /**
     * 停止运动
     */
    public void stopSport() {
        sportFlag = false;
        points.clear();
        distance = 0;
        steps = 0;
        PreferenceUtils.getInstance().putBooleanValue(Constant.PreferenceKeys.IS_SPORTING, false);
        PreferenceUtils.getInstance().putStringValue(Constant.PreferenceKeys.DISTANCE, "0");
        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.STEP, 0);
        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.TIME_SPACE, 0);
        stopForeground();
        stopSelf();
    }

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
                        //时间间隔
                        int timeSpace = (int) ((currentTime - oldTime) / 1000) + lastTimeSpace;
                        PreferenceUtils.getInstance().putIntValue(Constant.PreferenceKeys.TIME_SPACE, timeSpace);
                        String time = StringUtils.getInstance().getTime(timeSpace);
//                        LogUtils.e("倒计时", StringUtils.getInstance().getTime((int) ((currentTime - oldTime) / 1000)));
                        EventBus.getDefault().post(new SportDescEventBusMsg(time, StringUtils.getInstance().getSteps(steps),
                                StringUtils.getInstance().getDistance(distance), currentSportType));

                        //更新通知,每隔3秒更新一次
                        if ((timeSpace % 3) == 0) {
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
        if (sportFlag) {
            distance = Float.parseFloat(PreferenceUtils.getInstance().getStringValue(Constant.PreferenceKeys.DISTANCE, "0"));
            steps = PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.STEP, 0);
            lastTimeSpace = PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.TIME_SPACE, 0);
            if (PreferenceUtils.getInstance().getIntValue(Constant.PreferenceKeys.SPORT_TYPE, 0) == Constant.Common.RIDING) {
                startSport(SportType.RIDING);
            } else {
                startSport(SportType.STEP);
            }
        }
        super.onCreate();
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
