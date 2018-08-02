package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM HOTELS")
    @Transaction
    List<Hotel> getAllHotels();

    @Insert
    void insertAll(Hotel... hotels);
}
