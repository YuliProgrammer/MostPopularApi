package com.popularapi.db.table;

import android.support.annotation.NonNull;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavoritesArticle {

    @PrimaryKey
    @NonNull
    private String title;
    private String image = null;

    public FavoritesArticle() {
    }

    @Ignore
    public FavoritesArticle(@NonNull String title) {
        this.title = title;
    }

    @Ignore
    public FavoritesArticle(@NonNull String title, String image) {
        this.title = title;
        this.image = image;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}