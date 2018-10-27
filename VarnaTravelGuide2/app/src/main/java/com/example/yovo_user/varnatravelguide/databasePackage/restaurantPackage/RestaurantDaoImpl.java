package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    @Override
    public void createRestaurantTable(SQLiteDatabase dbWritableConnection) throws SQLException  {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_RESTAURANTS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_RESTAURANTS_TABLE);
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_RESTAURANTS + " is being created !");
    }

    @Override
    public void addRestaurant(SQLiteDatabase dbWritableConnection, Restaurant[] restaurants) {

        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (int i = 0; i < restaurants.length; i++) {
                values.put(DbStringConstants.R_PLACE_ID, restaurants[i].getPlaceId());
                values.put(DbStringConstants.R_PRICE_CATEGORY_ID,
                        restaurants[i].getPriceCategoryId());
                values.put(DbStringConstants.R_COUSINE, restaurants[i].getCousine());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_RESTAURANTS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_RESTAURANTS + "for: i = " + i );
                }

                Log.d("Restaurants  ", " newly inserted row ID: " + rowId);
                values = new ContentValues();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<Restaurant> getAllResaturants(SQLiteDatabase dbWritableConnection){
        List<Restaurant> allRestaurants = new ArrayList<Restaurant>();

        dbWritableConnection.beginTransaction();

        try{

            Cursor cursor = dbWritableConnection.rawQuery(DbStringConstants.GET_ALL_RESTAURANTS,
                    null);

            if (cursor.moveToFirst()) {
                do {
                    Restaurant restaurant = new Restaurant(
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3)
                    );
                    allRestaurants.add(restaurant);
                } while (cursor.moveToNext());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return allRestaurants;
    }

    @Override
    public Restaurant getRestaurantByPlaceId
            (SQLiteDatabase dbWritableConnection, Integer placeId){

        dbWritableConnection.beginTransaction();
        try{
            Cursor cursor = dbWritableConnection.
                    rawQuery(DbStringConstants.GET_ALL_RESTAURANTS,null);

            if (cursor.moveToFirst()) {
                do {
                    Restaurant restaurant = new Restaurant(
                            cursor.getInt(1),
                            cursor.getInt(2),
                            cursor.getString(3)
                    );

                    if(restaurant.getPlaceId() == placeId){
                        return restaurant;
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
