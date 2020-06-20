package com.example.dh_sample.db.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dh_sample.db.entity.User;

import java.util.List;

/**
 * 作者： zjf 2020/6/19 3:31 PM
 * 参考：
 * 描述：DAO是Data Access Object数据访问接口
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    Cursor getAll();

    @Query("SELECT * FROM users")
    List<User> getAllData();

    @Query("DELETE FROM users")
    void deleteAll();

    //当发生冲突时进行替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert
    void insert(List<User> userList);

    @Update
    void upDate(User user);

    @Update
    void upDate(List<User> userList);
}
