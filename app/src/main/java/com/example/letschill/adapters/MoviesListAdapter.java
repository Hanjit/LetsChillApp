package com.example.letschill.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.letschill.R;
import com.example.letschill.models.MoviesData;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesHolder> {

    private Context context;
    private ArrayList<MoviesData> movies;

    public MoviesListAdapter(ArrayList<MoviesData> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movies_list, parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        holder.movlistTitleTV.setText(movies.get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(40));

        Glide.with(context)
                .load(movies.get(position).getPoster())
                .apply(requestOptions)
                .into(holder.movlistImageIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                pindah kemana saya tak tau
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MoviesHolder extends RecyclerView.ViewHolder {
        TextView movlistTitleTV;
        ImageView movlistImageIV;

        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            movlistTitleTV = itemView.findViewById(R.id.movlistTitleTV);
            movlistImageIV = itemView.findViewById(R.id.movlistImageIV);
        }
    }
}
