package com.example.android2l1v2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class TaskModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, desc, time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TaskModel(String title, String desc, String time) {
        this.title = title;
        this.desc = desc;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
