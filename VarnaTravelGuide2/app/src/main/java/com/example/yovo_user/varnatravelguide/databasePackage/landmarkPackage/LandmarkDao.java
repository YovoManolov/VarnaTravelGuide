package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;

import java.util.List;

public interface LandmarkDao {
    public void createLandmarkTable();
    public void addLandmarks(Landmark[] landmarks);
    public List<Landmark> getAllLandmarks();
    public Landmark getLandmarkByPlaceId(Integer placeId);
}
