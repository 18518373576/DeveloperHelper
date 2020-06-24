package com.example.developerandroidx.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.developerandroidx.db.entity.Message;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * 作者： zjf 2020/6/20 6:14 PM
 * 参考：
 * 描述：
 */
@Dao
public interface MessageDao {

    @Query("SELECT * FROM message ORDER BY lastMsgSendTime DESC")
    Observable<List<Message>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Message... message);

    @Update
    Completable update(Message... message);

    @Delete
    Single<Integer> delete(Message... message);
}
