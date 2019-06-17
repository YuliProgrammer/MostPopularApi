package com.popularapi.db;

import com.popularapi.db.dao.*;
import com.popularapi.db.table.*;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Titles.class, Articles.class, FavoritesArticle.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    public abstract TitlesDao getTitlesDao();
    public abstract ArticlesDao getArticleDao();
    public abstract FavoritesArticleDao getFavoritesArticleDao();

}