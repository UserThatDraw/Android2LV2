package com.example.android2l1v2.ui;

import com.example.android2l1v2.TaskModel;

public interface OnClickInterface {
    void onItemClick(int position, TaskModel model);
    
    void onLongItemClick(int position);
}
