package com.popularapi.fragment;

import com.popularapi.R;
import com.popularapi.db.ArticleDatabase;
import com.popularapi.db.table.Articles;
import com.popularapi.db.table.Titles;
import com.popularapi.helper.Converter;
import com.popularapi.api.RetrofitClient;
import com.popularapi.helper.DbHelper;
import com.popularapi.model.view.ViewResponse;
import com.popularapi.model.view.ViewResult;
import com.popularapi.ui.main.adapter.ViewAdapter;

import android.util.Log;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewedFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewAdapter mAdapter;
    private ArticleDatabase database = DbHelper.database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ViewAdapter();
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RetrofitClient.getApiService().getView().enqueue(new Callback<ViewResponse>() {
            @Override
            public void onResponse(Call<ViewResponse> call, Response<ViewResponse> response) {
                database.getTitlesDao().clearTable();

                for (ViewResult view : response.body().getResults()) {
                    Titles title = new Titles(view.getTitle());
                    if (!database.getTitlesDao().containsTitle(view.getTitle())) {
                        database.getTitlesDao().addTitle(title);
                        int id = database.getTitlesDao().getTitleId(view.getTitle());
                        Articles article = new Articles(id, view.getUrl(),
                                view.getMedia().get(0).getMediaMetadata().get(0).getUrl(), false);
                        database.getArticleDao().addArticles(article);
                    }
                }

                mAdapter.setDataset(response.body().getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ViewResponse> call, Throwable t) {
                Log.i("Failure", "email error", t);
            }
        });
    }

}
