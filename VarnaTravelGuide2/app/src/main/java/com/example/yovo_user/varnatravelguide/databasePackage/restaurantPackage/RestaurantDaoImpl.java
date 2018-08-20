package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

public class RestaurantDaoImpl implements RestaurantDao {

    @Override
    public void createRestaurantTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_RESTAURANTS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_RESTAURANTS_TABLE);
    }

    @Override
    public void addRestaurant(SQLiteDatabase dbWritableConnection, Restaurant[] restaurants) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < restaurants.length ;i++){
            values.put(DbStringConstants.R_PLACE_ID,restaurants[i].getPlaceId());
            values.put(DbStringConstants.R_PRICE_CATEGORY_ID,
                                                restaurants[i].getPriceCategoryId());
            values.put(DbStringConstants.R_COUSINE,restaurants[i].getCousine());
            dbWritableConnection.insert(DbStringConstants.TABLE_RESTAURANTS,
                                                   null, values);
        }
        dbWritableConnection.endTransaction();
    }
}
