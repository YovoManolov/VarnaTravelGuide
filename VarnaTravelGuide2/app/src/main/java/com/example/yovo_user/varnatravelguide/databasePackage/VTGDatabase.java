package com.example.yovo_user.varnatravelguide.databasePackage;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceDaoImpl;

public class VTGDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "varnaTravelGuide.db";



    private SQLiteDatabase dbWritableConnection = this.getWritableDatabase();

    private HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();



    private PlaceDaoImpl placeDaoImpl = new PlaceDaoImpl();

    // Всички променливи са статични ! // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "varnaTravelGuide";


    public VTGDatabase(Context context)
                    { super(context, DATABASE_NAME, null, DATABASE_VERSION); }


    @Override public void onCreate(SQLiteDatabase db) {
        placeDaoImpl.createPlacesTable(dbWritableConnection);
        db.execSQL(DbStringConstants.CREATE_IMAGES_TABLE);
        hotelDaoImpl.createHotelTable(dbWritableConnection);
        db.execSQL(DbStringConstants.CREATE_LANDMARKS_TABLE);
        db.execSQL(DbStringConstants.CREATE_RESTAURANTS_TABLE);
        db.execSQL(DbStringConstants.SHOPPING_PLACES);
        db.execSQL(DbStringConstants.CREATE_WORK_HOURS_TABLE);
        db.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbWritableConnection, int oldVersion, int newVersion) {
        DbBaseOperations.upgradeDb(dbWritableConnection,oldVersion,newVersion);
        onCreate(dbWritableConnection);
    }

    /*public void addPlaces(Place[] places){
        dbWritableConnection.beginTransaction();

        for(int i = 0 ;i < places.length ;i++){
            ContentValues values = new ContentValues();

            if(places[i].getHotelId() != 0){
                values.put(DbStringConstants.PL_HOTEL_ID,places[i].getHotelId());
            }else if(places[i].getLandmarkId() != 0){
                values.put(DbStringConstants.PL_LANDMARK_ID,places[i].getLandmarkId());
            }else if(places[i].getRestaurantId() != 0){
                values.put(DbStringConstants.PL_RESTAURANT_ID,places[i].getRestaurantId());
            }else if(places[i].getShoppingPlaceId() != 0){
                values.put(DbStringConstants.PL_SHOPPING_PLACES_ID,places[i].getShoppingPlaceId());
            }
            values.put(DbStringConstants.PL_WORK_HOURS_ID, places[i].getWorkHoursId());
            values.put(DbStringConstants.PL_NAME, places[i].getName());
            values.put(DbStringConstants.PL_ADDRESS, places[i].getAddress());
            values.put(DbStringConstants.PL_LATITUDE, places[i].getLatitude());
            values.put(DbStringConstants.PL_LONGITUDE, places[i].getLongitude());
            values.put(DbStringConstants.PL_CONTACTS, places[i].getContacts());
            values.put(DbStringConstants.PL_DESCRIPTION, places[i].getDescription());

            dbWritableConnection.insert(DbStringConstants.TABLE_PLACES, null, values);
        }

        dbWritableConnection.endTransaction();
    }*/

    public void addWorkHours(WorkHours[] workHours){
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i = 0 ;i < workHours.length ;i++){
            ContentValues values = new ContentValues();

            values.put(DbStringConstants.WH_PLACE_ID, workHours[i].getPlaceId());
            if(workHours[i].getIs24h() == -1){
                values.put(DbStringConstants.WH_IS_24H,workHours[i].getIs24h());
                values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
            }
            else if(workHours[i].getIs24h() == 0){
                values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
                values.put(DbStringConstants.WH_SAT, workHours[i].getSat());
                values.put(DbStringConstants.WH_SUN, workHours[i].getSun());
            }
            else if(workHours[i].getIs24h() == 1){
                values.put(DbStringConstants.WH_IS_24H, workHours[i].getIs24h());
            }
            else{
                values.put(DbStringConstants.WH_IS_24H, workHours[i].getPlaceId());
                values.put(DbStringConstants.WH_SAT, workHours[i].getPlaceId());
                values.put(DbStringConstants.WH_SUN, workHours[i].getPlaceId());
            }
            db.insert(DbStringConstants.TABLE_WORK_HOURS, null, values);
        }
        db.close();
    }

    public void createWorkHoursTable(){
        DbBaseOperations.dropTableX(dbWritableConnection, DbStringConstants.TABLE_WORK_HOURS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_WORK_HOURS_TABLE);
    }

    public void createPriceCategoryTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        DbBaseOperations.dropTableX(db, DbStringConstants.TABLE_PRICE_CATEGORIES);
        db.execSQL(DbStringConstants.CREATE_PRICE_CATEGORIES_TABLE);
    }

    public void addPriceCategory(PriceCategory[] priceCategories){
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < priceCategories.length ;i++){
            values.put(DbStringConstants.PC_PRICE_TYPE,
                                            priceCategories[i].getPriceType());
            dbWritableConnection.insert(DbStringConstants.TABLE_PRICE_CATEGORIES,
                                        null, values);
        }

        dbWritableConnection.endTransaction();
    }

    public void addRestaurants (Restaurant[] restaurants) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < restaurants.length ;i++){
            values.put(DbStringConstants.R_PLACE_ID,restaurants[i].getPlaceId());
            values.put(DbStringConstants.R_PRICE_CATEGORY_ID,restaurants[i].getPriceCategoryId());
            values.put(DbStringConstants.R_COUSINE,restaurants[i].getCousine());
            dbWritableConnection.insert(DbStringConstants.TABLE_RESTAURANTS, null, values);
        }
        dbWritableConnection.endTransaction();
    }

    public void addShoppingPlaces(ShoppingPlace[] shoppingPlaces) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < shoppingPlaces.length ;i++){
            values.put(DbStringConstants.SP_PLACE_ID,shoppingPlaces[i].getPlaceId());
            values.put(DbStringConstants.SP_PRICE_CATEGORY_ID,
                                        shoppingPlaces[i].getPriceCategoryId());

            dbWritableConnection.insert(DbStringConstants.TABLE_SHOPPING_PLACES,
                                        null, values);
        }
        dbWritableConnection.endTransaction();
    }

    public void addLandmarks(Landmark[] landmarks) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < landmarks.length ;i++){
            values.put(DbStringConstants.L_PLACE_ID, landmarks[i].getPlaceId());
            values.put(DbStringConstants.L_ENTRANCE_TICKET,
                                    landmarks[i].getEntranceTicket());

            dbWritableConnection.insert(DbStringConstants.TABLE_LANDMARKS,
                                null, values);
        }
        dbWritableConnection.endTransaction();
    }


    public void addImages(Image[] images) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < images.length ;i++){
            values.put(DbStringConstants.IM_PLACE_ID, images[i].getPlaceId());
            values.put(DbStringConstants.IMAGE_URL, images[i].getImageURL());

            dbWritableConnection.insert(DbStringConstants.TABLE_IMAGES,
                    null, values);
        }
        dbWritableConnection.endTransaction();
    }

    public HotelDaoImpl getHotelDaoImpl() {
        return hotelDaoImpl;
    }

    public SQLiteDatabase getDbWritableConnection() {
        return dbWritableConnection;
    }

    public PlaceDaoImpl getPlaceDaoImpl() {
        return placeDaoImpl;
    }
}