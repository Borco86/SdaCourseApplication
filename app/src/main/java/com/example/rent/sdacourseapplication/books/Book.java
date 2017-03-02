package com.example.rent.sdacourseapplication.books;

import android.support.annotation.DrawableRes;

/**
 * Created by RENT on 2017-03-02.
 */
public class Book {


    private int id;
    private boolean isRead;


    @DrawableRes
    private int imgResourceId;
    private String title;


    public Book(int id, @DrawableRes int imgResourceId, String title) {
        this.id = id;
        this.imgResourceId = imgResourceId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getTitle() {
        return title;
    }
}
