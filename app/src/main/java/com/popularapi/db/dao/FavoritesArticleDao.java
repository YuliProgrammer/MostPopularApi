package com.popularapi.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.popularapi.db.table.FavoritesArticle;

import java.util.List;

@Dao
public interface FavoritesArticleDao {

    @Insert
    void addFavorites(FavoritesArticle article);

    @Delete
    void deleteFavorites(FavoritesArticle article);

    @Query("SELECT * FROM FavoritesArticle")
    List<FavoritesArticle> getAllFavorites();

    @Query("SELECT * FROM FavoritesArticle WHERE title = :title")
    FavoritesArticle getFavorites(String title);

    @Query(" SELECT EXISTS(SELECT * FROM FavoritesArticle WHERE title = :title)")
    boolean containsFavorites(String title);

}