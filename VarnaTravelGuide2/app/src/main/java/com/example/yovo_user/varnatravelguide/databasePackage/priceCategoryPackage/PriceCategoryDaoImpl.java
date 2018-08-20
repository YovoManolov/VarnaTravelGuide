package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

public class PriceCategoryDaoImpl implements PriceCategoryDao {

    @Override
    public void createPriceCategoryTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PRICE_CATEGORIES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
    }

    @Override
    public void addPriceCategory(SQLiteDatabase dbWritableConnection, PriceCategory[] priceCategories) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < priceCategories.length ;i++){
            values.put(DbStringConstants.PC_PRICE_TYPE,
                    priceCategories[i].getPriceType());
            dbWritableConnection.insert(DbStringConstants.TABLE_PRICE_CATEGORIES,
                    null, values);
        }

        dbWritableConnection.endTransaction();
    }
}
