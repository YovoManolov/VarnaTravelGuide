package com.example.yovo_user.varnatravelguide;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LandmarkDao {
    @Query("SELECT * FROM LANDMARK")
    public List<Hotels> getAllLandmarks();
}
