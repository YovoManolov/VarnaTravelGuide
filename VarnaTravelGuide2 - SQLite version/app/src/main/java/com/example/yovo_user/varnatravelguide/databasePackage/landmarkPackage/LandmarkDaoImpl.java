package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class LandmarkDaoImpl implements LandmarkDao {

    private SQLiteDatabase dbWritableConnection;

    public LandmarkDaoImpl(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }

    @Override
    public void createLandmarkTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,
                                        DbStringConstants.TABLE_LANDMARKS);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_LANDMARKS_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_LANDMARKS + " is being created !");

    }

    @Override
    public void addLandmarks(Landmark[] landmarks) {
        dbWritableConnection.beginTransaction();
        try {
            for (int i = 0; i < landmarks.length; i++) {
                ContentValues values = new ContentValues();
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
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<Landmark> getAllLandmarks() throws SQLException {
        List<Landmark> allLandmarks = new ArrayList<Landmark>();

        Cursor cursor = dbWritableConnection.rawQuery(
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
    public Landmark getLandmarkByPlaceId(Integer placeId){

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
