package com.example.yovo_user.varnatravelguide;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
interface ShoppingPlaceDao {
    @Query("SELECT * FROM SHOPING_PLACES")
    public List<ShoppingPlace> getAllShoppingPlaces();
}
