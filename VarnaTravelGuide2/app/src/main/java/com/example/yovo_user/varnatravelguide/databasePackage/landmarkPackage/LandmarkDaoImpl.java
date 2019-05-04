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
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.client.FindIterable;
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

public class LandmarkDaoImpl implements LandmarkDao {

    @Override
    public List<Landmark> getAllLandmarks() {

        RemoteMongoCollection<Document> landmarksCollection =
                DatabaseHelper.getVarnaTravelGuideDB()
                .getCollection("landmarks");

        RemoteFindIterable cursor = landmarksCollection.find();

        Task <ArrayList<Document>> landmarkDocumentsList
                = cursor.into(new ArrayList<Document>());

        while(landmarkDocumentsList.isComplete() == false) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Landmark> allLandmarks  = convertDocsToLandmarks(landmarkDocumentsList.getResult());

        return allLandmarks;
    }


    private ArrayList<Landmark> convertDocsToLandmarks (ArrayList<Document> documents) {
        final ArrayList<Landmark> listOfRestaurantObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfRestaurantObjects.add(new Landmark(doc));
        }
        return listOfRestaurantObjects;
    }

    @Override
    public Landmark getLandmarkByPlaceId(ObjectId place_id) {

        RemoteMongoCollection<Document> landmarkCollection =
                DatabaseHelper.getVarnaTravelGuideDB()
                .getCollection("landmarks");

        Document filter = new Document("place_id", place_id);
        RemoteFindIterable cursor = landmarkCollection.find(filter);

        Task <ArrayList<Document>> foundDocuments = cursor.into(new ArrayList<Document>());

        ArrayList<Landmark> resultList = null;
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(foundDocuments.isComplete()){
            resultList = convertDocsToLandmarks(foundDocuments.getResult());
        }
        return resultList.get(0);
    }
}
