package com.example.yovo_user.varnatravelguide.databasePackage;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

public class VTGDatabase extends SQLiteOpenHelper {

    private static VTGDatabase vtgDatabase;
    private SQLiteDatabase dbWritableConnection;

    // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "varnaTravelGuide.db";

    private static PlaceDaoImpl placeDaoImpl;
    private static HotelDaoImpl hotelDaoImpl;
    private static LandmarkDaoImpl landmarkDaoImpl;
    private static RestaurantDaoImpl restaurantDaoImpl;
    private static ImageDaoImpl imageDaoImpl;
    private static WorkHoursDaoImpl workHoursDaoImpl;
    private static PriceCategoryDaoImpl priceCategoryDaoImpl;
    private static ShoppingPlacesDaoImpl shoppingPlacesDaoImpl;

    public VTGDatabase(Context context){
            super(context, DB_NAME, null, DATABASE_VERSION);
            placeDaoImpl = new PlaceDaoImpl();
            hotelDaoImpl = new HotelDaoImpl();
            landmarkDaoImpl = new LandmarkDaoImpl();
            restaurantDaoImpl = new RestaurantDaoImpl();
            imageDaoImpl = new ImageDaoImpl();
            workHoursDaoImpl = new WorkHoursDaoImpl();
            priceCategoryDaoImpl = new PriceCategoryDaoImpl();
            shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl();
            dbWritableConnection = getWritableDatabase();
    }

    public static synchronized VTGDatabase getInstance(Context context) {

        if (vtgDatabase == null) {
            vtgDatabase = new VTGDatabase(context.getApplicationContext());

        }
        return vtgDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        placeDaoImpl.createPlacesTable(db);
        priceCategoryDaoImpl.createPriceCategoryTable(db);
        imageDaoImpl.createImageTable(db);
        hotelDaoImpl.createHotelTable(db);
        landmarkDaoImpl.createLandmarkTable(db);
        restaurantDaoImpl.createRestaurantTable(db);
        shoppingPlacesDaoImpl.createShoppingPlacesTable(db);
        workHoursDaoImpl.createWorkHoursTable(db);

        placeDaoImpl.addPlaces(db, Place.populatePlaces());
        priceCategoryDaoImpl.addPriceCategory(db, PriceCategory.populatePriceCategories());
        imageDaoImpl.addImage(db, Image.populateImages());
        hotelDaoImpl.addHotels(db, Hotel.populateHotels());
        landmarkDaoImpl.addLandmarks(db, Landmark.populateLandmarks());
        restaurantDaoImpl.addRestaurant(db, Restaurant.populateRestaurants() );
        shoppingPlacesDaoImpl.addShoppingPlaces(db, ShoppingPlace.populateShoppingPlaces() );
        workHoursDaoImpl.addWorkHours(db, WorkHours.populateWorkHours() );

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


}