package com.example.developerandroidx.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.developerandroidx.db.entity.City;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： zjf 2020/6/24 11:54 AM
 * 参考：
 * 描述：
 */
@Dao
public interface CityDao {

    @Query("SELECT * FROM city WHERE cityEn OR cityZh LIKE '%'||:cityName||'%'")
    Observable<List<City>> queryByCityName(String cityName);
}
