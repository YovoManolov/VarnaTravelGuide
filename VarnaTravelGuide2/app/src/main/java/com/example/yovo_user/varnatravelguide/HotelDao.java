package com.example.yovo_user.varnatravelguide;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface HotelDao {
    @Query("SELECT * FROM HOTELS")
    public List<Hotels> getAllHotels();
}
