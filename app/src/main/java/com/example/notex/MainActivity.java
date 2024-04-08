package com.example.notex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton flt,fltlogout;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    DatabaseReference ref;

    FirebaseAuth auth;

    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        fltlogout=findViewById(R.id.logout);
        fltlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        lottieAnimationView=findViewById(R.id.loding);

        ref = FirebaseDatabase.getInstance().getReference(auth.getCurrentUser().getUid());
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataModel> options = new FirebaseRecyclerOptions.Builder<DataModel>().setQuery(ref, DataModel.class).build();
        notesAdapter=new NotesAdapter(options);
        recyclerView.setAdapter(notesAdapter);

        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        };
        handler.postDelayed(runnable,3000);
        flt = findViewById(R.id.floatingActionButton);
        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PutNotes.class));
                Log.d("hello","hello");
                finish();
            }
        });
    }
    @Override protected void onStart()
    {
        super.onStart();
        notesAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        notesAdapter.stopListening();
    }
}


