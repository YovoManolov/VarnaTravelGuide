package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.google.android.gms.tasks.Task;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

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
                mongoClient.getDatabase("varnaTravelGuideDB")
                .getCollection("restaurants");

        RemoteFindIterable cursor = hotelsCollection.find();
        Task <ArrayList<Document>> restaurantDocumentsList =
                                cursor.into(new ArrayList<Document>());

        while(restaurantDocumentsList.isComplete() == false) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Restaurant> allRestaurants =
                convertDocsToResaturants(restaurantDocumentsList.getResult());

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

        RemoteMongoCollection<Document> restaurantCollection =
                mongoClient.getDatabase("varnaTravelGuideDB")
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
