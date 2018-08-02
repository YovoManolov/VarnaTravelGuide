package com.example.yovo_user.varnatravelguide.databasePackage;

import java.nio.ByteBuffer;

public class Image {

    private int id;
    private int placeId;
    private String imageURL;

    public Image(int id, int placeId, String imageURL) {
        this.id = id;
        this.placeId = placeId;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
