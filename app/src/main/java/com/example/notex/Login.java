package com.example.notex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText txt1, txt2;
    Button btn;
    TextView signuppage;

    FirebaseAuth auth;
    CardView cardView;
    LottieAnimationView lottieAnimationView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cardView=findViewById(R.id.cardView1);
        lottieAnimationView=findViewById(R.id.loding3);
        txt1 = findViewById(R.id.editTextTextEmailAddress);
        txt2 = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.Signin);
        signuppage = findViewById(R.id.regpageopen);
        auth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txt1.getText().toString().isEmpty() && !txt1.getText().toString().isEmpty()) {
                    cardView.setVisibility(View.GONE);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(txt1.getText().toString(), txt2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                lottieAnimationView.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }

                            else {
                                cardView.setVisibility(View.VISIBLE);
                                lottieAnimationView.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else{
                    Toast.makeText(Login.this, "Please enter required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }
}