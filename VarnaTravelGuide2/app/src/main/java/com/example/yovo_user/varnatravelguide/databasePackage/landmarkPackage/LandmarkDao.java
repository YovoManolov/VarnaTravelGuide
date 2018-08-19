package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.database.sqlite.SQLiteDatabase;

public interface LandmarkDao {
    public void createLandmarkTable(SQLiteDatabase dbWritableConnection);
    public void addLandmarks(SQLiteDatabase dbWritableConnection,
                                                    Landmark[] landmarks);
}
