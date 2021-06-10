package com.example.android2l1v2;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {TaskModel.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao getTaskDao();
}
