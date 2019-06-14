package com.popularapi.db.table;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Titles {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public Titles() {
    }

    @Ignore
    public Titles(String title) {
        this.title = title;
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

    public void setTitle(String title) {
        this.title = title;
    }
}
