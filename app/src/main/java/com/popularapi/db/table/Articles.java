package com.popularapi.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Titles.class,
        parentColumns = "id", childColumns = "title_id", onDelete = CASCADE))
public class Articles {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title_id")
    private int titleId;
    @ColumnInfo(name = "article_url")
    private String articleUrl;
    @ColumnInfo(name = "image_url")
    private String imageUrl;
    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public Articles() {
    }

    @Ignore
    public Articles(int titleId, String articleUrl, String imageUrl, boolean isActive) {
        this.titleId = titleId;
        this.articleUrl = articleUrl;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
