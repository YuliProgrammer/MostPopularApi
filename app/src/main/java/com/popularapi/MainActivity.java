package com.popularapi;

import com.popularapi.helper.ActiveTab;
import com.popularapi.helper.Converter;
import com.popularapi.ui.main.SectionsPagerAdapter;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import android.util.Log;
import android.os.Bundle;
import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                String title = textView.getText().toString();
                String url = Converter.getAllUrl().get(title);

                //byte[] imageArr = Converter.convertImageToArr(imageView);
                //  ActiveTab.setImage(imageArr);

                ActiveTab.setTitle(title);
                ActiveTab.setActiveUrl(url);
                Log.i("MAIN FAV", title);

                Intent intent = new Intent("com.popularapi.ArticleActivity");
                startActivity(intent);
            }
        });
    }

}