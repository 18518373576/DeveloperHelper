package com.example.developerandroidx.adapter.quickAdapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.developerandroidx.R;
import com.example.developerandroidx.ui.android.contentProvider.provider.Media;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.StringUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 作者： zjf 2020/6/15 2:13 PM
 * 参考：
 * 描述：
 */
public class MediaListRcvAdapter extends BaseQuickAdapter<Media, BaseViewHolder> {


    public MediaListRcvAdapter(@Nullable List<Media> data) {
        super(R.layout.item_media_list, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Media item) {
        holder.setText(R.id.tv_name, "名称:" + item.getName());
        holder.setText(R.id.tv_size, "大小:" + item.getSize() / (1024f * 1024f) + "M");
        switch (item.getMediaType()) {
            case PIC:
                holder.setText(R.id.tv_duration, "时间:" +
                        StringUtils.getInstance().getFormatTime(item.getDuration(), "yyyy-MM-dd HH:mm:ss"));
                //使用glide展示缩略图
                Glide
                        .with(getContext())
                        .load(item.getUri())
                        .override(PixelTransformForAppUtil.dip2px(100), PixelTransformForAppUtil.dip2px(100))
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.iv_media_preview));
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(item.getUri(), "image/*");
                    getContext().startActivity(intent);
                });
                break;
            case VIDEO:
                holder.setText(R.id.tv_duration, "时长:" + item.getDuration() / 1000f + "秒");
                //使用glide展示视频缩略图
                Glide
                        .with(getContext())
                        .load(item.getUri())
                        .override(PixelTransformForAppUtil.dip2px(100), PixelTransformForAppUtil.dip2px(100))
                        .centerCrop()
                        .into((ImageView) holder.getView(R.id.iv_media_preview));
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(item.getUri(), "video/*");
                    getContext().startActivity(intent);
                });
                break;
            case AUDIO:
                holder.setText(R.id.tv_duration, "时长:" + item.getDuration() / 1000f + "秒");
                holder.setImageResource(R.id.iv_media_preview, R.mipmap.icon_music);
                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(item.getUri(), "audio/*");
                    getContext().startActivity(intent);
                });
                break;
        }
    }
}
