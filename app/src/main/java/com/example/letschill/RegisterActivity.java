package com.example.letschill;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmailET, registerUsernameET, registerPasswordET, registerConfirmET;
    Button registerRegisterBtn;
    DatabaseReference database;
    TextView registerLoginTV;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLoginBtn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerEmailET = findViewById(R.id.registerEmailET);
        registerUsernameET = findViewById(R.id.registerUsernameET);
        registerPasswordET = findViewById(R.id.registerPasswordET);
        registerConfirmET = findViewById(R.id.registerConfirmET);
        registerRegisterBtn = findViewById(R.id.registerRegisterBtn);
        registerLoginTV = findViewById(R.id.registerLoginTV);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://letschill-01-default-rtdb.asia-southeast1.firebasedatabase.app/");

        auth = FirebaseAuth.getInstance();

        registerLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEmailET.getText().toString();
                String username = registerUsernameET.getText().toString();
                String password = registerPasswordET.getText().toString();
                String confirm = registerConfirmET.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)){
                    Toast.makeText(RegisterActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!password.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Enter same password", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                               Toast.makeText(RegisterActivity.this, "Register Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                               startActivity(intent);
                               finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Register Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    database = FirebaseDatabase.getInstance().getReference("Users");
                    database.child(username).get().addOnCompleteListener(task -> {
                       if (task.isSuccessful()) {
                           if (task.getResult().exists()) {
                               Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                           } else {
                               database.child(username).child("UserName").setValue(username);
                               database.child(username).child("Email").setValue(email);
                               database.child(username).child("Password").setValue(password);
                           }
                       }
                    });

                }
            }
        });
    }
}