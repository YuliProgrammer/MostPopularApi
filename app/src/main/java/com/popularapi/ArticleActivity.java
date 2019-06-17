package com.popularapi;

import com.popularapi.db.table.*;
import com.popularapi.db.ArticleDatabase;
import com.popularapi.helper.DbHelper;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import android.util.Base64;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.content.DialogInterface;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;
import java.io.ByteArrayOutputStream;

public class ArticleActivity extends AppCompatActivity {

    TextView mainText;
    TextView additionalText;
    public static ArticleDatabase database = DbHelper.database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        if (database.getFavoritesArticleDao().containsFavorites(getActiveTitle())) {
            mainText = findViewById(R.id.textViewFavorites);
            additionalText = findViewById(R.id.txtAdditionalFavorites);
            mainText.setText(R.string.txt_deleteFavorites);
            additionalText.setText(R.string.txt_additionalFavorites);
        }

        downloadWebPage();
    }

    public void onBtnFavoritesClick(View view) {
        final FloatingActionButton btnFavorites = view.findViewById(R.id.btnFavorites);

        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainText = findViewById(R.id.textViewFavorites);
                additionalText = findViewById(R.id.txtAdditionalFavorites);
                String text = mainText.getText().toString();
                AlertDialog.Builder dialog = new AlertDialog.Builder(ArticleActivity.this);

                if (text.equals("Add to favorites")) {
                    dialog.setMessage("The article successfully added to favorites");
                    dialog.setCancelable(false);

                    dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            addInDb();
                            mainText.setText(R.string.txt_deleteFavorites);
                            additionalText.setText(R.string.txt_additionalFavorites);
                        }
                    });

                } else {
                    dialog.setMessage("Are you sure that you want to delete an article from your favorites?");
                    dialog.setCancelable(true);

                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FavoritesArticle article = new FavoritesArticle(getActiveTitle());
                            database.getFavoritesArticleDao().deleteFavorites(article);

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
                }

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }

    private void downloadWebPage(){
        WebView webView = findViewById(R.id.webView);
        WebSettings webSetting = webView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(database.getArticleDao().getArticles(getActiveId()).getArticleUrl());
    }

    private void addInDb() {
        List<Articles> articles = database.getArticleDao().getAllArticles();
        for (Articles article : articles) {
            if (article.isActive()) {
                Glide.with(this)
                        .asBitmap()
                        .load(article.getImageUrl())
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                FavoritesArticle article = new FavoritesArticle(getActiveTitle(), getStringImage(resource));
                                database.getFavoritesArticleDao().addFavorites(article);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                            }
                        });
                break;
            }
        }
    }

    private int getActiveId() {
        List<Articles> articles = database.getArticleDao().getAllArticles();
        for (Articles article : articles) {
            if (article.isActive()) {
                return article.getTitleId();
            }
        }
        return 0;
    }

    private String getActiveTitle() {
        int id = getActiveId();
        return database.getTitlesDao().getTitleName(id);
    }

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}
