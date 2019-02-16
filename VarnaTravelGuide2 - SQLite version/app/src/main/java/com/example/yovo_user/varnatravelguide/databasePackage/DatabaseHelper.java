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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper  extends SQLiteOpenHelper {

    private static DatabaseHelper dbHelper;

    private PlaceDaoImpl placeDaoImpl;
    private HotelDaoImpl hotelDaoImpl;
    private LandmarkDaoImpl landmarkDaoImpl;
    private RestaurantDaoImpl restaurantDaoImpl;
    private ImageDaoImpl imageDaoImpl;
    private WorkHoursDaoImpl workHoursDaoImpl;
    private PriceCategoryDaoImpl priceCategoryDaoImpl;
    private ShoppingPlacesDaoImpl shoppingPlacesDaoImpl;

    // Версия на Базата от Данни
    private static final String DB_NAME = "varnaTravelGuide.db";
    private static final int DATABASE_VERSION = 2;


    public DatabaseHelper(Context context){
         super(context, DB_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase dbWritableConnection) {

        placeDaoImpl = new PlaceDaoImpl(dbWritableConnection);
        hotelDaoImpl = new HotelDaoImpl(dbWritableConnection);
        landmarkDaoImpl = new LandmarkDaoImpl(dbWritableConnection);
        restaurantDaoImpl = new RestaurantDaoImpl(dbWritableConnection);
        imageDaoImpl = new ImageDaoImpl(dbWritableConnection);
        workHoursDaoImpl = new WorkHoursDaoImpl(dbWritableConnection);
        priceCategoryDaoImpl = new PriceCategoryDaoImpl(dbWritableConnection);
        shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl(dbWritableConnection);

        placeDaoImpl.createPlacesTable();
        priceCategoryDaoImpl.createPriceCategoryTable();
        imageDaoImpl.createImageTable();
        hotelDaoImpl.createHotelTable();
        landmarkDaoImpl.createLandmarkTable();
        restaurantDaoImpl.createRestaurantTable();
        shoppingPlacesDaoImpl.createShoppingPlacesTable();
        workHoursDaoImpl.createWorkHoursTable();

        placeDaoImpl.addPlaces(Place.populatePlaces());
        priceCategoryDaoImpl.addPriceCategory(PriceCategory.populatePriceCategories());
        imageDaoImpl.addImage(Image.populateImages());
        hotelDaoImpl.addHotels(Hotel.populateHotels());
        landmarkDaoImpl.addLandmarks(Landmark.populateLandmarks());
        restaurantDaoImpl.addRestaurant(Restaurant.populateRestaurants());
        shoppingPlacesDaoImpl.addShoppingPlaces(ShoppingPlace.populateShoppingPlaces());
        workHoursDaoImpl.addWorkHours(WorkHours.populateWorkHours());

        List<Hotel> allHotels = new ArrayList<Hotel>();
        Cursor cursor = dbWritableConnection.
                rawQuery(DbStringConstants.GET_ALL_HOTELS,null);

        if (cursor.moveToFirst()) {
            do {
                Hotel hotel = new Hotel(cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3));
                allHotels.add(hotel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        for(Hotel hotel : allHotels){
            System.out.println(hotel.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbWritableConnection, int oldVersion, int newVersion) {
        DbBaseOperations.upgradeDb(dbWritableConnection,oldVersion,newVersion);
        onCreate(dbWritableConnection);
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