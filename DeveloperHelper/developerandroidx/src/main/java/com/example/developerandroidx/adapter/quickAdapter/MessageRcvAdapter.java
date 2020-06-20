package com.example.developerandroidx.adapter.quickAdapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.developerandroidx.R;
import com.example.developerandroidx.db.entity.Message;
import com.example.developerandroidx.utils.PixelTransformForAppUtil;
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
    }
}
