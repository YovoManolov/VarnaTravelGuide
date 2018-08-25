package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface LandmarkDao {
    public void createLandmarkTable(SQLiteDatabase dbWritableConnection);
    public void addLandmarks(SQLiteDatabase dbWritableConnection,
                                                    Landmark[] landmarks);
    public List<Landmark> getAllHLandmarks();
}
