package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.database.sqlite.SQLiteDatabase;

public interface PlaceDao {
    public void createPlacesTable();
    public void addPlaces(Place[] places);
    public Place getPlaceById(int placeId);
}
