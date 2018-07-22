package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
interface ShoppingPlaceDao {
    @Query("SELECT * FROM SHOPING_PLACES")
    List<ShoppingPlace> getAllShoppingPlaces();

    @Insert
    void insertAll(ShoppingPlace... shoppingPlaces);
}
