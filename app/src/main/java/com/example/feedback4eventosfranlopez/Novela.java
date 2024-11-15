package com.example.feedback4eventosfranlopez;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Novela implements Parcelable {
    private int id;
    private String title;
    private String author;
    private boolean isFavorite;

    // Constructor con ID para cuando se lee desde la base de datos
    public Novela(int id, String title, String author, boolean isFavorite) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isFavorite = isFavorite;
    }

    // Constructor sin ID para cuando se crea una nueva novela
    public Novela(String title, String author, boolean isFavorite) {
        this.title = title;
        this.author = author;
        this.isFavorite = isFavorite;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}

