package com.example.letschill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.letschill.R;
import com.example.letschill.models.PopularData;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularHolder> {

    private Context context;
    private ArrayList<PopularData> populars;

    public PopularAdapter(ArrayList<PopularData> populars) {
        this.populars = populars;
    }

    @NonNull
    @Override
    public PopularAdapter.PopularHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layoutView = inflater.inflate(R.layout.popular_list, parent, false);
        PopularHolder holder = new PopularHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.PopularHolder holder, int position) {
        holder.setHolder(populars.get(position));
    }

    @Override
    public int getItemCount() {
        return populars.size();
    }

    public class PopularHolder extends RecyclerView.ViewHolder {

        private ImageView popularImageIv;
        private RatingBar popularRateRb;
        private TextView popularTitleTv, popularInfoTv, popularDescriptionTv, popularDirectorTv, popularWriterTv, popularStarsTv;
        private RecyclerView popularGenreRv;
        private Button popularWatchBtn, popularDownloadBtn;
        private String year, age, length;
        private ArrayList<String> stars;

        public PopularHolder(@NonNull View itemView) {
            super(itemView);
            popularImageIv = itemView.findViewById(R.id.popularImageIv);
            popularRateRb = itemView.findViewById(R.id.popularRateRb);
            popularTitleTv = itemView.findViewById(R.id.popularTitleTv);
            popularInfoTv = itemView.findViewById(R.id.popularInfoTv);
            popularDescriptionTv = itemView.findViewById(R.id.popularDescriptionTv);
            popularWriterTv = itemView.findViewById(R.id.popularWriterTv);
            popularStarsTv = itemView.findViewById(R.id.popularStarsTv);
            popularDirectorTv = itemView.findViewById(R.id.popularDirectorTv);
            popularGenreRv = itemView.findViewById(R.id.popularGenreRv);
            popularWatchBtn = itemView.findViewById(R.id.popularWatchBtn);
            popularDownloadBtn = itemView.findViewById(R.id.popularDownloadBtn);
        }

        public void setHolder(PopularData popularData) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));
            Glide.with(context)
                    .load(popularData.getImage())
                    .apply(requestOptions)
                    .into(popularImageIv);
            popularRateRb.setRating(popularData.getRating() / 2);
            popularTitleTv.setText(popularData.getName());
            popularInfoTv.setText(popularData.getYear() + " . " + popularData.getAge() + " . " + popularData.getLength());
            popularDescriptionTv.setText(popularData.getDescription());
            popularDirectorTv.setText(popularData.getDirector());
            popularWriterTv.setText(popularData.getWriter());

            GenreAdapter adapter = new GenreAdapter(popularData.getGenre());
            popularGenreRv.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            popularGenreRv.setAdapter(adapter);

//            addEvent button

        }
    }
}
