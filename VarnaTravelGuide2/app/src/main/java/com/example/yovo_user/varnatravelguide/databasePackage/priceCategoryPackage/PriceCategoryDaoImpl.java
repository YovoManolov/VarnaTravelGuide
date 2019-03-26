package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.android.services.mongodb.remote.SyncFindIterable;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PriceCategoryDaoImpl implements PriceCategoryDao {

    private RemoteMongoClient mongoClient;
    private PriceCategoryListAdapter _priceCategoryListAdapter;
    private StitchAppClient stitchAppClient;

/*  public PriceCategoryDaoImpl() {
        stitchAppClient  = Stitch.getDefaultAppClient();
        this.stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential());
        mongoClient  = stitchAppClient.getServiceClient(
                RemoteMongoClient.factory, "mongodb-atlas");
    }*/


    public PriceCategoryDaoImpl() {
        this.mongoClient = DatabaseHelper.getMongoClient();
    }

   /* @Override
    public void createPriceCategoryTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PRICE_CATEGORIES);
        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }
        Log.d("Create table message: ","Table " +
                DbStringConstants.TABLE_PRICE_CATEGORIES + " is being created !");
    }
*/
   /* @Override
    public void addPriceCategory(PriceCategory[] priceCategories) {

        dbWritableConnection.beginTransaction();
        try{
            for(int i = 0 ;i < priceCategories.length ;i++){

                ContentValues values = new ContentValues();
                values.put(DbStringConstants.PC_PRICE_TYPE,
                        priceCategories[i].getPriceType());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_PRICE_CATEGORIES,
                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_PRICE_CATEGORIES + "for: i = " + i );
                }

                Log.d("Price category ", " newly inserted row ID: " + rowId);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }*/

   /* private List<PriceCategory> convertDocsToHotels(final List<Document> documents) {
        final List<PriceCategory> listOfHotelObjects = new ArrayList<>(documents.size());
        for (final Document doc : documents) {
            listOfHotelObjects.add(new PriceCategory(doc));
        }
        return listOfHotelObjects;
    }
*/
   private ArrayList<PriceCategory> convertDocsToPlaces(ArrayList<Document> documents) {
       final ArrayList<PriceCategory> listOfPriceCategoryObjects = new ArrayList<>(documents.size());
       for (final Document doc : documents) {
           listOfPriceCategoryObjects.add(new PriceCategory(doc));
       }
       return listOfPriceCategoryObjects;
   }

    public PriceCategory getPriceCategoryById(int priceCategoryId){
        PriceCategory priceCategory = null;

        RemoteMongoCollection<Document> priceCategoryCollection =  mongoClient
                .getDatabase("VarnaTravelGuide")
                .getCollection("priceCategory");

        Document filter = new Document("priceCategory_id",priceCategoryId);

        RemoteFindIterable cursor = priceCategoryCollection.find(filter);
        Task <ArrayList<Document>> foundDocuments =
                cursor.into(new ArrayList<Document>());

        while(foundDocuments.isComplete() == false) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Place> priceCategoryList = convertDocsToPlaces(foundDocuments.getResult());

        return priceCategoryList.get(0);
    }
}
