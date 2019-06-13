package com.popularapi.fragment;

import com.popularapi.R;
import com.popularapi.helper.Converter;
import com.popularapi.api.RetrofitClient;
import com.popularapi.model.email.EmailResponse;
import com.popularapi.ui.main.adapter.EmailAdapter;

import android.util.Log;
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
                Converter.setmEmailDataset(response.body().getResults());
                mAdapter.setDataset(response.body().getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<EmailResponse> call, Throwable t) {
                Log.i("Failure", "email error", t);
            }
        });
    }
}
