package com.example.moviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class PorVerAdapter extends ArrayAdapter<String> {
    private final ArrayList<String> peliculas;
    private final ArrayList<Boolean> estados;

    public PorVerAdapter(Context context, ArrayList<String> peliculas) {
        super(context, 0, peliculas);
        this.peliculas = peliculas;
        this.estados = new ArrayList<>();
        for (int i = 0; i < peliculas.size(); i++) {
            estados.add(false);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            item = LayoutInflater.from(getContext()).inflate(R.layout.item_pelicula_checklist, parent, false);
        }

        CheckBox checkBox = item.findViewById(R.id.checkbox_pelicula);
        TextView texto = item.findViewById(R.id.texto_pelicula);

        String pelicula = peliculas.get(position);
        texto.setText(pelicula);
        checkBox.setChecked(estados.get(position));

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            estados.set(position, isChecked);
            if (isChecked) {
                Toast.makeText(getContext(), "¡Ya viste: " + pelicula + "!", Toast.LENGTH_SHORT).show();
            }
        });

        return item;
    }

    public void agregar(String nueva) {
        peliculas.add(nueva);
        estados.add(false);
        notifyDataSetChanged();
    }
}
