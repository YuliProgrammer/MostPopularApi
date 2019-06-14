package com.popularapi.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.popularapi.db.dao.*;
import com.popularapi.db.table.*;

@Database(entities = {Titles.class, Articles.class, FavoritesArticle.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    public abstract TitlesDao getTitlesDao();
    public abstract ArticlesDao getArticleDao();
    public abstract FavoritesArticleDao getFavoritesArticleDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE article ADD COLUMN image BLOB");
        }
    };

}