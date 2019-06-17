package com.popularapi;

import com.popularapi.ui.main.SectionsPagerAdapter;
import com.popularapi.db.ArticleDatabase;
import com.popularapi.db.table.Articles;
import com.popularapi.helper.DbHelper;

import android.view.View;
import android.widget.TextView;

import android.os.Bundle;
import android.content.Intent;

import android.arch.persistence.room.Room;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int position;
    public static ArticleDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(this, ArticleDatabase.class, "dbPopularArticle")
                .allowMainThreadQueries()
                .build();
        DbHelper.database = database;

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        final int[] TAB_ICON = new int[]{R.drawable.icon_email, R.drawable.icon_share,
                R.drawable.icon_view, R.drawable.icon_star};

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        for (int i = 0; i < TAB_ICON.length; i++) {
            tabs.getTabAt(i).setIcon(TAB_ICON[i]);
        }
    }

    public void onClick(View view) {
        final TextView textView = view.findViewById(R.id.titleView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TabLayout tabLayout = findViewById(R.id.tabs);
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        position = tab.getPosition();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

                if (position != 3) {
                    List<Articles> articles = database.getArticleDao().getAllArticles();
                    for (Articles article : articles) {
                        if (article.isActive()) {
                            article.setActive(false);
                            database.getArticleDao().updateArticles(article);
                            break;
                        }
                    }

                    String title = textView.getText().toString();
                    int id = database.getTitlesDao().getTitleId(title);
                    Articles article = database.getArticleDao().getArticles(id);
                    article.setActive(true);
                    database.getArticleDao().updateArticles(article);
                }

                Intent intent = new Intent("com.popularapi.ArticleActivity");
                startActivity(intent);
            }
        });
    }

}