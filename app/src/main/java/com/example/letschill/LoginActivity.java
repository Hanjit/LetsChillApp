package com.example.letschill;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsernameET, loginPasswordET;
    Button loginLoginBtn, loginRegisterBtn;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLoginBtn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginUsernameET = findViewById(R.id.loginUsernameET);
        loginPasswordET = findViewById(R.id.loginPasswordET);
        loginLoginBtn = findViewById(R.id.loginLoginBtn);
        loginRegisterBtn = findViewById(R.id.loginRegisterBtn);

        loginRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = loginUsernameET.getText().toString();
                String password = loginPasswordET.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("Users");

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(name).exists()) {
                                if(snapshot.child(name).child("Password").getValue(String.class).equals(password)) {
                                    Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(LoginActivity.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }



            }
        });

    }
}