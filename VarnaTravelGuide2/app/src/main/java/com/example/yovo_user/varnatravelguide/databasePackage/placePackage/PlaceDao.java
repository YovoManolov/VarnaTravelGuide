package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import org.bson.types.ObjectId;

import java.util.List;

public interface PlaceDao {
/*    public void createPlacesTable();
    public void addPlaces(Place[] places);*/
    public Place getPlaceById(ObjectId placeId) throws InterruptedException;
    public List<Place> getPlacesByTypeOfPlace(int typeOfPlace) throws InterruptedException;
}
