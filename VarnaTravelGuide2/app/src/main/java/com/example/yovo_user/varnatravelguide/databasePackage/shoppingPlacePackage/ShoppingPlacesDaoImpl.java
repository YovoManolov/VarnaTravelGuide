package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

public class ShoppingPlacesDaoImpl implements ShoppingPlaceDao {
    @Override
    public void createShoppingPlacesTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_SHOPPING_PLACES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_SHOPPING_PLACES_TABLE);
    }

    @Override
    public void addShoppingPlaces(SQLiteDatabase dbWritableConnection,
                                        ShoppingPlace[] shoppingPlaces) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < shoppingPlaces.length ;i++){
            values.put(DbStringConstants.SP_PLACE_ID,shoppingPlaces[i].getPlaceId());
            values.put(DbStringConstants.SP_PRICE_CATEGORY_ID,
                    shoppingPlaces[i].getPriceCategoryId());

            dbWritableConnection.insert(DbStringConstants.TABLE_SHOPPING_PLACES,
                    null, values);
        }
        dbWritableConnection.endTransaction();
    }
}
