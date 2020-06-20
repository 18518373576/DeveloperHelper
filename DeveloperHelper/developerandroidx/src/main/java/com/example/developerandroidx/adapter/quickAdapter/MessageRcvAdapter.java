package com.example.developerandroidx.adapter.quickAdapter;

import android.graphics.Color;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.developerandroidx.R;
import com.example.developerandroidx.db.entity.Message;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
import com.example.developerandroidx.utils.StringUtils;
import com.example.developerandroidx.view.swipeAction.QMUISwipeAction;
import com.example.developerandroidx.view.swipeAction.QMUISwipeViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 作者： zjf 2020/6/20 2:25 PM
 * 参考：
 * 描述：
 */
public class MessageRcvAdapter extends BaseQuickAdapter<Message, QMUISwipeViewHolder> {

    public final QMUISwipeAction mWriteReviewAction;
    public final QMUISwipeAction mDeleteAction;

    public MessageRcvAdapter() {
        super(R.layout.item_message_list);

        QMUISwipeAction.ActionBuilder builder = new QMUISwipeAction.ActionBuilder()
                .textSize(PixelTransformForAppUtil.dip2px(14))
                .paddingStartEnd(PixelTransformForAppUtil.dip2px(20));
//        238 121 66 	232 232 232
        mDeleteAction = builder.text("删除").backgroundColor(Color.rgb(238, 121, 66)).textColor(Color.WHITE).build();
        mWriteReviewAction = builder.text("修改").backgroundColor(Color.rgb(232, 232, 232)).textColor(Color.GRAY).build();
    }

    @Override
    protected void convert(@NotNull QMUISwipeViewHolder holder, Message item) {
        holder.addSwipeAction(mDeleteAction);
        holder.addSwipeAction(mWriteReviewAction);
        holder.setText(R.id.tv_name, item.contactName);
        holder.setText(R.id.tv_last_msg, item.lastMsg);
        holder.setText(R.id.tv_last_msg_time, StringUtils.getInstance().getFormatTime(item.lastMsgSendTime, "MM月dd日"));
        Glide
                .with(getContext())
                .load(Uri.parse(item.contactHeaderImage))
                .override(PixelTransformForAppUtil.dip2px(50), PixelTransformForAppUtil.dip2px(50))
                .centerCrop()
                .error(R.mipmap.icon_camera)
                .placeholder(R.mipmap.icon_camera)
                .fallback(R.mipmap.icon_camera)
                .into((ImageView) holder.getView(R.id.iv_header_image));
    }
}
