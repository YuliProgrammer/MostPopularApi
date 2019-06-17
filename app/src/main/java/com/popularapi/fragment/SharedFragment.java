package com.popularapi.fragment;

import com.popularapi.R;
import com.popularapi.db.table.*;
import com.popularapi.model.share.*;
import com.popularapi.helper.DbHelper;
import com.popularapi.db.ArticleDatabase;
import com.popularapi.api.RetrofitClient;
import com.popularapi.ui.main.adapter.ShareAdapter;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharedFragment extends Fragment {

    private RecyclerView recyclerView;
    private ShareAdapter mAdapter;
    private ArticleDatabase database = DbHelper.database;
    private String[] listOfShareType = {"", "Email", "Facebook", "Twitter"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shared, container, false);

        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, listOfShareType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        RetrofitClient.getApiService().getShared().enqueue(new Callback<ShareResponse>() {
                            @Override
                            public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                                workWithArticle(response);
                            }

                            @Override
                            public void onFailure(Call<ShareResponse> call, Throwable t) {
                            }
                        });
                        break;

                    case 1:
                        RetrofitClient.getApiService().getSharedEmail().enqueue(new Callback<ShareResponse>() {
                            @Override
                            public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                                workWithArticle(response);
                            }

                            @Override
                            public void onFailure(Call<ShareResponse> call, Throwable t) {
                            }
                        });
                        break;

                    case 2:
                        RetrofitClient.getApiService().getSharedFacebook().enqueue(new Callback<ShareResponse>() {
                            @Override
                            public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                                workWithArticle(response);
                            }

                            @Override
                            public void onFailure(Call<ShareResponse> call, Throwable t) {
                            }
                        });
                        break;

                    case 3:
                        RetrofitClient.getApiService().getSharedTwitter().enqueue(new Callback<ShareResponse>() {
                            @Override
                            public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                                workWithArticle(response);
                            }

                            @Override
                            public void onFailure(Call<ShareResponse> call, Throwable t) {
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setAdapter(adapter);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ShareAdapter();
        recyclerView.setAdapter(mAdapter);

        return view;
    }


    private void workWithArticle(Response<ShareResponse> response) {
        List<ShareResult> shareResults = response.body().getResults();
        addInDatabase(shareResults);
        mAdapter.setDataset(shareResults);
        mAdapter.notifyDataSetChanged();
    }

    private void addInDatabase(List<ShareResult> shareResults) {
        for (ShareResult view : shareResults) {
            Titles title = new Titles(view.getTitle());
            if (!database.getTitlesDao().containsTitle(view.getTitle())) {
                database.getTitlesDao().addTitle(title);
                int id = database.getTitlesDao().getTitleId(view.getTitle());
                Articles article = new Articles(id, view.getUrl(),
                        view.getMedia().get(0).getMediaMetadata().get(0).getUrl(), false);
                database.getArticleDao().addArticles(article);
            }
        }
    }

}
