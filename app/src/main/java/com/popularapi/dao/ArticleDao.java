package com.popularapi.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert
    void addArticle(Article article);

    @Delete
    void deleteArticle(Article article);

    @Query("SELECT * FROM article")
    List<Article> getAllArticle();

    @Query("SELECT * FROM article WHERE title = :title")
    Article getArticle(String title);

    @Query(" SELECT EXISTS(SELECT * FROM article WHERE title = :title)")
    boolean containsArticle(String title);
}