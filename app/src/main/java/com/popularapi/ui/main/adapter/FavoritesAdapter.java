package com.popularapi.ui.main.adapter;

import com.popularapi.R;
import com.popularapi.db.table.FavoritesArticle;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

import com.bumptech.glide.Glide;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private List<FavoritesArticle> mDataset = new ArrayList<>();

    public void setDataset(List<FavoritesArticle> results) {
        mDataset.clear();
        mDataset.addAll(results);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public static ImageView image;
        public View view;

        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.titleView);
            image = v.findViewById(R.id.imageView);
            view = v;
        }
    }

    @Override
    public FavoritesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        FavoritesAdapter.MyViewHolder vh = new FavoritesAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.MyViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());

        String str = mDataset.get(position).getImage();
        byte data[] = android.util.Base64.decode(str, android.util.Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        Glide.with(holder.view).load(bitmap).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
