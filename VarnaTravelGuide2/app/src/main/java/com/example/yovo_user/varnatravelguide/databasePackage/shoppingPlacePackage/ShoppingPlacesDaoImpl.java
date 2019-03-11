package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceListAdapter;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.SyncFindIterable;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPlacesDaoImpl implements ShoppingPlaceDao {

    private RemoteMongoClient mongoClient;
    private ShoppingPlacesListAdapter _shoppingPlacesListAdapter;
    private PlaceListAdapter _placesListAdapter;
    private StitchAppClient stitchAppClient;

    public ShoppingPlacesDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }

/*
    public ShoppingPlacesDaoImpl() {
        stitchAppClient  = Stitch.getDefaultAppClient();
        this.stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential());
        mongoClient  = stitchAppClient.getServiceClient(
                RemoteMongoClient.factory, "mongodb-atlas");
    }
*/

    /*@Override
    public void createShoppingPlacesTable()throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_SHOPPING_PLACES);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_SHOPPING_PLACES_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_SHOPPING_PLACES + " is being created !");
    }*/
/*
    @Override
    public void addShoppingPlaces(ShoppingPlace[] shoppingPlaces) {
        dbWritableConnection.beginTransaction();
        try{
            for (int i = 0; i < shoppingPlaces.length; i++) {
                ContentValues values = new ContentValues();
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
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }*/

   /*private List<Place> convertDocsToShoppingPlaces(final List<Document> documents) {
        final List<Place> listOfShoppingPlaceObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfShoppingPlaceObjects.add(new Place(doc));
        }
        return listOfShoppingPlaceObjects;
    }

*/


   /* @Override
    public ShoppingPlace getShoppingPlaceByPlaceId(Integer placeId){

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

            cursor.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return null;
    }*/
}
