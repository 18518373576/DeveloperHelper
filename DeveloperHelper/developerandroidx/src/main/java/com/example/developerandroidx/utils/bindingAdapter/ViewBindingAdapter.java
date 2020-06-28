package com.example.developerandroidx.utils.bindingAdapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapView;
import com.bumptech.glide.Glide;
import com.example.developerandroidx.view.loadingView.LoadingPage;
import com.example.developerandroidx.view.loadingView.LoadingState;

/**
 * 作者： zjf 2020/6/23 12:10 PM
 * 参考：
 * 描述：ViewBindingAdapter
 */
public class ViewBindingAdapter {

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
        if (statusUpdate != null)
            mapView.getMap().animateMapStatus(statusUpdate);
    }

    /**
     * 设置自定义样式路径
     *
     * @param mapView
     * @param mapCustomStylePath
     */
    @BindingAdapter("mapCustomStylePath")
    public static void setMapCustomStylePath(MapView mapView, String mapCustomStylePath) {
        if (mapCustomStylePath != null) {
            mapView.setMapCustomStylePath(mapCustomStylePath);
            mapView.setMapCustomStyleEnable(true);
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
}
