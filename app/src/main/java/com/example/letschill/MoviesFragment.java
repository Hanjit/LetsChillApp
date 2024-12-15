package com.example.letschill;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letschill.adapters.BannerAdapter;
import com.example.letschill.adapters.MoviesListAdapter;
import com.example.letschill.models.BannerData;
import com.example.letschill.models.MoviesData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private ViewPager2 moviesBannerVP;
    private RecyclerView moviesActionRV, moviesFantasyRV, moviesHorrorRV;
    private ArrayList<MoviesData> moviesList;
    private DatabaseReference ref;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        moviesList = new ArrayList<>();

        moviesBannerVP = view.findViewById(R.id.moviesBannerVP);
        moviesActionRV = view.findViewById(R.id.moviesView1RV);
        moviesFantasyRV = view.findViewById(R.id.moviesView2RV);
        moviesHorrorRV = view.findViewById(R.id.moviesView3RV);

        bannerList(moviesBannerVP);
        moviesList("Action", moviesActionRV);
        moviesList("Fantasy", moviesFantasyRV);
        moviesList("Horror", moviesHorrorRV);

        return view;
    }

    private void moviesList(String genre, RecyclerView recyclerView) {
        ref = FirebaseDatabase.getInstance().getReference("Items");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<MoviesData> moviesList = new ArrayList<>();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        MoviesData data = itemSnapshot.getValue(MoviesData.class);
                        ArrayList<String> genres = new ArrayList<>();
                        for (DataSnapshot genreSnapshot : itemSnapshot.child("Genre").getChildren()) {
                            genres.add(genreSnapshot.getValue(String.class));
                        }

                        if (genres.contains(genre)) {
                            moviesList.add(data);
                        }
                    }
                    if (recyclerView.getAdapter() == null) {
                        MoviesListAdapter adapter = new MoviesListAdapter(moviesList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void bannerList(ViewPager2 viewPager2) {
        ref = FirebaseDatabase.getInstance().getReference("Banners");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    ArrayList<BannerData> bannerList = new ArrayList<>();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        BannerData data = itemSnapshot.getValue(BannerData.class);
                        bannerList.add(data);
                    }

                    if (viewPager2.getAdapter() == null) {
                        BannerAdapter adapter = new BannerAdapter(bannerList, viewPager2);
                        viewPager2.setAdapter(adapter);
                    }else {
                        viewPager2.getAdapter().notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
