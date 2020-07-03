package com.example.developerandroidx.db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.developerandroidx.db.dao.SportHistoryDao;
import com.example.developerandroidx.db.entity.SportHistory;

/**
 * 作者： zjf 2020/7/3 2:43 PM
 * 参考：
 * 描述：
 */
@Database(entities = {SportHistory.class}, version = 1, exportSchema = false)
public abstract class SportHistoryDatabase extends RoomDatabase {
    public abstract SportHistoryDao getSportHistoryDao();
}
