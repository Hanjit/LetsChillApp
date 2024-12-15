package com.example.letschill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letschill.R;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {

    Context context;
    ArrayList<String> genres;

    public GenreAdapter(ArrayList<String> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public GenreAdapter.GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layoutView = inflater.inflate(R.layout.popular_genre_list, parent, false);
        GenreHolder holder = new GenreHolder(layoutView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.GenreHolder holder, int position) {
        holder.genreTitleTv.setText(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class GenreHolder extends RecyclerView.ViewHolder {

        TextView genreTitleTv;

        public GenreHolder(@NonNull View itemView) {
            super(itemView);
            genreTitleTv = itemView.findViewById(R.id.genreTitleTv);
        }
    }
}


