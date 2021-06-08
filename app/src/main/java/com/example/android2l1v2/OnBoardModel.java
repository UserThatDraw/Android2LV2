package com.example.android2l1v2;

public class OnBoardModel {
    String title, descr;
    int image;

    public OnBoardModel(String title, String descr, int image) {
        this.title = title;
        this.descr = descr;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescr() {
        return descr;
    }

    public int getImage() {
        return image;
    }

}
