package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

public class LandmarkDaoImpl implements LandmarkDao {
    @Override
    public void createLandmarkTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection,
                                        DbStringConstants.TABLE_LANDMARKS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_HOTELS_TABLE);
    }

    @Override
    public void addLandmarks(SQLiteDatabase dbWritableConnection, Landmark[] landmarks) {

    }
}
