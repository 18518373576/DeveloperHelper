package com.example.developerandroidx.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.developerandroidx.db.dao.MessageDao;
import com.example.developerandroidx.db.entity.Message;

/**
 * 作者： zjf 2020/6/20 6:24 PM
 * 参考：
 * 描述：
 */
@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MessageDao getMessageDao();
}
