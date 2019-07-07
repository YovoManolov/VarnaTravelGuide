package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class PlaceDaoImpl implements PlaceDao {

    private RemoteMongoClient mongoClient;
    private StitchAppClient stitchAppClient;
    private PlaceListAdapter _placeListAdapter;


    public PlaceDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }


    private ArrayList<Place> convertDocsToPlaces(ArrayList<Document> documents) {
        final ArrayList<Place> listOfPlaceObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfPlaceObjects.add(new Place(doc));
        }
        return listOfPlaceObjects;
    }

    @Override
    public Place getPlaceById(ObjectId place_id) {

        RemoteMongoCollection<Document> placesCollection =  mongoClient
                .getDatabase("varnaTravelGuideDB").getCollection("places");

        Document filter = new Document("_id", place_id);
        RemoteFindIterable cursor = placesCollection.find(filter);

        Task <ArrayList<Document>> foundDocuments =
                cursor.into(new ArrayList<Document>());

        while(foundDocuments.isComplete() == false) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Place> placeList = convertDocsToPlaces(foundDocuments.getResult());

        return placeList.get(0);
    }

    @Override
    public List<Place> getPlacesByTypeOfPlace(int typeOfPlace) {


        final ArrayList<Document> shoppingPlacesList = new ArrayList<>();

        RemoteMongoCollection placesCollection =  mongoClient
                .getDatabase("varnaTravelGuideDB")
                .getCollection("places");

        Document filter = new Document();
        RemoteFindIterable cursor = placesCollection.find();

        Task <ArrayList<Document>> placesDocuments =
                cursor.into(new ArrayList<Document>());

        ArrayList<Place> resultList = null;
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(placesDocuments.isComplete()){
            resultList = convertDocsToPlaces(placesDocuments.getResult());
        }
        return resultList;
    }


    public Image getMainImageForPlace(ArrayList<Image> images ){
        boolean found = false;
        for(Image i: images){
            if(i.getIsMainImage() == 1){
                found = true;
                return i;
            }
        }
        Log.i("Error images: ","No main image found in the array of Images !");
        return null;
    }
}
