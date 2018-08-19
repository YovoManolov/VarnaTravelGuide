package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

public class HotelDaoImpl implements HotelDao {

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
}
