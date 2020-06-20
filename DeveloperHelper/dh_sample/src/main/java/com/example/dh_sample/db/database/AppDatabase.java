package com.example.dh_sample.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dh_sample.db.dao.UserDao;
import com.example.dh_sample.db.entity.User;

/**
 * 作者： zjf 2020/6/19 3:40 PM
 * 参考：
 * 描述：数据描述文件
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
