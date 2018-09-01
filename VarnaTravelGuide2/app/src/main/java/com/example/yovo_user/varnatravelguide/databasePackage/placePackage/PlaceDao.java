package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.database.sqlite.SQLiteDatabase;

public interface PlaceDao {
    public void createPlacesTable(SQLiteDatabase dbWritableConnection);
    public void addPlaces(SQLiteDatabase dbWritableConnection,Place[] places);
    public Place getPlaceById(SQLiteDatabase dbWritableConnection,int placeId);
}
