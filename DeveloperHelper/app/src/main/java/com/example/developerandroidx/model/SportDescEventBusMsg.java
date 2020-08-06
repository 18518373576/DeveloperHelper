package com.example.developerandroidx.model;

import com.example.developerandroidx.utils.enumPkg.SportType;

/**
 * 作者： zjf 2020/7/1 11:19 AM
 * 参考：
 * 描述：运动描述,主要记录时间,步数和距离
 */
public class SportDescEventBusMsg {
    public String time;
    public String steps;
    public String distance;
    public SportType sportType;

    public SportDescEventBusMsg(String time, String steps, String distance, SportType sportType) {
        this.time = time;
        this.steps = steps;
        this.distance = distance;
        this.sportType = sportType;
    }
}
