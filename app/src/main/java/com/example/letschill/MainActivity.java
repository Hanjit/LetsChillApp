package com.example.letschill;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    private FrameLayout frameLayoutMenu;
    private ImageView mainProfileIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLoginBtn), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragmentContainer, new MoviesFragment())
                .commit();

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setSelectedItemId(R.id.navHome);
        frameLayoutMenu = findViewById(R.id.frameLayoutMenu);
        mainProfileIv = findViewById(R.id.mainProfileIv);

        // assign navbar
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = manager.beginTransaction();

                if (item.getItemId() == R.id.navHome) {
                    transaction.replace(R.id.fragmentContainer, new MoviesFragment());
                    transaction.addToBackStack(null);
                } else if (item.getItemId() == R.id.navSearch) {
                    transaction.replace(R.id.fragmentContainer, new MoviesFragment());
                    transaction.addToBackStack(null);
                } else if (item.getItemId() == R.id.navPopular) {
                    transaction.replace(R.id.fragmentContainer, new PopularFragment());
                    transaction.addToBackStack(null);
                } else if (item.getItemId() == R.id.navNotification) {
                    transaction.replace(R.id.fragmentContainer, new NotificationFragment());
                } else {
                    return false;
                }

                transaction.commit();
                return true;
            }
        });

        mainProfileIv.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}