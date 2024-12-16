package com.example.letschill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.letschill.R;
import com.example.letschill.models.BannerData;

import java.util.ArrayList;

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.ShowcaseHolder> {

    private ArrayList<BannerData> movies;
    private Context context;

    public ShowcaseAdapter(ArrayList<BannerData> movies, ViewPager2 viewPager2) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ShowcaseAdapter.ShowcaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layoutView = inflater.inflate(R.layout.showcase_list, parent, false);
        ShowcaseHolder adapter = new ShowcaseHolder(layoutView);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowcaseAdapter.ShowcaseHolder holder, int position) {
        holder.setImage(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ShowcaseHolder extends RecyclerView.ViewHolder {

        ImageView showcaseImageIv;

        public ShowcaseHolder(@NonNull View itemView) {
            super(itemView);
            showcaseImageIv = itemView.findViewById(R.id.showcaseImageIv);
        }

        public void setImage(BannerData bannerData) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop());
            Glide.with(context)
                    .load(bannerData.getImage())
                    .apply(requestOptions)
                    .into(showcaseImageIv);
        }
    }
}
