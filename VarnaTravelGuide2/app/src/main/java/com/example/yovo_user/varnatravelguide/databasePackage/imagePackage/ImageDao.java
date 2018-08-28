package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface ImageDao {
    public void createImageTable(SQLiteDatabase dbWritableConnection);
    public void addImage(SQLiteDatabase dbWritableConnection,
                              Image[] images);
    public List<Image> getImagesForPlace(SQLiteDatabase dbReadableConnection,int placeId);
}
