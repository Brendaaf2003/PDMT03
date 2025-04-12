package com.example.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;


public class MoviesActivity extends BaseActivity {
    private ArrayList<String> peliculas;
    private ArrayAdapter<String> adapter;
    private ListView listaPeliculas;
    private EditText edtNuevaPelicula;
    private Button btnAgregarPelicula;
    private boolean modoSeleccion = false;
    private ArrayList<Integer> seleccionados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_movies, findViewById(R.id.content_frame), true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("PopCorns");
        }

        peliculas = new ArrayList<>();
        peliculas.add("The Wild Robot");
        peliculas.add("Paris, Texas");
        peliculas.add("Matilda");
        peliculas.add("The VelociPastor");
        peliculas.add("Taylor Swift: The Eras Tour");

        listaPeliculas = findViewById(R.id.lista_peliculas);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, peliculas);
        listaPeliculas.setAdapter(adapter);

        edtNuevaPelicula = findViewById(R.id.edt_nueva_pelicula);
        btnAgregarPelicula = findViewById(R.id.btn_agregar_pelicula);

        btnAgregarPelicula.setOnClickListener(v -> {
            String nuevaPelicula = edtNuevaPelicula.getText().toString().trim();
            if (!nuevaPelicula.isEmpty()) {
                peliculas.add(nuevaPelicula);
                adapter.notifyDataSetChanged();
                edtNuevaPelicula.setText("");
                Toast.makeText(MoviesActivity.this, "Película agregada: " + nuevaPelicula, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MoviesActivity.this, "Ingrese un nombre válido", Toast.LENGTH_SHORT).show();
            }
        });

        listaPeliculas.setOnItemClickListener((parent, view, position, id) -> {
            String peliculaSeleccionada = peliculas.get(position);
            Intent intent = new Intent(MoviesActivity.this, ReviewActivity.class);
            intent.putExtra("pelicula", peliculaSeleccionada);
            startActivity(intent);
        });
    }

    private void mostrarListaConCheckbox() {
        listaPeliculas.setAdapter(new ArrayAdapter<String>(this, R.layout.item_pelicula_check, R.id.texto_pelicula, peliculas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                CheckBox checkBox = view.findViewById(R.id.checkbox);
                checkBox.setChecked(seleccionados.contains(position));

                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        if (!seleccionados.contains(position)) {
                            seleccionados.add(position);
                        }
                    } else {
                        seleccionados.remove(Integer.valueOf(position));
                    }
                });

                return view;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    private void mostrarListaNormal() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, peliculas);
        listaPeliculas.setAdapter(adapter);
    }

    private void eliminarSeleccionados() {
        // Orden inverso para evitar conflictos al eliminar
        seleccionados.sort((a, b) -> b - a);
        for (int index : seleccionados) {
            peliculas.remove(index);
        }
        seleccionados.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Películas eliminadas", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_eliminar) {
            if (!modoSeleccion) {
                modoSeleccion = true;
                seleccionados.clear();
                mostrarListaConCheckbox();
            } else {
                eliminarSeleccionados();
                modoSeleccion = false;
                mostrarListaNormal();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
