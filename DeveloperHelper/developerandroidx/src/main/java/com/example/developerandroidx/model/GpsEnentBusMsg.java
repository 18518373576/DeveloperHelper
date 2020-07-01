package com.example.developerandroidx.model;

/**
 * 作者： zjf 2020/7/1 9:21 AM
 * 参考：
 * 描述：
 */
public class GpsEnentBusMsg {
    public double currentLat;
    public double currentLon;
    public float currentAccracy;
    public float currentDirection;
    public boolean isFirstLoc;

    public GpsEnentBusMsg(double currentLat, double currentLon, float currentAccracy, float currentDirection, boolean isFirstLoc) {
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.currentAccracy = currentAccracy;
        this.currentDirection = currentDirection;
        this.isFirstLoc = isFirstLoc;
    }
}
