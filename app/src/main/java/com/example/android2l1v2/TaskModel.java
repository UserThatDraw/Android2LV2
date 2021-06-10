package com.example.android2l1v2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class TaskModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, desc;

    public TaskModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
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
