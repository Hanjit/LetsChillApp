package com.example.letschill;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoViewVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        videoViewVV = findViewById(R.id.videoViewVV);

        String videoUrl = getIntent().getStringExtra("videoUrl");
        if (videoUrl != null) {
            Uri videoUri = Uri.parse(videoUrl);
            videoViewVV.setVideoURI(videoUri);

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoViewVV);
            videoViewVV.setMediaController(mediaController);

            videoViewVV.start();
        }

    }

}