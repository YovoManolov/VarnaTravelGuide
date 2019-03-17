package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelListAdapter;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
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

public class PlaceDaoImpl implements PlaceDao {

    //private SQLiteDatabase dbWritableConnection;
    private RemoteMongoClient mongoClient;
    private StitchAppClient stitchAppClient;
    private PlaceListAdapter _placeListAdapter;


    public PlaceDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }

  /*  public PlaceDaoImpl() {
        stitchAppClient  = Stitch.getDefaultAppClient();
        this.stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential());
        mongoClient  = stitchAppClient.getServiceClient(
                RemoteMongoClient.factory, "mongodb-atlas");
    }*/


    /*@Override
    public void addPlaces(Place[] places){

        dbWritableConnection.beginTransaction();
        try{
            for(int i = 0 ;i < places.length ;i++){
                ContentValues values = new ContentValues();
                values.put(DbStringConstants.PL_NAME, places[i].getName());
                values.put(DbStringConstants.PL_ADDRESS, places[i].getAddress());
                values.put(DbStringConstants.PL_LATITUDE, places[i].getLatitude());
                values.put(DbStringConstants.PL_LONGITUDE, places[i].getLongitude());
                values.put(DbStringConstants.PL_CONTACTS, places[i].getContacts());
                values.put(DbStringConstants.PL_DESCRIPTION, places[i].getDescription());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_PLACES,
                                                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                    + DbStringConstants.TABLE_PLACES + "for: i = " + i );
                }
                Log.d("Places ", " newly inserted row ID: " + rowId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    public PlaceDaoImpl(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }*/

    /*@Override
    public void createPlacesTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PLACES);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_PLACES_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_PLACES + " is being created !");
    }
    */
    private ArrayList<Place> convertDocsToPlaces(ArrayList<Document> documents) {
        final ArrayList<Place> listOfPlaceObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfPlaceObjects.add(new Place(doc));
        }
        return listOfPlaceObjects;
    }

    @Override
    public Place getPlaceById(ObjectId place_id) {

        RemoteMongoCollection<Document> hotelsCollection =  DatabaseHelper.getVarnaTravelGuideDB()
                .getCollection("places");

        Document filter = new Document();
        RemoteFindIterable cursor = hotelsCollection.find();

        Task <ArrayList<Document>> foundDocuments =
                cursor.into(new ArrayList<Document>());

        ArrayList<Place> placeList = null;
        try {
            foundDocuments.wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(foundDocuments.isComplete()){
            placeList = convertDocsToPlaces(foundDocuments.getResult());
        }

        return  placeList.get(0);
    }

    @Override
    public List<Place> getPlacesByTypeOfPlace(int typeOfPlace) {


        final ArrayList<Document> shoppingPlacesList = new ArrayList<>();

        RemoteMongoCollection placesCollection =  DatabaseHelper.getVarnaTravelGuideDB()
                .getCollection("places");

        Document filter = new Document();
        RemoteFindIterable cursor = placesCollection.find();

        Task <ArrayList<Document>> placesDocuments =
                cursor.into(new ArrayList<Document>());

        ArrayList<Place> resultList = null;
        try {
            placesDocuments.wait(2000);
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
