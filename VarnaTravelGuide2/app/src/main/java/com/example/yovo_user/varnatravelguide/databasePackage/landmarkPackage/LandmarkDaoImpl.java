package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_LANDMARKS + " is being created !");

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

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_LANDMARKS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_LANDMARKS + "for: i = " + i );
                }

                Log.d("Landmarks ", " newly inserted row ID: " + rowId);
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
                Landmark landmark = new Landmark(
                        cursor.getInt(1),
                        cursor.getString(2)
                );
                allLandmarks.add(landmark);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return allLandmarks;
    }

    @Override
    public Landmark getLandmarkByPlaceId(SQLiteDatabase dbWritableConnection,Integer placeId){

        try{
            Cursor cursor = dbWritableConnection.
                    rawQuery(DbStringConstants.GET_ALL_LANDMARKS,null);

            if (cursor.moveToFirst()) {
                do {
                    Landmark landmark = new Landmark(
                            cursor.getInt(1),
                            cursor.getString(2));

                    if(landmark.getPlaceId() == placeId){
                        return landmark;
                    }
                } while (cursor.moveToNext());
            }

            cursor.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return null;
    }
}
