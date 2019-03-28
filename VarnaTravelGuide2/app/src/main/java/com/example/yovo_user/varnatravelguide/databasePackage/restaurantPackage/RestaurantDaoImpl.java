package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelListAdapter;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.SyncFindIterable;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao {

    private RemoteMongoClient mongoClient;

    public RestaurantDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }

    @Override
    public List<Restaurant> getAllResaturants(){

        RemoteMongoCollection<Document> hotelsCollection =
                DatabaseHelper.getVarnaTravelGuideDB()
                .getCollection("restaurants");

        RemoteFindIterable cursor = hotelsCollection.find();
        Task <ArrayList<Document>> restaurantDocumentsList =
                                cursor.into(new ArrayList<Document>());

        List<Restaurant>  allRestaurants = null;
        try {
            restaurantDocumentsList.wait(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(restaurantDocumentsList.isComplete()){
            allRestaurants = convertDocsToResaturants(restaurantDocumentsList.getResult());
        }

        return allRestaurants;
    }

    private ArrayList<Restaurant> convertDocsToResaturants(ArrayList<Document> documents) {
        final ArrayList<Restaurant> listOfHotelObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfHotelObjects.add(new Restaurant(doc));
        }
        return listOfHotelObjects;
    }

    @Override
    public Restaurant getRestaurantByPlaceId(ObjectId place_id){

        RemoteMongoCollection<Document> restaurantCollection =  mongoClient
                .getDatabase("varnaTravelGuideDB")
                .getCollection("restaurants");

        Document filter = new Document("place_id",place_id);

        RemoteFindIterable cursor = restaurantCollection.find(filter);
        final ArrayList<Document> foundDocuments = new ArrayList<>();

        Task <ArrayList<Document>> restaurantDocuments = cursor.into(new ArrayList<Document>());

        ArrayList<Restaurant> resultList = null;
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(restaurantDocuments.isComplete()){
            resultList = convertDocsToResaturants(restaurantDocuments.getResult());
        }
        return  resultList.get(0);
    }
}
