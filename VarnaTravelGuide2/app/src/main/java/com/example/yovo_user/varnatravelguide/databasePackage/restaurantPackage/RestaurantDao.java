package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;

public interface RestaurantDao {
    public void createRestaurantTable(SQLiteDatabase dbWritableConnection);
    public void addRestaurant(SQLiteDatabase dbWritableConnection,
                          Restaurant[] restaurants);
}
