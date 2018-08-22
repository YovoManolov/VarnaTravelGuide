package com.example.yovo_user.varnatravelguide.databasePackage;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbBaseOperations {

    public static void dropTableX(SQLiteDatabase dbWritableConnection,String tableName){
        //TODO:P3 NPE tablename
        try{
            dbWritableConnection.beginTransaction();
            dbWritableConnection.execSQL(DbStringConstants.DROP_TABLE_X + tableName);
            dbWritableConnection.endTransaction();
        }catch(SQLException e){
            e.printStackTrace();
            Log.e("VTGDatabase","error dropping table " + tableName);
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
}
