package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;

import java.util.List;

public interface RestaurantDao {
    public void createRestaurantTable(SQLiteDatabase dbWritableConnection);
    public void addRestaurant(
            SQLiteDatabase dbWritableConnection, Restaurant[] restaurants);
    public List<Restaurant> getAllResaturants(SQLiteDatabase dbReadableConnection);
    public Restaurant getRestaurantByPlaceId
            (SQLiteDatabase dbWritableConnection, Integer placeId);

}
