package com.example.listviewapril;

import android.net.Uri;

public class Item {
    private String name;
    private Uri imageUri;

    public Item(String name, Uri imageUri) {
        this.name = name;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return name;
    }
}

