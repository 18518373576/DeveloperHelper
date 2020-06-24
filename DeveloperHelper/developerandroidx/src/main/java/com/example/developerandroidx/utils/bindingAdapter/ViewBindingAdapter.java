package com.example.developerandroidx.utils.bindingAdapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.base.App;
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
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

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
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
