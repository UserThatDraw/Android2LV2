package com.example.android2l1v2;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class App extends Application {
    public static AppDataBase instance = null;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        PreferenceHelper.init(this);
    }
    public static AppDataBase getInstance(){
        if (instance == null) {
            instance = Room.
                    databaseBuilder(context, AppDataBase.class, "task-database").
                    allowMainThreadQueries().fallbackToDestructiveMigration().
                    build();
        }
        return instance;
    }
}
