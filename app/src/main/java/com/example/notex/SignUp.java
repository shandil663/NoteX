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

public class SignUp extends AppCompatActivity {
    EditText txt1, txt2, txt3;
    Button signUp;

    TextView txt;

    FirebaseAuth auth;
    CardView cardView;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lottieAnimationView=findViewById(R.id.loding1);
cardView=findViewById(R.id.cardView);
        txt1 = findViewById(R.id.name);
        txt2 = findViewById(R.id.email);
        txt3 = findViewById(R.id.pass);
        signUp = findViewById(R.id.SignUp);
        txt = findViewById(R.id.SignInpage);
        auth=FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txt1.getText().toString().isEmpty()&&!txt2.getText().toString().isEmpty()&&!txt3.getText().toString().isEmpty()){
                    cardView.setVisibility(View.INVISIBLE);
                    lottieAnimationView.setVisibility(View.VISIBLE);

                    auth.createUserWithEmailAndPassword(txt2.getText().toString(),txt3.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(SignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(), MainActivity.class));
                               finish();
                           }
                           else {
                               cardView.setVisibility(View.VISIBLE);
                               lottieAnimationView.setVisibility(View.GONE);
                               Toast.makeText(SignUp.this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUp.this, "Please enter required fields", Toast.LENGTH_SHORT).show();
                }
            }

        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}