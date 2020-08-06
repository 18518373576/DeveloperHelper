package com.example.developerandroidx.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者： zjf 2020/6/24 1:48 PM
 * 参考：
 * 描述：
 */
@Entity
public class City {
    //`id`, `cityEn`, `cityZh`, `provinceEn`, `provinceZh`,
    // `countryEn`, `countryZh`, `leaderEn`, `leaderZh`, `lat`, `lon`
    @NonNull
    @PrimaryKey
    public String id;
    @NonNull
    public String cityEn;
    @NonNull
    public String cityZh;
    @NonNull
    public String provinceEn;
    @NonNull
    public String provinceZh;
    @NonNull
    public String countryEn;
    @NonNull
    public String countryZh;
    @NonNull
    public String leaderEn;
    @NonNull
    public String leaderZh;
    @NonNull
    public String lat;
    @NonNull
    public String lon;

    public City(@NonNull String id, @NonNull String cityEn, @NonNull String cityZh,
                @NonNull String provinceEn, @NonNull String provinceZh, @NonNull String countryEn,
                @NonNull String countryZh, @NonNull String leaderEn, @NonNull String leaderZh,
                @NonNull String lat, @NonNull String lon) {
        this.id = id;
        this.cityEn = cityEn;
        this.cityZh = cityZh;
        this.provinceEn = provinceEn;
        this.provinceZh = provinceZh;
        this.countryEn = countryEn;
        this.countryZh = countryZh;
        this.leaderEn = leaderEn;
        this.leaderZh = leaderZh;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", cityEn='" + cityEn + '\'' +
                ", cityZh='" + cityZh + '\'' +
                ", provinceEn='" + provinceEn + '\'' +
                ", provinceZh='" + provinceZh + '\'' +
                ", countryEn='" + countryEn + '\'' +
                ", countryZh='" + countryZh + '\'' +
                ", leaderEn='" + leaderEn + '\'' +
                ", leaderZh='" + leaderZh + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
