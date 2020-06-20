package com.example.dh_sample.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * 作者： zjf 2020/6/19 3:22 PM
 * 参考：
 * 描述：用户实例
 */
@Entity(tableName = "users", primaryKeys = {"user_id"})
public class User {

    @NonNull
    @ColumnInfo(name = "user_id", defaultValue = "100")
    public String userId;
    //姓名
    public String name;
    //电话
    public String tel;

    public User(String name, String tel, @NonNull String userId) {
        this.name = name;
        this.tel = tel;
        this.userId = userId;
    }
}
