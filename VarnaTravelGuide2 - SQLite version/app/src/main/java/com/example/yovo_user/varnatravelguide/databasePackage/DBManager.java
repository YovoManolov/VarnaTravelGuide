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
    private Context context;
    private SQLiteDatabase dbWritableConnection;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = DatabaseHelper.getInstance(this.context);
        //dbHelper = DatabaseHelper.getInstance(context.getApplicationContext());
        this.dbWritableConnection = dbHelper.getWritableDatabase();

        return this;
    }
    public void close() {
        dbHelper.close();
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


    public ImageDaoImpl getImageDaoImpl() {
        return dbHelper.getImageDaoImpl();
    }



    public WorkHoursDaoImpl getWorkHoursDaoImpl() {
        return dbHelper.getWorkHoursDaoImpl();
    }


    public PriceCategoryDaoImpl getPriceCategoryDaoImpl() {
        return dbHelper.getPriceCategoryDaoImpl();
    }

    public ShoppingPlacesDaoImpl getShoppingPlacesDaoImpl() {
        return dbHelper.getShoppingPlacesDaoImpl();
    }


/*    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }*/


    /* public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }*/

}
