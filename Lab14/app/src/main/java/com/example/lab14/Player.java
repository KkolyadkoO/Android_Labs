package com.example.lab14;

import android.graphics.Bitmap;

public class Player {
    private int number;
    private String lastName;
    private String imageUrl;
    private Bitmap bitmap;

    public Player(int number, String lastName, String imageUrl) {
        this.number = number;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
