package com.example.moviesapp;

import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;

public class PorVerActivity extends BaseActivity {
    private ArrayList<String> porVerList;
    private PorVerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_por_ver, findViewById(R.id.content_frame), true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Por ver");
        }

        ListView lista = findViewById(R.id.lista_por_ver);
        EditText edt = findViewById(R.id.edt_pelicula_por_ver);
        Button btn = findViewById(R.id.btn_agregar_por_ver);

        porVerList = new ArrayList<>();
        adapter = new PorVerAdapter(this, porVerList);
        lista.setAdapter(adapter);

        btn.setOnClickListener(v -> {
            String nueva = edt.getText().toString().trim();
            if (!nueva.isEmpty()) {
                adapter.agregar(nueva);
                edt.setText("");
                Toast.makeText(this, "Agregada: " + nueva, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Escribe un nombre válido", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
