package com.example.dh_sample;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dh_sample.db.DB_utils;
import com.example.dh_sample.db.entity.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 示例主要配合developerandroidx演示AIDL和ContentProvider
 */
public class MainActivity extends AppCompatActivity {

    List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {

//                DB_utils.getInstance().getDB().getUserDao().deleteAll();
                DB_utils.getInstance().getDB().getUserDao().insert(new User("张三", "16666666666", "100"));
                DB_utils.getInstance().getDB().getUserDao().insert(new User("李四", "16666666666", "101"));
                Cursor cursor = DB_utils.getInstance().getDB().getUserDao().getAll();
                list = DB_utils.getInstance().getDB().getUserDao().getAllData();
            }
        }).start();
        final TextView tv_click = findViewById(R.id.tv_click);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                if (list == null) {
                    tv_click.setText("创建数据库失败");
                } else {
                    tv_click.setText("");
                    Observable
                            .fromIterable(list)
                            .subscribe(new Consumer<User>() {
                                @Override
                                public void accept(User user) throws Exception {
                                    tv_click.append(user.name + "\n" + user.tel + "\n");
                                }
                            });
                }
            }
        });
    }
}
