package com.example.developerandroidx.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.developerandroidx.db.dao.CityDao;
import com.example.developerandroidx.db.entity.City;


/**
 * 作者： zjf 2020/6/24 11:52 AM
 * 参考：
 * 描述：
 */
@Database(entities = {City.class}, version = 1, exportSchema = false)
public abstract class CityDatabase extends RoomDatabase {
    public abstract CityDao getCitiesDao();
}
