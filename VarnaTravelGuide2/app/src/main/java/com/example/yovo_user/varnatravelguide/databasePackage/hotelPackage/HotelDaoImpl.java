package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;

import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl implements HotelDao{

    @Override
    public void createHotelTable(SQLiteDatabase db) {
        DbBaseOperations.dropTableX(db, DbStringConstants.TABLE_HOTELS);
        db.execSQL(DbStringConstants.CREATE_HOTELS_TABLE);
    }

    @Override
    public void addHotels(SQLiteDatabase dbWritableConnection, Hotel[] hotels) {

        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < hotels.length ;i++){
            values.put(DbStringConstants.H_PLACE_ID, hotels[i].getPlaceId());
            values.put(DbStringConstants.H_NUMB_OF_STARS, hotels[i].getNumbOfStars());
            values.put(DbStringConstants.H_PRICE_CATEGORY_ID,
                                        hotels[i].getPriceCategoryId());

            dbWritableConnection.insert(DbStringConstants.TABLE_HOTELS,
                                        null, values);
        }

        dbWritableConnection.endTransaction();
    }

    @Override
    public List<Hotel> getAllHotels(SQLiteDatabase dbReadableConnection) {
        List<Hotel> allHotels = new ArrayList<Hotel>();

        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_ALL_HOTELS,null);

        if (cursor.moveToFirst()) {
            do {
                Hotel hotel = new Hotel(cursor.getInt(1),cursor.getInt(2)
                                        ,cursor.getInt(3));
                allHotels.add(hotel);
            } while (cursor.moveToNext());
        }

        return allHotels;
    }


}
