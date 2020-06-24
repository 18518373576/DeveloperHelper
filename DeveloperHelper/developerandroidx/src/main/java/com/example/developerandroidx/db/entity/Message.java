package com.example.developerandroidx.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者： zjf 2020/6/20 1:19 PM
 * 参考：
 * 描述：
 */
@Entity
public class Message {
    //联系人ID,主键
    @NonNull
    @PrimaryKey
    public String contactId;
    //最新的一条消息
    public String lastMsg;
    //联系人头像,保存图片URI
    public String contactHeaderImage;
    //联系人名称
    public String contactName;
    //最近一条消息发送时间
    public long lastMsgSendTime;

    public Message(@NonNull String contactId, String lastMsg, String contactHeaderImage, String contactName, long lastMsgSendTime) {
        this.contactId = contactId;
        this.lastMsg = lastMsg;
        this.contactHeaderImage = contactHeaderImage;
        this.contactName = contactName;
        this.lastMsgSendTime = lastMsgSendTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "contactId='" + contactId + '\'' +
                ", lastMsg='" + lastMsg + '\'' +
                ", contactHeaderImage='" + contactHeaderImage + '\'' +
                ", contactName='" + contactName + '\'' +
                ", lastMsgSendTime=" + lastMsgSendTime +
                '}';
    }
}
