package com.example.feedback4eventosfranlopez;

import android.os.Parcel;
import android.os.Parcelable;

public class Novela implements Parcelable {
    private int id;
    private String title;
    private String author;
    private boolean isFavorite;
    private double latitude;
    private double longitude;

    // Constructor with location
    public Novela(int id, String title, String author, boolean isFavorite, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isFavorite = isFavorite;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Constructor without location
    public Novela(int id, String title, String author, boolean isFavorite) {
        this(id, title, author, isFavorite, 0.0, 0.0);
    }

    // Constructor without ID for new novels
    public Novela(String title, String author, boolean isFavorite) {
        this(-1, title, author, isFavorite, 0.0, 0.0);
    }

    // Getters and Setters
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public static final Parcelable.Creator<Novela> CREATOR = new Parcelable.Creator<Novela>() {
        @Override
        public Novela createFromParcel(Parcel in) {
            return new Novela(in);
        }

        @Override
        public Novela[] newArray(int size) {
            return new Novela[size];
        }
    };

    protected Novela(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        isFavorite = in.readByte() != 0;
        latitude = in.readDouble();
        longitude = in.readDouble();
    }
}