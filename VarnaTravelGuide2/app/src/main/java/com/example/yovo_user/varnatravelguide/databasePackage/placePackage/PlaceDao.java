package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.database.sqlite.SQLiteDatabase;

import org.bson.types.ObjectId;

public interface PlaceDao {
    public void createPlacesTable();
    public void addPlaces(Place[] places);
    public Place getPlaceById(ObjectId placeId);
}
