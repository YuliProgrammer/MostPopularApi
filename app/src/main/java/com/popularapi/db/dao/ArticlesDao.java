package com.popularapi.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.popularapi.db.table.*;

import java.util.List;

@Dao
public interface ArticlesDao {

    @Insert
    void addArticles(Articles article);

    @Update
    void updateArticles(Articles article);

    @Delete
    void deleteArticles(Articles article);

    @Query("DELETE FROM Articles")
    void clearTable();

    @Query("SELECT * FROM Articles")
    List<Articles> getAllArticles();

    @Query("SELECT * FROM Articles WHERE title_id = :titleId")
    Articles getArticles(int titleId);

    @Query(" SELECT EXISTS(SELECT * FROM Articles WHERE title_id = :titleId)")
    boolean containsArticles(int titleId);

    @Query("SELECT is_active FROM Articles WHERE title_id = :titleId")
    boolean getActive(int titleId);

}
