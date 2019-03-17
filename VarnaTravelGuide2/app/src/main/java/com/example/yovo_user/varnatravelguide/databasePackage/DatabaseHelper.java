package com.example.yovo_user.varnatravelguide.databasePackage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.ImageDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.LandmarkDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategoryDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.RestaurantDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlacesDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHours;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHoursDaoImpl;
import com.google.android.gms.tasks.Task;
import com.mongodb.client.MongoClient;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchAuth;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoDatabase;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private StitchAppClient stitchAppClient;
    public static RemoteMongoClient mongoClient;
   public static RemoteMongoDatabase varnaTravelGuideDB;

    private PlaceDaoImpl placeDaoImpl;
    private HotelDaoImpl hotelDaoImpl;
    private LandmarkDaoImpl landmarkDaoImpl;
    private RestaurantDaoImpl restaurantDaoImpl;
    private ImageDaoImpl imageDaoImpl;
    private WorkHoursDaoImpl workHoursDaoImpl;
    private PriceCategoryDaoImpl priceCategoryDaoImpl;
    private ShoppingPlacesDaoImpl shoppingPlacesDaoImpl;

    public DatabaseHelper(){

        stitchAppClient  = Stitch.getDefaultAppClient();
        this.stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential());

        mongoClient  = this.stitchAppClient.getServiceClient(RemoteMongoClient.factory,
                "mongodb-atlas");
                //stitchAppClient.getServiceClienst(
                //RemoteMongoClient.factory, "mongodb-atlas");

        varnaTravelGuideDB = mongoClient.getDatabase("varnaTravelGuideDB");

        placeDaoImpl = new PlaceDaoImpl();
        landmarkDaoImpl = new LandmarkDaoImpl();
        restaurantDaoImpl = new RestaurantDaoImpl();
        //imageDaoImpl = new ImageDaoImpl();
        workHoursDaoImpl = new WorkHoursDaoImpl();
        priceCategoryDaoImpl = new PriceCategoryDaoImpl();
        shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl();
        hotelDaoImpl = new HotelDaoImpl();

    }

    public static RemoteMongoDatabase getVarnaTravelGuideDB() {
        return varnaTravelGuideDB;
    }

    public static void setVarnaTravelGuideDB(RemoteMongoDatabase varnaTravelGuideDB) {
        DatabaseHelper.varnaTravelGuideDB = varnaTravelGuideDB;
    }

    public static RemoteMongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(RemoteMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public HotelDaoImpl getHotelDaoImpl() {
        return hotelDaoImpl;
    }

    public PlaceDaoImpl getPlaceDaoImpl() {
        return placeDaoImpl;
    }

    public LandmarkDaoImpl getLandmarkDaoImpl() {
        return landmarkDaoImpl;
    }

    public ImageDaoImpl getImageDaoImpl() {
        return imageDaoImpl;
    }

    public RestaurantDaoImpl getRestaurantDaoImpl() {
        return restaurantDaoImpl;
    }

    public WorkHoursDaoImpl getWorkHoursDaoImpl() {
        return workHoursDaoImpl;
    }

    public PriceCategoryDaoImpl getPriceCategoryDaoImpl() {
        return priceCategoryDaoImpl;
    }

    public ShoppingPlacesDaoImpl getShoppingPlacesDaoImpl() {
        return shoppingPlacesDaoImpl;
    }
/*
   used when no anonymous authentication is used.

    private static class MyAuthListener implements StitchAuthListener {

        private WeakReference<MainActivity> _main;
        private StitchUser _user;

        public MyAuthListener(final MainActivity activity) {
            _main = new WeakReference<>(activity);
        }

        @Override
        public void onAuthEvent(final StitchAuth auth) {
            if (auth.isLoggedIn() && _user == null) {
                Log.d(TAG, "Logged into Stitch");
                _user = auth.getUser();
                return;
            }

            if (!auth.isLoggedIn() && _user != null) {
                _user = null;
                onLogout();
            }
        }

        public void onLogout() {
            final MainActivity activity = _main.get();

            final List<Task<Void>> futures = new ArrayList<Task<Void>>();
            if (activity != null) {
                activity._handler.removeCallbacks(activity._refresher);

                if (activity._googleApiClient != null) {
                    final TaskCompletionSource<Void> future = new TaskCompletionSource<>();
                    GoogleSignInApi.signOut(
                            activity._googleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull final Status ignored) {
                            future.setResult(null);
                        }
                    });
                    futures.add(future.getTask());
                }

                if (activity._fbInitOnce) {
                    LoginManager.getInstance().logOut();
                }

                Tasks.whenAll(futures).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull final Task<Void> ignored) {
                        activity.setupLogin();
                    }
                });
            }
        }
    }

    private static class ListRefresher implements Runnable {

        private WeakReference<MainActivity> _main;

        private ListRefresher(final MainActivity activity) {
            _main = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            final MainActivity activity = _main.get();
            if (activity != null && activity._client.getAuth().isLoggedIn()) {
                activity.refreshList();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            final GoogleSignInResult result = GoogleSignInApi.getSignInResultFromIntent(data);
            handleGooglSignInResult(result);
            return;
        }

        if (_callbackManager != null) {
            _callbackManager.onActivityResult(requestCode, resultCode, data);
            return;
        }
        Log.e(TAG, "Nowhere to send activity result for ourselves");
    }

    private void handleGooglSignInResult(final GoogleSignInResult result) {
        if (result == null) {
            Log.e(TAG, "Got a null GoogleSignInResult");
            return;
        }

        Log.d(TAG, "handleGooglSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            final GoogleCredential googleCredential = new GoogleCredential(result.getSignInAccount().getServerAuthCode());
            _client.getAuth().loginWithCredential(googleCredential).addOnCompleteListener(new OnCompleteListener<StitchUser>() {
                @Override
                public void onComplete(@NonNull final Task<StitchUser> task) {
                    if (task.isSuccessful()) {
                        initTodoView();
                    } else {
                        Log.e(TAG, "Error logging in with Google", task.getException());
                    }
                }
            });
        }
    }
*/



//
//    public void createPlacesTable(SQLiteDatabase dbWritableConnection) throws SQLException {
//        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PLACES);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_PLACES_TABLE);
//        Log.d("Create table message: ","Table " +
//                DbStringConstants.TABLE_PLACES + " is being created !");
//    }
//
//    public void createPriceCategoryTable(SQLiteDatabase dbWritableConnection) throws SQLException {
//        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_PRICE_CATEGORIES);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
//        Log.d("Create table message: ","Table " +
//                DbStringConstants.TABLE_PRICE_CATEGORIES + " is being created !");
//    }
//
//    public void createImageTable(SQLiteDatabase dbWritableConnection) throws SQLException {
//        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_IMAGES);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_IMAGES_TABLE);
//        Log.d("Create table message: ","Table "
//                + DbStringConstants.TABLE_IMAGES + " is being created !");
//    }
//
//    public void createHotelTable(SQLiteDatabase db) throws SQLException {
//        DbBaseOperations.dropTableX(db, DbStringConstants.TABLE_HOTELS);
//        db.execSQL(DbStringConstants.CREATE_HOTELS_TABLE);
//        Log.d("Create table message: ","Table "
//                + DbStringConstants.TABLE_HOTELS + " is being created !");
//
//    }
//
//    public void createLandmarkTable(SQLiteDatabase dbWritableConnection) throws SQLException {
//        DbBaseOperations.dropTableX(dbWritableConnection,
//                DbStringConstants.TABLE_LANDMARKS);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_LANDMARKS_TABLE);
//        Log.d("Create table message: ","Table "
//                + DbStringConstants.TABLE_LANDMARKS + " is being created !");
//
//    }
//
//    public void createRestaurantTable(SQLiteDatabase dbWritableConnection) throws SQLException  {
//        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_RESTAURANTS);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_RESTAURANTS_TABLE);
//        Log.d("Create table message: ","Table " +
//                DbStringConstants.TABLE_RESTAURANTS + " is being created !");
//    }
//
//    public void createShoppingPlacesTable(SQLiteDatabase dbWritableConnection)throws SQLException {
//        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_SHOPPING_PLACES);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_SHOPPING_PLACES_TABLE);
//        Log.d("Create table message: ","Table " +
//                DbStringConstants.TABLE_SHOPPING_PLACES + " is being created !");
//    }
//
//    public void createWorkHoursTable(SQLiteDatabase dbWritableConnection) throws SQLException {
//        DbBaseOperations.dropTableX(dbWritableConnection, DbStringConstants.TABLE_WORK_HOURS);
//        dbWritableConnection.execSQL(DbStringConstants.CREATE_WORK_HOURS_TABLE);
//        Log.d("Create table message: ","Table "
//                + DbStringConstants.TABLE_WORK_HOURS + " is being created !");
//
//    }


/*    public void addPlaces(SQLiteDatabase dbWritableConnection,Place[] places){

        dbWritableConnection.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for(int i = 0 ;i < places.length ;i++){

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
                values.clear();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    public void addPriceCategory(SQLiteDatabase dbWritableConnection, PriceCategory[] priceCategories) {

        dbWritableConnection.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for(int i = 0 ;i < priceCategories.length ;i++){
                values.put(DbStringConstants.PC_PRICE_TYPE,
                        priceCategories[i].getPriceType());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_PRICE_CATEGORIES,
                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_PRICE_CATEGORIES + "for: i = " + i );
                }

                Log.d("Price category ", " newly inserted row ID: " + rowId);
                values = new ContentValues();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }


    public void addImage(SQLiteDatabase dbWritableConnection, Image[] images) {

        dbWritableConnection.beginTransaction();
        try {
            int i;
            ContentValues values = new ContentValues();
            for (i = 0; i < images.length; i++) {
                values.put(DbStringConstants.IM_PLACE_ID, images[i].getPlaceId());
                values.put(DbStringConstants.IM_IMAGE_URL, images[i].getImageURL());
                values.put(DbStringConstants.IM_MAIN_IMAGE, images[i].getIsMainImage());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_IMAGES,
                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_IMAGES + "for: i = " + i );
                }

                Log.d("Images ", " newly inserted row ID: " + rowId);

                values.clear();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    public void addHotels(SQLiteDatabase dbWritableConnection, Hotel[] hotels) throws SQLException {

        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (int i = 0; i < hotels.length; i++) {
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

                values.clear();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    public void addLandmarks(SQLiteDatabase dbWritableConnection, Landmark[] landmarks) {
        dbWritableConnection.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            for (int i = 0; i < landmarks.length; i++) {
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
                values.clear();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }


    public void addRestaurant(SQLiteDatabase dbWritableConnection, Restaurant[] restaurants) {

        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (int i = 0; i < restaurants.length; i++) {
                values.put(DbStringConstants.R_PLACE_ID, restaurants[i].getPlaceId());
                values.put(DbStringConstants.R_PRICE_CATEGORY_ID,
                        restaurants[i].getPriceCategoryId());
                values.put(DbStringConstants.R_COUSINE, restaurants[i].getCousine());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_RESTAURANTS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_RESTAURANTS + "for: i = " + i );
                }

                Log.d("Restaurants  ", " newly inserted row ID: " + rowId);
                values = new ContentValues();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    public void addShoppingPlaces(SQLiteDatabase dbWritableConnection,
                                  ShoppingPlace[] shoppingPlaces) {
        dbWritableConnection.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            for (int i = 0; i < shoppingPlaces.length; i++) {
                values.put(DbStringConstants.SP_PLACE_ID, shoppingPlaces[i].getPlaceId());
                values.put(DbStringConstants.SP_PRICE_CATEGORY_ID,
                        shoppingPlaces[i].getPriceCategoryId());

                long rowId =  dbWritableConnection.insert(DbStringConstants.TABLE_SHOPPING_PLACES,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_SHOPPING_PLACES + "for: i = " + i );
                }

                Log.d("Shopping places  ", " newly inserted row ID: " + rowId);

                values = new ContentValues();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }


    public void addWorkHours(SQLiteDatabase dbWritableConnection, WorkHours[] workHours) {
        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (int i = 0; i < workHours.length; i++) {

                values.put(DbStringConstants.WH_PLACE_ID, workHours[i].getPlaceId());
                if (workHours[i].getIs24h() == -1) {
                    values.put(DbStringConstants.WH_IS_24H, workHours[i].getIs24h());
                    values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
                } else if (workHours[i].getIs24h() == 0) {
                    values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
                    values.put(DbStringConstants.WH_SAT, workHours[i].getSat());
                    values.put(DbStringConstants.WH_SUN, workHours[i].getSun());
                } else if (workHours[i].getIs24h() == 1) {
                    values.put(DbStringConstants.WH_IS_24H, workHours[i].getIs24h());
                } else {
                    values.put(DbStringConstants.WH_IS_24H, workHours[i].getPlaceId());
                    values.put(DbStringConstants.WH_SAT, workHours[i].getPlaceId());
                    values.put(DbStringConstants.WH_SUN, workHours[i].getPlaceId());
                }

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_WORK_HOURS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_WORK_HOURS + "for: i = " + i );
                }

                Log.d("Work hours  ", " newly inserted row ID: " + rowId);

                values = new ContentValues();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }*/
}