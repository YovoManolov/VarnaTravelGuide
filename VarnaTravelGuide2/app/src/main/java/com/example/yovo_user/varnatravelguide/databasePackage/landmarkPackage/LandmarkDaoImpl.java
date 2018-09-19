package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;

import java.util.ArrayList;
import java.util.List;

public class LandmarkDaoImpl implements LandmarkDao {
    @Override
    public void createLandmarkTable(SQLiteDatabase dbWritableConnection) throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,
                                        DbStringConstants.TABLE_LANDMARKS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_LANDMARKS_TABLE);
    }

    @Override
    public void addLandmarks(SQLiteDatabase dbWritableConnection, Landmark[] landmarks) {
        dbWritableConnection.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            for (int i = 0; i < landmarks.length; i++) {
                values.put(DbStringConstants.L_PLACE_ID, landmarks[i].getPlaceId());
                values.put(DbStringConstants.L_ENTRANCE_TICKET,
                        landmarks[i].getEntranceTicket());

                dbWritableConnection.insert(DbStringConstants.TABLE_LANDMARKS,
                        null, values);
                values.clear();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<Landmark> getAllLandmarks(SQLiteDatabase dbReadableConnection) throws SQLException {
        List<Landmark> allLandmarks = new ArrayList<Landmark>();

        Cursor cursor = dbReadableConnection.rawQuery(
                DbStringConstants.GET_ALL_LANDMARKS,null);

        if (cursor.moveToFirst()) {
            do {
                Landmark landmark = new Landmark(cursor.getInt(1),
                                                            cursor.getString(2));
                allLandmarks.add(landmark);
            } while (cursor.moveToNext());
        }

        return allLandmarks;
    }
}
