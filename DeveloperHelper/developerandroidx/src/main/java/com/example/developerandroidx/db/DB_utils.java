package com.example.developerandroidx.db;

import androidx.room.Room;

import com.example.developerandroidx.App;
import com.example.developerandroidx.db.database.CityDatabase;
import com.example.developerandroidx.db.database.MessageDatabase;
import com.example.developerandroidx.db.database.SportHistoryDatabase;
import com.example.developerandroidx.db.entity.SportHistory;
import com.example.developerandroidx.projectInterface.CallBack;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： zjf 2020/6/19 3:43 PM
 * 参考：
 * 描述：数据库使用工具
 */
public class DB_utils {
    private final MessageDatabase messageDatabase;
    private final CityDatabase citiesDatabase;
    private final SportHistoryDatabase sportHistoryDatabase;
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
        messageDatabase = Room.databaseBuilder(App.context, MessageDatabase.class, "message.db")
                .build();
        citiesDatabase = Room.databaseBuilder(App.context, CityDatabase.class, "city.db")
                .createFromAsset("city.db")
                .build();
        sportHistoryDatabase = Room.databaseBuilder(App.context, SportHistoryDatabase.class, "sportHistory.db")
                .build();
    }

    public static DB_utils getInstance() {
        return Instance.INSTANCE;
    }

    public MessageDatabase getMessageDB() {
        return messageDatabase;
    }

    public CityDatabase getCityDB() {
        return citiesDatabase;
    }

    public SportHistoryDatabase getSportHistoryDB() {
        return sportHistoryDatabase;
    }

    /**
     * 获取所有的运动数据
     */
    public void getAllSportData(CallBack<List<SportHistory>> callBack) {
        getSportHistoryDB().getSportHistoryDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SportHistory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SportHistory> sportHistoryList) {
                        callBack.onSuc(sportHistoryList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
