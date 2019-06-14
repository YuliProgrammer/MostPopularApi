package com.popularapi.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class FavoritesArticle {

    @PrimaryKey
    @NonNull
    private String title;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image = null;

    public FavoritesArticle() {
    }

    @Ignore
    public FavoritesArticle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}