package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

import java.util.List;

public interface ShoppingPlaceDao {

    public void createShoppingPlacesTable();
    public void addShoppingPlaces(ShoppingPlace[] shoppingPlaces);
    public List<ShoppingPlace> getAllShoppingPlaces();
    public ShoppingPlace getShoppingPlaceByPlaceId(Integer placeId);

}
