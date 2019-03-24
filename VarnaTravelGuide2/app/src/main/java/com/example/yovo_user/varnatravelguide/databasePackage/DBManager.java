package com.example.yovo_user.varnatravelguide.databasePackage;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

public class DBManager {

    private DatabaseHelper dbHelper;
    /*
        private Context context;
        private SQLiteDatabase dbWritableConnection;
    */

    /* public DBManager(Context c) {
        this.context = c;
    }*/

    public DBManager open() throws SQLException {
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

}
