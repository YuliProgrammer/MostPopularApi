package com.popularapi.ui.main.adapter;

import com.popularapi.R;
import com.popularapi.model.view.ViewResult;

import java.util.List;
import java.util.ArrayList;

import com.bumptech.glide.Glide;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    private List<ViewResult> mDataset = new ArrayList<>();

    public void setDataset(List<ViewResult> results) {
        mDataset.clear();
        mDataset.addAll(results);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public View view;

        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.titleView);
            image = v.findViewById(R.id.imageView);
            view = v;
        }
    }

    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        ViewAdapter.MyViewHolder vh = new ViewAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewAdapter.MyViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        Glide.with(holder.view).load(mDataset.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).
                into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}