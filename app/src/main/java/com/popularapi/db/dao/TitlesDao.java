package com.popularapi.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.popularapi.db.table.Titles;

import java.util.List;

@Dao
public interface TitlesDao {

    @Insert
    void addTitle(Titles title);

    @Delete
    void deleteTitle(Titles title);

    @Query("DELETE FROM Titles")
    void clearTable();

    @Query("SELECT * FROM Titles")
    List<Titles> getAllTitles();

    @Query("SELECT * FROM Titles WHERE title = :title")
    Titles getTitle(String title);

    @Query("SELECT id FROM Titles WHERE title = :title")
    int getTitleId(String title);

    @Query("SELECT title FROM Titles WHERE id = :id")
    String getTitleName(int id);

    @Query(" SELECT EXISTS(SELECT * FROM Titles WHERE title = :title)")
    boolean containsTitle(String title);
}
