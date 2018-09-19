package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

public class PlaceDaoImpl implements PlaceDao {
    @Override
    public void addPlaces(SQLiteDatabase dbWritableConnection,Place[] places){

        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for(int i = 0 ;i < places.length ;i++){

                values.put(DbStringConstants.PL_NAME, places[i].getName());
                values.put(DbStringConstants.PL_ADDRESS, places[i].getAddress());
                values.put(DbStringConstants.PL_LATITUDE, places[i].getLatitude());
                values.put(DbStringConstants.PL_LONGITUDE, places[i].getLongitude());
                values.put(DbStringConstants.PL_CONTACTS, places[i].getContacts());
                values.put(DbStringConstants.PL_DESCRIPTION, places[i].getDescription());

                dbWritableConnection.insert(DbStringConstants.TABLE_PLACES,
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
    public void createPlacesTable(SQLiteDatabase dbWritableConnection) throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PLACES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_PLACES_TABLE);
    }

    @Override
    public Place getPlaceById(SQLiteDatabase dbReadableConnection, int placeId) {
        dbReadableConnection.beginTransaction();
        Place place = null;

        try{

            Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_PLACE_BY_ID,
                new String[]{
                        String.valueOf(placeId)
                });

            if(cursor.getCount()!= 1){

                if (cursor.moveToFirst()) {
                    do {
                        place = new Place(
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getDouble(3),
                                cursor.getDouble(4),
                                cursor.getString(5),
                                cursor.getString(6)
                        );
                    } while (cursor.moveToNext());
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbReadableConnection.endTransaction();
        }
        return place;
    }
}
