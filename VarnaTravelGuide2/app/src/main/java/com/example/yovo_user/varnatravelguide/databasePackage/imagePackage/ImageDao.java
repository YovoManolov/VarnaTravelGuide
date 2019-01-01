package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface ImageDao {
    public void createImageTable();
    public void addImage(Image[] images);
    public List<Image> getImagesForPlace(int placeId);
}
