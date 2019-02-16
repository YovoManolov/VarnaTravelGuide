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

    private SQLiteDatabase dbWritableConnection;

    public RestaurantDaoImpl(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }

    @Override
    public void createRestaurantTable() throws SQLException  {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_RESTAURANTS);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_RESTAURANTS_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_RESTAURANTS + " is being created !");
    }

    @Override
    public void addRestaurant(Restaurant[] restaurants) {

        dbWritableConnection.beginTransaction();
        try{
            for (int i = 0; i < restaurants.length; i++) {
                ContentValues values = new ContentValues();
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
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<Restaurant> getAllResaturants(){
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
    public Restaurant getRestaurantByPlaceId(Integer placeId){

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
