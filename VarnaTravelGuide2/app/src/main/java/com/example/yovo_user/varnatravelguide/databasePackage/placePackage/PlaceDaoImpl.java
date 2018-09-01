package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

public class PlaceDaoImpl implements PlaceDao {
    @Override
    public void createPlacesTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PLACES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_PLACES_TABLE);
    }

    @Override
    public void addPlaces(SQLiteDatabase dbWritableConnection,Place[] places){
        dbWritableConnection.beginTransaction();

        for(int i = 0 ;i < places.length ;i++){
            ContentValues values = new ContentValues();

            if(places[i].getHotelId() != 0){
                values.put(DbStringConstants.PL_HOTEL_ID,places[i].getHotelId());
            }else if(places[i].getLandmarkId() != 0){
                values.put(DbStringConstants.PL_LANDMARK_ID,places[i].getLandmarkId());
            }else if(places[i].getRestaurantId() != 0){
                values.put(DbStringConstants.PL_RESTAURANT_ID,places[i].getRestaurantId());
            }else if(places[i].getShoppingPlaceId() != 0){
                values.put(DbStringConstants.PL_SHOPPING_PLACES_ID,places[i].getShoppingPlaceId());
            }
            values.put(DbStringConstants.PL_WORK_HOURS_ID, places[i].getWorkHoursId());
            values.put(DbStringConstants.PL_NAME, places[i].getName());
            values.put(DbStringConstants.PL_ADDRESS, places[i].getAddress());
            values.put(DbStringConstants.PL_LATITUDE, places[i].getLatitude());
            values.put(DbStringConstants.PL_LONGITUDE, places[i].getLongitude());
            values.put(DbStringConstants.PL_CONTACTS, places[i].getContacts());
            values.put(DbStringConstants.PL_DESCRIPTION, places[i].getDescription());

            dbWritableConnection.insert(DbStringConstants.TABLE_PLACES, null, values);
        }

        dbWritableConnection.endTransaction();
    }

    @Override
    public Place getPlaceById(SQLiteDatabase dbReadableConnection, int placeId) {
        dbReadableConnection.beginTransaction();
        Place place = null;
        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_PLACE_BY_ID,
                new String[]{
                        String.valueOf(placeId)
                });

            if(cursor.getCount()!= 1){

                if (cursor.moveToFirst()) {
                    do {
                        place = new Place(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getInt(2),
                                cursor.getInt(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getDouble(6),
                                cursor.getDouble(7),
                                cursor.getString(8),
                                cursor.getString(9),
                                cursor.getInt(10)
                                );
                    } while (cursor.moveToNext());
                }
            }

        dbReadableConnection.endTransaction();
        return place;
    }
}
