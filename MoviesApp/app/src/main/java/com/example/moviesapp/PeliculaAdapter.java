package com.example.moviesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder> {
    private List<Pelicula> listaPeliculas;

    public PeliculaAdapter(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPoster;
        public TextView txtTitulo;

        public ViewHolder(View view) {
            super(view);
            imgPoster = view.findViewById(R.id.img_poster);
            txtTitulo = view.findViewById(R.id.txt_titulo);
        }
    }

    @Override
    public PeliculaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pelicula pelicula = listaPeliculas.get(position);
        holder.txtTitulo.setText(pelicula.getTitulo());
        holder.imgPoster.setImageResource(pelicula.getImagenResId());
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }
}
