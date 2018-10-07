package com.example.yovo_user.varnatravelguide.databasePackage;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbBaseOperations {

    public static void dropTableX(SQLiteDatabase dbWritableConnection,String tableName){
        dbWritableConnection.beginTransaction();
        try{
            dbWritableConnection.execSQL(DbStringConstants.DROP_TABLE_X + tableName);
        }catch(SQLException e){
            e.printStackTrace();
            Log.e("VTGDatabase","error dropping table " + tableName);
        }finally{
            dbWritableConnection.endTransaction();
        }
    }


    public static void upgradeDb(SQLiteDatabase dbWritableConnection, int oldVersion, int newVersion) {

        dropTableX(dbWritableConnection,DbStringConstants.TABLE_PLACES);
        dropTableX(dbWritableConnection,DbStringConstants.TABLE_IMAGES);
        dropTableX(dbWritableConnection,DbStringConstants.TABLE_LANDMARKS);
        dropTableX(dbWritableConnection,DbStringConstants.TABLE_RESTAURANTS);
        dropTableX(dbWritableConnection,DbStringConstants.TABLE_SHOPPING_PLACES);
        dropTableX(dbWritableConnection,DbStringConstants.TABLE_WORK_HOURS);
        dropTableX(dbWritableConnection,DbStringConstants.TABLE_PRICE_CATEGORIES);
        Log.i("DbBaseOperations","Old version db "+ oldVersion +"is droped !");
        Log.i("DbBaseOperations","New version db "+ newVersion +"is to be generated !");
    }

    public static String getPlaceTypeById(Integer placeId){
        if(placeId > 0 && placeId <= 5){
            return DbStringConstants.TABLE_RESTAURANTS;
        }else if(placeId > 5 && placeId <= 10){
            return DbStringConstants.TABLE_SHOPPING_PLACES;
        }else if(placeId > 10 && placeId <= 15){
            return DbStringConstants.TABLE_HOTELS;
        }else if(placeId > 15 && placeId <= 20){
            return DbStringConstants.TABLE_LANDMARKS;
        }else{
            return null;
        }
    }
}
