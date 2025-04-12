package com.example.moviesapp;

public class Pelicula {
    private String titulo;
    private int imagenResId;

    public Pelicula(String titulo, int imagenResId) {
        this.titulo = titulo;
        this.imagenResId = imagenResId;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
