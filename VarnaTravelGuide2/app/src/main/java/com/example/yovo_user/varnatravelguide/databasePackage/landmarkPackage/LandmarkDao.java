package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;

import org.bson.types.ObjectId;

import java.util.List;

public interface LandmarkDao {
    /*public void createLandmarkTable();
    public void addLandmarks(Landmark[] landmarks);*/
    public List<Landmark> getAllLandmarks() throws InterruptedException;
    public Landmark getLandmarkByPlaceId(ObjectId place_id) throws InterruptedException;
}
