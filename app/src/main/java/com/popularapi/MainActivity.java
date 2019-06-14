package com.popularapi;

import com.popularapi.db.ArticleDatabase;
import com.popularapi.db.table.Articles;
import com.popularapi.helper.DbHelper;
import com.popularapi.ui.main.SectionsPagerAdapter;

import android.arch.persistence.room.Room;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import android.util.Log;
import android.os.Bundle;
import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArticleDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(this, ArticleDatabase.class, "test2")
                .allowMainThreadQueries()
                .build();
        DbHelper.database = database;

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void onClick(View view) {
        final ImageView imageView = view.findViewById(R.id.imageView);
        final TextView textView = view.findViewById(R.id.titleView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Articles> articles = database.getArticleDao().getAllArticles();
                for (Articles article : articles) {
                    if (article.isActive()) {
                        article.setActive(false);
                        break;
                    }
                }

                String title = textView.getText().toString();
                int id = database.getTitlesDao().getTitleId(title);
                Articles article = database.getArticleDao().getArticles(id);
                article.setActive(true);
                database.getArticleDao().updateArticles(article);
                Log.i("MAIN FAV", title);

                Intent intent = new Intent("com.popularapi.ArticleActivity");
                startActivity(intent);
            }
        });
    }

}