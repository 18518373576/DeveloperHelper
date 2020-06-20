package com.example.dh_sample.db;

import androidx.room.Room;

import com.example.dh_sample.App;
import com.example.dh_sample.db.database.AppDatabase;

/**
 * 作者： zjf 2020/6/19 3:43 PM
 * 参考：
 * 描述：数据库使用工具
 */
public class DB_utils {
    private final AppDatabase db;
//    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE users ADD COLUMN user_id TEXT NOT NULL DEFAULT 100");
//        }
//    };
//    private static final Migration MIGRATION_2_3 = new Migration(1, 3) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("DROP TABLE users");
//            database.execSQL("CREATE TABLE users(user_id TEXT  PRIMARY KEY,name TEXT,tel TEXT)");
//        }
//    };

    private static class Instance {
        public static final DB_utils INSTANCE = new DB_utils();
    }

    private DB_utils() {
        db = Room.databaseBuilder(App.context, AppDatabase.class, "sample_db.db")
//                .addMigrations()
                .build();
    }

    public static DB_utils getInstance() {
        return Instance.INSTANCE;
    }

    public AppDatabase getDB() {
        return db;
    }


}
