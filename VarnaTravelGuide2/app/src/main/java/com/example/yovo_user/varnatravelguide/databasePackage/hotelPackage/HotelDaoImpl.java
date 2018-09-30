package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

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

public class HotelDaoImpl implements HotelDao{

    @Override
    public void createHotelTable(SQLiteDatabase db) throws SQLException {
        DbBaseOperations.dropTableX(db, DbStringConstants.TABLE_HOTELS);
        db.execSQL(DbStringConstants.CREATE_HOTELS_TABLE);
        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_HOTELS + " is being created !");

    }

    @Override
    public void addHotels(SQLiteDatabase dbWritableConnection, Hotel[] hotels) throws SQLException {

        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (int i = 0; i < hotels.length; i++) {
                values.put(DbStringConstants.H_PLACE_ID, hotels[i].getPlaceId());
                values.put(DbStringConstants.H_NUMB_OF_STARS, hotels[i].getNumbOfStars());
                values.put(DbStringConstants.H_PRICE_CATEGORY_ID,
                        hotels[i].getPriceCategoryId());

                dbWritableConnection.insert(DbStringConstants.TABLE_HOTELS,
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
    public List<Hotel> getAllHotels(SQLiteDatabase dbWritableConnection) {
        List<Hotel> allHotels = new ArrayList<Hotel>();

        try{
            Cursor cursor = dbWritableConnection.
                    rawQuery(DbStringConstants.GET_ALL_HOTELS,null);

            if (cursor.moveToFirst()) {
                do {
                    Hotel hotel = new Hotel(cursor.getInt(1),
                                            cursor.getInt(2),
                                            cursor.getInt(3));
                    allHotels.add(hotel);
                } while (cursor.moveToNext());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return allHotels;
    }

    @Override
    public Hotel getHotelByPlaceId(
            SQLiteDatabase dbWritableConnection,Integer placeId) {

        try{
            Cursor cursor = dbWritableConnection.
                    rawQuery(DbStringConstants.GET_ALL_HOTELS,null);

            if (cursor.moveToFirst()) {
                do {
                    Hotel hotel = new Hotel(
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getInt(3));

                    if(hotel.getPlaceId() == placeId){
                        return hotel;
                    }
                } while (cursor.moveToNext());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return null;
    }


}
