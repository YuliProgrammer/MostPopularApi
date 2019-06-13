package com.popularapi.ui.main.adapter;

import com.popularapi.R;
import com.popularapi.dao.Article;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private List<Article> mDataset = new ArrayList<>();

    public void setDataset(List<Article> results) {
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

//        byte[] imageArr = ActiveTab.getImage();
//        Bitmap bmp = BitmapFactory.decodeByteArray(imageArr, 0, imageArr.length);
//        MyViewHolder.image.setImageBitmap(Bitmap.createScaledBitmap(bmp, MyViewHolder.image.getWidth(),
//                MyViewHolder.image.getHeight(), false));

//        Glide.with(holder.view).load(mDataset.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).
//                into(holder.image);
    }

    @Override
    public int getItemCount() {
      //  return 0;
       return mDataset.size();
    }

    public List<Article> getmDataset() {
        return mDataset;
    }
}
