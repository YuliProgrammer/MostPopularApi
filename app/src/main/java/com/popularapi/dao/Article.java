package com.popularapi.dao;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Article {

    @PrimaryKey
    @NonNull
    private String title;
//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    private byte[] image = null;

    public Article() {
    }

    @Ignore
    public Article(@NonNull String title) {
        this.title = title;
    }

//    @Ignore
//    public Article(String title, byte[] image) {
//        this.title = title;
//        this.image = image;
//    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
}