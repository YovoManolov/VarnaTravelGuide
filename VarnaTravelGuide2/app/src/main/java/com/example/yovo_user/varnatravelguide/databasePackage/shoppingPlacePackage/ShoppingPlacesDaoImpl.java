package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPlacesDaoImpl implements ShoppingPlaceDao {

    @Override
    public void createShoppingPlacesTable(SQLiteDatabase dbWritableConnection)throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_SHOPPING_PLACES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_SHOPPING_PLACES_TABLE);
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_SHOPPING_PLACES + " is being created !");
    }

    @Override
    public void addShoppingPlaces(SQLiteDatabase dbWritableConnection,
                                        ShoppingPlace[] shoppingPlaces) {
        dbWritableConnection.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for (int i = 0; i < shoppingPlaces.length; i++) {
                values.put(DbStringConstants.SP_PLACE_ID, shoppingPlaces[i].getPlaceId());
                values.put(DbStringConstants.SP_PRICE_CATEGORY_ID,
                        shoppingPlaces[i].getPriceCategoryId());

                long rowId =  dbWritableConnection.insert(DbStringConstants.TABLE_SHOPPING_PLACES,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_SHOPPING_PLACES + "for: i = " + i );
                }

                Log.d("Shopping places  ", " newly inserted row ID: " + rowId);

                values = new ContentValues();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<ShoppingPlace> getAllShoppingPlaces(SQLiteDatabase dbWritableConnection) {

        List<ShoppingPlace> allShoppingPlaces = new ArrayList<ShoppingPlace>();

        dbWritableConnection.beginTransaction();
        try{
            Cursor cursor = dbWritableConnection.
                    rawQuery(DbStringConstants.GET_ALL_SHOPPING_PLACES,null );

            if (cursor.moveToFirst()) {
                do {
                    ShoppingPlace shoppingPlace = new ShoppingPlace(
                            cursor.getInt(1),
                            cursor.getInt(2)
                    );
                    allShoppingPlaces.add(shoppingPlace);
                } while (cursor.moveToNext());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return allShoppingPlaces;
    }

    @Override
    public ShoppingPlace getShoppingPlaceByPlaceId
            (SQLiteDatabase dbWritableConnection,Integer placeId){

        dbWritableConnection.beginTransaction();
        try{
            Cursor cursor = dbWritableConnection.
                    rawQuery(DbStringConstants.GET_ALL_RESTAURANTS,null);

            if (cursor.moveToFirst()) {
                do {
                    ShoppingPlace shoppingPlace =  new ShoppingPlace(
                            cursor.getInt(1),
                            cursor.getInt(2)
                    );
                    if(shoppingPlace.getPlaceId() == placeId){
                        return shoppingPlace;
                    }
                } while (cursor.moveToNext());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return null;
    }
}
