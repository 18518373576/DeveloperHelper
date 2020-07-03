package com.example.developerandroidx.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.developerandroidx.db.entity.SportHistory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * 作者： zjf 2020/7/3 2:42 PM
 * 参考：
 * 描述：
 */
@Dao
public interface SportHistoryDao {
    @Query("SELECT * FROM SportHistory ORDER BY id DESC")
    Observable<List<SportHistory>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(SportHistory... histories);

    @Update
    Completable update(SportHistory... histories);
}
