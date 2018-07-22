package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM RESTAURANT")
    List<Restaurant> getAllRestaurants();

    @Insert
    void insertAll(Restaurant... restaurants);
}
