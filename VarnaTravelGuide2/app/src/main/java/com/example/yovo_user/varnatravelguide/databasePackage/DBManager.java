package com.example.yovo_user.varnatravelguide.databasePackage;

import android.content.Context;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.LandmarkDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategoryDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.RestaurantDaoImpl;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;

    //private SQLiteDatabase dbWritableConnection;
      /*  public DBManager(Context c) {
            this.context = c;
    }*/

    public DBManager open(){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper();
        }
        return this;
    }

    public PlaceDaoImpl getPlaceDaoImpl() {
        return dbHelper.getPlaceDaoImpl();
    }

    public HotelDaoImpl getHotelDaoImpl() {
        return dbHelper.getHotelDaoImpl();
    }

    public LandmarkDaoImpl getLandmarkDaoImpl() {
        return dbHelper.getLandmarkDaoImpl();
    }

    public RestaurantDaoImpl getRestaurantDaoImpl() {
        return dbHelper.getRestaurantDaoImpl();
    }

    public PriceCategoryDaoImpl getPriceCategoryDaoImpl(){
        return dbHelper.getPriceCategoryDaoImpl();
    }
}
