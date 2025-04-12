package com.example.moviesapp;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InicioActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_inicio, findViewById(R.id.content_frame), true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Recomendaciones");
        }

        RecyclerView recycler = findViewById(R.id.recycler_inicio);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Pelicula> lista = new ArrayList<>();
        lista.add(new Pelicula("Matilda", R.drawable.matilda));
        lista.add(new Pelicula("Barbie", R.drawable.barbie));
        lista.add(new Pelicula("Dune", R.drawable.dune));
        lista.add(new Pelicula("Coraline", R.drawable.coraline));
        lista.add(new Pelicula("Past Lives", R.drawable.past_lives));
        lista.add(new Pelicula("La La Land", R.drawable.lalaland));

        PeliculaAdapter adapter = new PeliculaAdapter(lista);
        recycler.setAdapter(adapter);
    }
}
