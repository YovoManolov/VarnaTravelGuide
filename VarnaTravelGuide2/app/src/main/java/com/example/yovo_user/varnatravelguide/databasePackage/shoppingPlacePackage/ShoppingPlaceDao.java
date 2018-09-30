package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

import java.util.List;

public interface ShoppingPlaceDao {
    public void createShoppingPlacesTable(SQLiteDatabase dbWritableConnection);
    public void addShoppingPlaces(SQLiteDatabase dbWritableConnection,
                         ShoppingPlace[] shoppingPlaces);
    public List<ShoppingPlace> getAllShoppingPlaces(SQLiteDatabase dbReadableConnection);
    public ShoppingPlace getShoppingPlaceByPlaceId
            (SQLiteDatabase dbWritableConnection,Integer placeId);
}
