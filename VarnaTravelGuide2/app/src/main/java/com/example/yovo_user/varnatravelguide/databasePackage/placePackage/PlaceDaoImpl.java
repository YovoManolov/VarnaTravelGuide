package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.SyncFindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class PlaceDaoImpl implements PlaceDao {

    //private SQLiteDatabase dbWritableConnection;
    private RemoteMongoClient mongoClient;

    public PlaceDaoImpl(RemoteMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    private PlaceListAdapter _placeListAdapter;

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
    private List<Place> convertDocsToPlaces(final List<Document> documents) {
        final List<Place> listOfPlaceObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfPlaceObjects.add(new Place(doc));
        }
        return listOfPlaceObjects;
    }

    @Override
    public Place getPlaceById(ObjectId place_id) {
        Place place ;

        RemoteMongoCollection<Document> hotelsCollection =  mongoClient
                .getDatabase("VarnaTravelGuide")
                .getCollection("places");

        Document filter = new Document("_id",place_id);
        SyncFindIterable cursor = hotelsCollection.sync().find(filter);
        final ArrayList<Document> foundDocuments = new ArrayList<>();

        cursor.into(foundDocuments).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                _placeListAdapter.clear();
                _placeListAdapter.addAll(convertDocsToPlaces(foundDocuments));
                _placeListAdapter.notifyDataSetChanged();
            }
        });

        ArrayList<Place> resultList = (ArrayList<Place>)convertDocsToPlaces(foundDocuments);
        return  resultList.get(0);
    }
}
