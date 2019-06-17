package com.popularapi.fragment;

import com.popularapi.R;
import com.popularapi.db.ArticleDatabase;
import com.popularapi.helper.DbHelper;
import com.popularapi.ui.main.adapter.FavoritesAdapter;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesAdapter mAdapter;
    private ArticleDatabase database = DbHelper.database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FavoritesAdapter();
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter.setDataset(database.getFavoritesArticleDao().getAllFavorites());
    }

}
