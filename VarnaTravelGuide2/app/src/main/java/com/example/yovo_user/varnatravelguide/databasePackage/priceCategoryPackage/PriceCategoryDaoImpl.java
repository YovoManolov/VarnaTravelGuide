package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

import java.util.ArrayList;
import java.util.List;

public class PriceCategoryDaoImpl implements PriceCategoryDao {

    @Override
    public void createPriceCategoryTable(SQLiteDatabase dbWritableConnection) throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PRICE_CATEGORIES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_PRICE_CATEGORIES + " is being created !");
    }

    @Override
    public void addPriceCategory(SQLiteDatabase dbWritableConnection, PriceCategory[] priceCategories) {

        dbWritableConnection.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for(int i = 0 ;i < priceCategories.length ;i++){
                values.put(DbStringConstants.PC_PRICE_TYPE,
                        priceCategories[i].getPriceType());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_PRICE_CATEGORIES,
                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_PRICE_CATEGORIES + "for: i = " + i );
                }

                Log.d("Price category ", " newly inserted row ID: " + rowId);
                values = new ContentValues();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }


    public PriceCategory getPriceCategoryById(SQLiteDatabase dbReadableConnection,
                                                int placeCategoryId){
        PriceCategory priceCategory = null;

        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_PRICE_CATEGORIES_BY_ID,
                new String[]{String.valueOf(placeCategoryId)  });

        if (cursor.moveToFirst()) {
            do {
                priceCategory = new PriceCategory(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return priceCategory;
    }
}
