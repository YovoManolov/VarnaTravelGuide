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

    private static SQLiteDatabase db;
    private static VTGDatabase vtgDatabase;
    private static Context context;

    // Всички променливи са статични ! // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "varnaTravelGuide.db";


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
                    this.context = context;
    }

    public static synchronized VTGDatabase getInstance(Context context) {

        if (vtgDatabase == null) {
            vtgDatabase = new VTGDatabase(context.getApplicationContext());
        }
        return vtgDatabase;
    }


    @Override
    public void onConfigure(SQLiteDatabase db){
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
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