package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelListAdapter;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.SyncFindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    private RemoteMongoClient mongoClient;
    private RestaurantListAdapter _restaurantListAdapter;

    public RestaurantDaoImpl(RemoteMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    /*@Override
    public void createRestaurantTable() throws SQLException  {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_RESTAURANTS);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_RESTAURANTS_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_RESTAURANTS + " is being created !");
    }*/

   /* @Override
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
    }*/

    @Override
    public List<Restaurant> getAllResaturants(){
        List<Restaurant>  allRestaurants = new ArrayList<Restaurant>();
        final ArrayList<Document> restaurantDocumentsList = new ArrayList<>();

        RemoteMongoCollection<Document> hotelsCollection =  mongoClient
                .getDatabase("VarnaTravelGuide")
                .getCollection("restaurants");

        Document filter = new Document();
        SyncFindIterable cursor = hotelsCollection.sync().find(filter);

        cursor.into(restaurantDocumentsList).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                _restaurantListAdapter.clear();
                _restaurantListAdapter.addAll(convertDocsToResaturants(restaurantDocumentsList));
                _restaurantListAdapter.notifyDataSetChanged();
            }
        });

        allRestaurants = convertDocsToResaturants(restaurantDocumentsList);

        return allRestaurants;
    }

    private List<Restaurant> convertDocsToResaturants(final List<Document> documents) {
        final List<Restaurant> listOfHotelObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfHotelObjects.add(new Restaurant(doc));
        }
        return listOfHotelObjects;
    }

    @Override
    public Restaurant getRestaurantByPlaceId(ObjectId place_id){

        RemoteMongoCollection<Document> restaurantCollection =  mongoClient
                .getDatabase("VarnaTravelGuide")
                .getCollection("restaurants");

        Document filter = new Document("_id",place_id);
        SyncFindIterable cursor = restaurantCollection.sync().find(filter);
        final ArrayList<Document> foundDocuments = new ArrayList<>();

        cursor.into(foundDocuments).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                _restaurantListAdapter.clear();
                _restaurantListAdapter.addAll(convertDocsToResaturants(foundDocuments));
                _restaurantListAdapter.notifyDataSetChanged();
            }
        });

        ArrayList<Restaurant> resultList =
                (ArrayList<Restaurant>)convertDocsToResaturants(foundDocuments);
        return  resultList.get(0);
    }
}
