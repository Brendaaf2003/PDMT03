package com.example.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, findViewById(R.id.content_frame), true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Bienvenida");
        }

        Button btnVerPeliculas = findViewById(R.id.btn_ver_peliculas);
        btnVerPeliculas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            startActivity(intent);
        });
    }
}
