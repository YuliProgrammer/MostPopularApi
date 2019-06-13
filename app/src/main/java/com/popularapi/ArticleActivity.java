package com.popularapi;

import com.popularapi.dao.Article;
import com.popularapi.dao.ArticleDatabase;
import com.popularapi.helper.ActiveTab;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;

public class ArticleActivity extends AppCompatActivity {

    TextView mainText;
    TextView additionalText;
    ImageView image;
    public static ArticleDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);


        database = Room.databaseBuilder(this, ArticleDatabase.class, "PopularApi")
                .allowMainThreadQueries()
                .build();

        if (database.getArticleDao().containsArticle(ActiveTab.getTitle()) == true) {
            mainText = findViewById(R.id.textViewFavorites);
            additionalText = findViewById(R.id.txtAdditionalFavorites);
            mainText.setText(R.string.txt_deleteFavorites);
            additionalText.setText(R.string.txt_additionalFavorites);
        }

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl(ActiveTab.getActiveUrl());
    }

    public void onBtnFavoritesClick(View view) {
        final FloatingActionButton btnFavorites = view.findViewById(R.id.btnFavorites);

        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainText = findViewById(R.id.textViewFavorites);
                additionalText = findViewById(R.id.txtAdditionalFavorites);
                String text = mainText.getText().toString();

                if (text.equals("Add to favorites")) {
                    Log.i("FAV", "Add to favorites");
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ArticleActivity.this);
                    dialog.setMessage("The article successfully added to favorites");
                    dialog.setCancelable(false);

                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (!database.getArticleDao().containsArticle(ActiveTab.getTitle())) {
                                Article article = new Article(ActiveTab.getTitle());
                                database.getArticleDao().addArticle(article);
                            }
                            mainText.setText(R.string.txt_deleteFavorites);
                            additionalText.setText(R.string.txt_additionalFavorites);
                        }
                    });

                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();

                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ArticleActivity.this);
                    dialog.setMessage("Are you sure that you want to delete an article from your favorites?");
                    dialog.setCancelable(true);

                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (database.getArticleDao().containsArticle(ActiveTab.getTitle())) {
                                Article article = new Article(ActiveTab.getTitle());
                                database.getArticleDao().deleteArticle(article);
                            }
                            mainText.setText(R.string.txt_addFavorites);
                            additionalText.setText(R.string.txt_additionalAddFavorites);
                        }
                    });

                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.cancel();
                        }
                    });

                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();

                }

                database.close();
            }
        });
    }

}
