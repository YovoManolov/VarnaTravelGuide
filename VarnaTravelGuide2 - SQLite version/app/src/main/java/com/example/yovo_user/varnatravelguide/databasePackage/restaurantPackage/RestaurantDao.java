package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;

import java.util.List;

public interface RestaurantDao {
    public void createRestaurantTable();
    public void addRestaurant(Restaurant[] restaurants);
    public List<Restaurant> getAllResaturants();
    public Restaurant getRestaurantByPlaceId(Integer placeId);
}
