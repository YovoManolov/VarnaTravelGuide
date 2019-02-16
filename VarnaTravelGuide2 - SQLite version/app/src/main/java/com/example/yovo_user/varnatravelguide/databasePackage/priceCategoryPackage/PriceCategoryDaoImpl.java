package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

import java.util.ArrayList;
import java.util.List;

public class PriceCategoryDaoImpl implements PriceCategoryDao {

    private SQLiteDatabase dbWritableConnection;

    public PriceCategoryDaoImpl(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }

    @Override
    public void createPriceCategoryTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PRICE_CATEGORIES);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_PRICE_CATEGORIES + " is being created !");
    }

    @Override
    public void addPriceCategory(PriceCategory[] priceCategories) {

        dbWritableConnection.beginTransaction();
        try{
            for(int i = 0 ;i < priceCategories.length ;i++){

                ContentValues values = new ContentValues();
                values.put(DbStringConstants.PC_PRICE_TYPE,
                        priceCategories[i].getPriceType());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_PRICE_CATEGORIES,
                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_PRICE_CATEGORIES + "for: i = " + i );
                }

                Log.d("Price category ", " newly inserted row ID: " + rowId);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }


    public PriceCategory getPriceCategoryById(int placeCategoryId){
        PriceCategory priceCategory = null;

        Cursor cursor = dbWritableConnection.rawQuery(DbStringConstants.GET_PRICE_CATEGORIES_BY_ID,
                new String[]{String.valueOf(placeCategoryId)  });

        if (cursor.moveToFirst()) {
            do {
                priceCategory = new PriceCategory(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return priceCategory;
    }
}
