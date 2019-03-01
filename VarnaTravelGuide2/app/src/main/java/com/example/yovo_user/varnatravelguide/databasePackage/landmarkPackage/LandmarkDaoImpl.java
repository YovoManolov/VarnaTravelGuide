package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceListAdapter;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
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

public class LandmarkDaoImpl implements LandmarkDao {

    private RemoteMongoClient mongoClient;
    private LandmarkListAdapter _landmarkListAdapter;

    public LandmarkDaoImpl(RemoteMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


   /* @Override
    public void createLandmarkTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,
                                        DbStringConstants.TABLE_LANDMARKS);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_LANDMARKS_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_LANDMARKS + " is being created !");

    }

    @Override
    public void addLandmarks(Landmark[] landmarks) {
        dbWritableConnection.beginTransaction();
        try {
            for (int i = 0; i < landmarks.length; i++) {
                ContentValues values = new ContentValues();
                values.put(DbStringConstants.L_PLACE_ID, landmarks[i].getPlaceId());
                values.put(DbStringConstants.L_ENTRANCE_TICKET,
                        landmarks[i].getEntranceTicket());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_LANDMARKS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_LANDMARKS + "for: i = " + i );
                }

                Log.d("Landmarks ", " newly inserted row ID: " + rowId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }*/

    @Override
    public List<Landmark> getAllLandmarks() throws SQLException {
        List<Landmark> allLandmarks = new ArrayList<Landmark>();

        final ArrayList<Document> landmarkDocumentsList = new ArrayList<>();

        RemoteMongoCollection<Document> landmarksCollection =  mongoClient
                .getDatabase("VarnaTravelGuide")
                .getCollection("landmarks");

        Document filter = new Document();
        SyncFindIterable cursor = landmarksCollection.sync().find(filter);

        cursor.into(landmarkDocumentsList).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                _landmarkListAdapter.clear();
                _landmarkListAdapter.addAll(convertDocsToLandmarks(landmarkDocumentsList));
                _landmarkListAdapter.notifyDataSetChanged();
            }
        });

        allLandmarks = convertDocsToLandmarks(landmarkDocumentsList);

        return allLandmarks;
    }


    private List<Landmark> convertDocsToLandmarks (final List<Document> documents) {
        final List<Landmark> listOfRestaurantObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfRestaurantObjects.add(new Landmark(doc));
        }
        return listOfRestaurantObjects;
    }

    @Override
    public Landmark getLandmarkByPlaceId(ObjectId place_id) {

        RemoteMongoCollection<Document> restaurantCollection = mongoClient
                .getDatabase("VarnaTravelGuide")
                .getCollection("restaurants");

        Document filter = new Document("_id", place_id);
        SyncFindIterable cursor = restaurantCollection.sync().find(filter);
        final ArrayList<Document> foundDocuments = new ArrayList<>();

        cursor.into(foundDocuments).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                _landmarkListAdapter.clear();
                _landmarkListAdapter.addAll(convertDocsToLandmarks(foundDocuments));
                _landmarkListAdapter.notifyDataSetChanged();
            }
        });

        ArrayList<Landmark> resultList =
                (ArrayList<Landmark>) convertDocsToLandmarks(foundDocuments);
        return resultList.get(0);
    }
}
