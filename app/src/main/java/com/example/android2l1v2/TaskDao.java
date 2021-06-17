package com.example.android2l1v2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM taskmodel")
    List<TaskModel> getAll();

    @Insert
    void insertAll(TaskModel... models);

    @Delete
    void deleteTask(TaskModel model);
}
