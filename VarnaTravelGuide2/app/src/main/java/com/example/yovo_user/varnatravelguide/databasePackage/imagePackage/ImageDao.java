package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;

public interface ImageDao {
    public void createImageTable(SQLiteDatabase dbWritableConnection);
    public void addImage(SQLiteDatabase dbWritableConnection,
                              Image[] images);
}
