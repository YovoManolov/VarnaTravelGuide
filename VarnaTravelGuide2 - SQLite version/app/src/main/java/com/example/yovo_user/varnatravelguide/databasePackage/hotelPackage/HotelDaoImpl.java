package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

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

public class HotelDaoImpl implements HotelDao{

    private SQLiteDatabase dbWritableConnection;

    public HotelDaoImpl(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }
    @Override
    public void createHotelTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection, DbStringConstants.TABLE_HOTELS);

        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_HOTELS_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_HOTELS + " is being created !");

    }

    @Override
    public void addHotels(Hotel[] hotels) throws SQLException {

        dbWritableConnection.beginTransaction();
        try{

            for (int i = 0; i < hotels.length; i++) {
                ContentValues values = new ContentValues();
                values.put(DbStringConstants.H_PLACE_ID, hotels[i].getPlaceId());
                values.put(DbStringConstants.H_NUMB_OF_STARS, hotels[i].getNumbOfStars());
                values.put(DbStringConstants.H_PRICE_CATEGORY_ID,
                        hotels[i].getPriceCategoryId());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_HOTELS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_HOTELS + "for: i = " + i );
                }

                Log.d("Hotels ", " newly inserted row ID: " + rowId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> allHotels = new ArrayList<Hotel>();

        dbWritableConnection.beginTransaction();
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

            cursor.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return allHotels;
    }

    @Override
    public Hotel getHotelByPlaceId(Integer placeId) {

        dbWritableConnection.beginTransaction();
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

    public SQLiteDatabase getDbWritableConnection() {
        return dbWritableConnection;
    }

    public void setDbWritableConnection(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }


}
