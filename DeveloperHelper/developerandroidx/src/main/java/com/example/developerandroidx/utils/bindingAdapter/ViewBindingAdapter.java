package com.example.developerandroidx.utils.bindingAdapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.developerandroidx.view.loadingView.LoadingPage;
import com.example.developerandroidx.view.loadingView.LoadingState;

import java.util.List;

/**
 * 作者： zjf 2020/6/23 12:10 PM
 * 参考：
 * 描述：ViewBindingAdapter
 */
public class ViewBindingAdapter {

    /**
     * ------------------View---------------------
     */
    @BindingAdapter("onTouchListener")
    public static void setOnTouchListener(View view, View.OnTouchListener onTouchListener) {
        view.setOnTouchListener(onTouchListener);
    }
    /**
     * ------------------imageView--------------------------
     */

    /**
     * 设置imageView的src以Bitmap
     *
     * @param view
     * @param bitmap
     */
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    /**
     * 以资源ID设置ImageView的src
     *
     * @param view
     * @param resId
     */
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "error"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(holderDrawable)
                .error(errorDrawable)
                .into(imageView);
    }

    /**
     * ------------------loadingPage--------------------------
     */

    /**
     * {@link LoadingPage}自定义属性
     *
     * @param loadingPage
     * @param loadingState
     */
    @BindingAdapter("loadingState")
    public static void setState(LoadingPage loadingPage, LoadingState loadingState) {
        loadingPage.setLoadingState(loadingState);
    }

    @BindingAdapter("noDataMsg")
    public static void setNoDataMsg(LoadingPage loadingPage, String noDataMsg) {
        loadingPage.setNoDataMsg(noDataMsg);
    }

    @BindingAdapter("loadingFailImage")
    public static void setLoadingFailImage(LoadingPage loadingPage, Integer imageId) {
        loadingPage.setLoadingFailImage(imageId);
    }

    /**
     * ------------------RecyclerView--------------------------
     */

    /**
     * 设置adapter属性
     *
     * @param recyclerView
     * @param adapter
     */
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        recyclerView.setAdapter(adapter);
    }

    /**
     * ------------------com.baidu.mapapi.map.MapView--------------------------
     */

    /**
     * 百度地图自定义属性
     *
     * @param mapView
     * @param statusUpdate
     */
    @BindingAdapter("animateMapStatus")
    public static void setAnimateMapStatus(MapView mapView, MapStatusUpdate statusUpdate) {
        // 更新地图
        if (statusUpdate != null) {
            mapView.getMap().animateMapStatus(statusUpdate);
        }
    }

    /**
     * 执行一些地图的基础设置
     *
     * @param mapView
     * @param init
     */
    @BindingAdapter("init")
    public static void initMap(MapView mapView, Boolean init) {
        if (init) {
            //初始化一些属性,百度log展示位置,
            mapView.setLogoPosition(LogoPosition.logoPostionRightTop);
            mapView.getMap().setViewPadding(0, 80, 30, 0);
            //隐藏比例尺
            mapView.showScaleControl(false);
            //隐藏缩放按钮
            mapView.showZoomControls(false);
            //隐藏指南针
            mapView.getMap().getUiSettings().setCompassEnabled(false);
        }
    }

    /**
     * 设置自定义样式路径
     *
     * @param mapView
     * @param mapCustomStylePath
     */
    @BindingAdapter("mapCustomStylePath")
    public static void setMapCustomStylePath(MapView mapView, String mapCustomStylePath) {
        if (!TextUtils.isEmpty(mapCustomStylePath)) {
            mapView.setMapCustomStylePath(mapCustomStylePath);
            mapView.setMapCustomStyleEnable(true);
        } else {
            mapView.setMapCustomStyleEnable(false);
        }
    }

    /**
     * 是否开启实时交通状况图
     *
     * @param mapView
     * @param trafficEnabled
     */
    @BindingAdapter("trafficEnabled")
    public static void setTrafficEnabled(MapView mapView, Boolean trafficEnabled) {
        mapView.getMap().setTrafficEnabled(trafficEnabled);
    }

    /**
     * 设置地图是否可以旋转
     */
    @BindingAdapter("rotateEnable")
    public static void setRotateEnable(MapView mapView, Boolean rotateEnable) {
        mapView.getMap().getUiSettings().setRotateGesturesEnabled(rotateEnable);
    }

    /**
     * 设置地图是否开启俯视
     */
    @BindingAdapter("overlookEnable")
    public static void setOverlookEnable(MapView mapView, Boolean overlookEnable) {
        mapView.getMap().getUiSettings().setOverlookingGesturesEnabled(overlookEnable);
    }

    /**
     * 设置是否展示3d建筑
     */
    @BindingAdapter("buildingsEnabled")
    public static void setBuildingsEnabled(MapView mapView, Boolean buildingsEnabled) {
        BaiduMap baiduMap = mapView.getMap();
        baiduMap.setBuildingsEnabled(buildingsEnabled);
        MapStatus mapStatus = baiduMap.getMapStatus();
        if (null != mapStatus) {
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
            // 设置地图状态
            baiduMap.setMapStatus(mapStatusUpdate);
        }
    }

    /**
     * 是否开启定位图层
     */
    @BindingAdapter("myLocationEnabled")
    public static void setMyLocationEnabled(MapView mapView, Boolean myLocationEnabled) {
        mapView.getMap().setMyLocationEnabled(myLocationEnabled);
    }

    /**
     * 设置当前位置展示模式
     */
    @BindingAdapter("myLocationConfiguration")
    public static void setMyLocationConfiguration(MapView mapView, MyLocationConfiguration.LocationMode locationMode) {
        // 传入null，则为默认图标,参数1:当前位置模式.参数二:是否开启方向箭头.参数三:自定义位置图标
        mapView.getMap().setMyLocationConfiguration(new MyLocationConfiguration(locationMode, true, null));
    }

    /**
     * 设置当前位置
     */
    @BindingAdapter("myLocationData")
    public static void setMyLocationData(MapView mapView, MyLocationData myLocationData) {
        mapView.getMap().setMyLocationData(myLocationData);
    }

    /**
     * 在地图上画线
     */
    @BindingAdapter("addOverlay")
    public static void addOverlay(MapView mapView, List<LatLng> points) {
        if (points != null && points.size() > 1) {
            //清除所有覆盖图层
            mapView.getMap().clear();
            //设置折线的属性
            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(8)
                    .color(0xAAFF0000)
                    .points(points);
            //在地图上绘制折线
            //mPloyline 折线对象
            Overlay mPolyline = mapView.getMap().addOverlay(mOverlayOptions);
        }
    }
}
