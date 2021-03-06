package com.example.developerandroidx.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者： zjf 2020/7/3 9:47 AM
 * 参考：
 * 描述：
 */
@Entity
public class SportHistory {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;//记录ID
    public String dateStr;//yyyy-MM-dd
    public long startTime;//运动开始时间,单位毫秒:new Date().getTime()
    public long endTime;//运动结束时间
    public float distance;//运动距离,单位米
    public int steps;//运动步数
    public int sportType;//运动类型,RIDING = 100;STEP = 101;步行

    public SportHistory(String dateStr, long startTime, long endTime, float distance, int steps, int sportType) {
        this.dateStr = dateStr;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.steps = steps;
        this.sportType = sportType;
    }
}
