package com.example.android2l1v2;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String title, desc;

    public TaskModel(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
