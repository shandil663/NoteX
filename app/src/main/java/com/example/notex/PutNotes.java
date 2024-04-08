package com.example.notex;

import android.content.Intent;
import android.os.Bundle;
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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PutNotes extends AppCompatActivity {

    EditText txt1, txt2;
    Button btn;

    FirebaseDatabase db;
    DatabaseReference rf;

    FirebaseAuth muth;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_put_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lottieAnimationView=findViewById(R.id.loding2);
        muth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        rf = db.getReference(muth.getCurrentUser().getUid());
        txt1 = findViewById(R.id.title);
        txt2 = findViewById(R.id.mainnote);
        btn = findViewById(R.id.Save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txt1.getText().toString().isEmpty() && !txt2.getText().toString().isEmpty()) {
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    DataModel dt = new DataModel(txt1.getText().toString(), txt2.getText().toString());
                    UUID uuid = UUID.randomUUID();
                    String uuidAsString = uuid.toString();
                    rf.child(uuidAsString).setValue(dt).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                lottieAnimationView.setVisibility(View.GONE);
                                Toast.makeText(PutNotes.this, "Notes Synced successfully with cloud", Toast.LENGTH_SHORT).show();
                                txt1.setText("");
                                txt2.setText("");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(PutNotes.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(PutNotes.this, "Enter all required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}