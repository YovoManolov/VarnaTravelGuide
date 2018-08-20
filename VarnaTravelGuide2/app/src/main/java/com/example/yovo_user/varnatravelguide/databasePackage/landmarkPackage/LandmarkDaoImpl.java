package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.content.ContentValues;
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
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < landmarks.length ;i++){
            values.put(DbStringConstants.L_PLACE_ID, landmarks[i].getPlaceId());
            values.put(DbStringConstants.L_ENTRANCE_TICKET,
                    landmarks[i].getEntranceTicket());

            dbWritableConnection.insert(DbStringConstants.TABLE_LANDMARKS,
                    null, values);
        }
        dbWritableConnection.endTransaction();
    }
}
