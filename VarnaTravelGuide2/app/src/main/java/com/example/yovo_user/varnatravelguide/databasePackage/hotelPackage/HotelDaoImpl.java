package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoDatabase;
import com.mongodb.stitch.android.services.mongodb.remote.SyncFindIterable;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HotelDaoImpl implements HotelDao{

    private RemoteMongoClient mongoClient;

    public HotelDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }

    private final List<Hotel> convertDocsToHotels(List<Document> documents) {
        List<Hotel> listOfHotelObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfHotelObjects.add(new Hotel(doc));
        }
        return listOfHotelObjects;
    }

    @Override
    public final List<Hotel> getAllHotels() {

        RemoteMongoCollection<Document> hotelsCollection =
                DatabaseHelper.getVarnaTravelGuideDB()
                        .getCollection("hotels");

        RemoteFindIterable cursor = hotelsCollection.find();

        Task <ArrayList<Document>> hotelDocuments = cursor.into(new ArrayList<Document>());

        while(hotelDocuments.isComplete() == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Hotel> hotelsList = convertDocsToHotels(hotelDocuments.getResult());

        return hotelsList;
    }

    @Override
    public Hotel getHotelByPlaceId(Object placeId) {

        RemoteMongoCollection<Document> hotelsCollection =
                DatabaseHelper.getVarnaTravelGuideDB()
                .getCollection("hotels");

        Document filter = new Document("_id",placeId);
        RemoteFindIterable cursor = hotelsCollection.find(filter);

        Task <ArrayList<Document>> foundDocuments = cursor.into(new ArrayList<Document>());
        List<Hotel> resultList = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(foundDocuments.isComplete()){
            resultList = convertDocsToHotels(foundDocuments.getResult());
        }
        return  resultList.get(0);
    }
}
