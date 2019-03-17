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
    private StitchAppClient stitchAppClient;

    public HotelDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }

    HotelListAdapter _hotelListAdapter; /*= new HotelListAdapter(
            this,
            R.layout.todo_item,
            new ArrayList<TodoItem>(),
            getItemsCollection());
        ((ListView) findViewById(R.id.todoList)).setAdapter(_itemAdapter);*/

/*    public HotelDaoImpl() {
        stitchAppClient  = Stitch.getDefaultAppClient();
        this.stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential());
        mongoClient  = stitchAppClient.getServiceClient(
                RemoteMongoClient.factory, "mongodb-atlas");
    }*/


    /*@Override
    public void createHotelTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection, DbStringConstants.TABLE_HOTELS);

        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_HOTELS_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_HOTELS + " is being created !");

    }*/

   /* @Override
    public void addHotels(Hotel[] hotels) throws SQLException {

        dbWritableConnection.beginTransaction();
        try{

            for (int i = 0; i < hotels.length; i++) {
                ContentValues values = new ContentValues();
                values.put(DbStringConstants.H_PLACE_ID, hotels[i].getPlaceId());
                values.put(DbStringConstants.H_NUMB_OF_STARS, hotels[i].getNumbOfStars());
                values.put(DbStringConstants.H_PRICE_CATEGORY_ID,
                        hotels[i].getPriceCategoryId());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_HOTELS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_HOTELS + "for: i = " + i );
                }

                Log.d("Hotels ", " newly inserted row ID: " + rowId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }*/

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

        hotelDocuments.addOnCompleteListener(new OnCompleteListener<ArrayList<Document>>() {

            @Override
            public void onComplete(@NonNull Task<ArrayList<Document>> task) {

                hotelsList = convertDocsToHotels(task.getResult());
                return hotelsList;
            }
        });


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
            foundDocuments.wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(foundDocuments.isComplete()){
            resultList = convertDocsToHotels(foundDocuments.getResult());
        }
        return  resultList.get(0);
    }
}
