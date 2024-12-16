package com.example.letschill;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.letschill.adapters.ShowcaseAdapter;
import com.example.letschill.models.BannerData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button homeLoginBtn, homeRegisterBtn;
    private TextView homeLoginTv;
    private ImageView homeKidsIv;
    private ViewPager2 homeCarouselVp;
    private ArrayList<BannerData> movies;
    private DatabaseReference myRef;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentPage = 0;
    private final int delay = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeLoginBtn = findViewById(R.id.homeLoginBtn);
        homeRegisterBtn = findViewById(R.id.homeRegisterBtn);
        homeLoginTv = findViewById(R.id.homeLoginTv);
        homeKidsIv = findViewById(R.id.homeKidsIv);
        homeCarouselVp = findViewById(R.id.homeCarouselVp);
        movies = new ArrayList<>();
        myRef = FirebaseDatabase.getInstance().getReference("Banners");
        runnable = new Runnable() {
            @Override
            public void run() {
                if (movies.size() > 0) {
                    if (currentPage == movies.size()){
                        currentPage = 0;
                    }
                    homeCarouselVp.setCurrentItem(currentPage++, true);
                }
                handler.postDelayed(this, delay);
            }
        };

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot data : snapshot.getChildren()){
                        BannerData item = data.getValue(BannerData.class);
                        movies.add(item);
                    }
                }

                if (homeCarouselVp.getAdapter() == null) {
                    ShowcaseAdapter adapter = new ShowcaseAdapter(movies, homeCarouselVp);
                    homeCarouselVp.setAdapter(adapter);
                } else {
                    homeCarouselVp.getAdapter().notifyDataSetChanged();
                }

                handler.postDelayed(runnable, delay);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        homeLoginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        homeRegisterBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        homeLoginTv.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new CenterCrop());
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/letschill-01.appspot.com/o/kids.jpg?alt=media&token=308b9da1-dcd5-45c4-815c-d6635bc0df48")
                .apply(requestOptions)
                .into(homeKidsIv);

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }
}