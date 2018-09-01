package com.example.yovo_user.varnatravelguide.databasePackage;


import android.content.ContentValues;
import android.content.Context;
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
    // Всички променливи са статични ! // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "varnaTravelGuide";

    private Context mCxt; ;
    private SQLiteDatabase dbWritableConnection;
    private SQLiteDatabase dbReadableConnection ;
    private PlaceDaoImpl placeDaoImpl = new PlaceDaoImpl();
    private HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
    private LandmarkDaoImpl landmarkDaoImpl = new LandmarkDaoImpl();
    private RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl();
    private ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
    private WorkHoursDaoImpl workHoursDaoImpl = new WorkHoursDaoImpl();
    private PriceCategoryDaoImpl priceCategoryDaoImpl = new PriceCategoryDaoImpl();
    private ShoppingPlacesDaoImpl shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl();

    public VTGDatabase(Context context){
                    super(context, DB_NAME, null, DATABASE_VERSION);
       SQLiteDatabase dbWritableConnection = vtgDatabase.getWritableDatabase();
    }

    public static synchronized VTGDatabase getInstance(Context context) {

        if (vtgDatabase == null) {
            vtgDatabase = new VTGDatabase(context);
        }
        return vtgDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbWritableConnection = vtgDatabase.getWritableDatabase();
        dbReadableConnection = vtgDatabase.getReadableDatabase();

        placeDaoImpl.createPlacesTable(dbWritableConnection);
        imageDaoImpl.createImageTable(dbWritableConnection);
        hotelDaoImpl.createHotelTable(dbWritableConnection);
        landmarkDaoImpl.createLandmarkTable(dbWritableConnection);
        restaurantDaoImpl.createRestaurantTable(dbWritableConnection);
        shoppingPlacesDaoImpl.createShoppingPlacesTable(dbWritableConnection);
        workHoursDaoImpl.createWorkHoursTable(dbWritableConnection);
        priceCategoryDaoImpl.createPriceCategoryTable(dbWritableConnection);
        //DATABASE SCRIPTS execution:)
        callDbScripts();
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbWritableConnection, int oldVersion, int newVersion) {
        DbBaseOperations.upgradeDb(dbWritableConnection,oldVersion,newVersion);
        onCreate(dbWritableConnection);
    }


    private void callDbScripts(){
        getHotelDaoImpl().addHotels(dbWritableConnection,Hotel.populateHotels());
        getImageDaoImpl().addImage(dbWritableConnection,Image.populateImages());
        getLandmarkDaoImpl().addLandmarks(
                getDbWritableConnection(), Landmark.populateLandmarks()
        );
        getPlaceDaoImpl().addPlaces(
                getDbWritableConnection(), Place.populatePlaces()
        );
        getPriceCategoryDaoImpl().addPriceCategory(
                dbWritableConnection, PriceCategory.populatePriceCategories()
        );
        getRestaurantDaoImpl().addRestaurant(
                dbWritableConnection, Restaurant.populateRestaurants()
        );
        getShoppingPlacesDaoImpl().addShoppingPlaces(
                dbWritableConnection,ShoppingPlace.populateShoppingPlaces()
        );
        getWorkHoursDaoImpl().addWorkHours(
                dbWritableConnection, WorkHours.populateWorkHours()
        );

         getDbWritableConnection().close();
    }


    public SQLiteDatabase getDbWritableConnection() {
        return dbWritableConnection;
    }
    public SQLiteDatabase getDbReadableConnection() {
        return dbReadableConnection;
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