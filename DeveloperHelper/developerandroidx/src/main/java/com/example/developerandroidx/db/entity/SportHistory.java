package com.example.developerandroidx.db.entity;

/**
 * 作者： zjf 2020/7/3 9:47 AM
 * 参考：
 * 描述：
 */
public class SportHistory {
    public int id;//记录ID
    public String dateStr;//yyyy年MM月dd日
    public long startTime;//运动开始时间,单位秒:(new Date().getTime())/1000
    public long endTime;//运动结束时间
    public float distance;//运动距离,单位米
    public int steps;//运动步数
    public int pointsTotal;//记录的轨迹点个数
    public int sportType;//运动类型,RIDING = 100;STEP = 101;步行

    public SportHistory(int id, String dateStr, long startTime, long endTime, float distance, int steps, int sportType, int pointsTotal) {
        this.id = id;
        this.dateStr = dateStr;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.steps = steps;
        this.sportType = sportType;
        this.pointsTotal = pointsTotal;
    }
}
