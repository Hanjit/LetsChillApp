package com.example.letschill;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.letschill.models.PopularData;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailFragment extends Fragment {

    private String name, image, year, age, length, description, genreString;
    private float rating;
    private ArrayList<String> genre, stars;

    private ImageView detailImageIv;
    private TextView detailTitleTv, detailGenreTv, detailYearTv, detailAgeTv, detailLengthTv, detailDescriptionTv;
    private ImageButton detailPlayBtn, detailStarBtn, detailShareBtn;
    private RatingBar detailRatingRb;

    FragmentManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("movieName");
            image = getArguments().getString("movieImage");
            year = getArguments().getString("movieYear");
            age = getArguments().getString("movieAge");
            length = getArguments().getString("movieLength");
            description = getArguments().getString("movieDescription");
            rating = getArguments().getFloat("movieRating");
            stars = getArguments().getStringArrayList("movieStars");
            genre = getArguments().getStringArrayList("movieGenre");
            genreString = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        manager = getParentFragmentManager();
        detailImageIv = view.findViewById(R.id.detailImageIv);
        detailTitleTv = view.findViewById(R.id.detailTitleTv);
        detailYearTv = view.findViewById(R.id.detailYearTv);
        detailAgeTv = view.findViewById(R.id.detailAgeTv);
        detailLengthTv = view.findViewById(R.id.detailLengthTv);
        detailDescriptionTv = view.findViewById(R.id.detailDescriptionTv);
        detailPlayBtn = view.findViewById(R.id.detailPlayBtn);
        detailStarBtn = view.findViewById(R.id.detailStarBtn);
        detailShareBtn = view.findViewById(R.id.detailShareBtn);
        detailRatingRb = view.findViewById(R.id.detailRatingRb);
        detailGenreTv = view.findViewById(R.id.detailGenreTv);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new TopCrop() ,new RoundedCornersTransformation(500, 0, RoundedCornersTransformation.CornerType.BOTTOM));
        Glide.with(this)
                .load(image)
                .apply(requestOptions)
                .into(detailImageIv);

        detailTitleTv.setText(name);
        detailYearTv.setText(year);
        detailAgeTv.setText(age);
        detailLengthTv.setText(length);
        detailDescriptionTv.setText(description);
        detailRatingRb.setRating(rating);

        for (int i = 0; i < genre.size(); i++){
            if (i == genre.size() - 2) {
                genreString.concat(genre.get(i));
            } else {
                genreString.concat(genre.get(i) + " | ");
            }
        }
        detailGenreTv.setText(genreString);

        detailPlayBtn.setOnClickListener(v -> {
            Toast toast = Toast.makeText(view.getContext(), "Play movie clicked", Toast.LENGTH_SHORT);
            toast.show();
        });

        detailStarBtn.setOnClickListener(v -> {
            detailStarBtn.setImageResource(R.drawable.baseline_star_48_orange);
        });

        return view;
    }
}