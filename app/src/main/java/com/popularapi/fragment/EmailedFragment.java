package com.popularapi.fragment;

import com.popularapi.R;
import com.popularapi.db.table.*;
import com.popularapi.model.email.*;
import com.popularapi.helper.DbHelper;
import com.popularapi.api.RetrofitClient;
import com.popularapi.db.ArticleDatabase;
import com.popularapi.ui.main.adapter.EmailAdapter;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailedFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmailAdapter mAdapter;
    private ArticleDatabase database = DbHelper.database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emailed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new EmailAdapter();
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RetrofitClient.getApiService().getEmail().enqueue(new Callback<EmailResponse>() {
            @Override
            public void onResponse(Call<EmailResponse> call, Response<EmailResponse> response) {
                database.getArticleDao().clearTable();
                database.getTitlesDao().clearTable();

                for (EmailResult email : response.body().getResults()) {
                    Titles title = new Titles(email.getTitle());
                    if (!database.getTitlesDao().containsTitle(email.getTitle())) {
                        database.getTitlesDao().addTitle(title);
                        int id = database.getTitlesDao().getTitleId(email.getTitle());
                        Articles article = new Articles(id, email.getUrl(),
                                email.getMedia().get(0).getMediaMetadata().get(0).getUrl(), false);
                        database.getArticleDao().addArticles(article);
                    }
                }

                mAdapter.setDataset(response.body().getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EmailResponse> call, Throwable t) {
            }
        });
    }
}
