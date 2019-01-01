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

    private static PlaceDaoImpl placeDaoImpl;
    private static HotelDaoImpl hotelDaoImpl;
    private static LandmarkDaoImpl landmarkDaoImpl;
    private static RestaurantDaoImpl restaurantDaoImpl;
    private static ImageDaoImpl imageDaoImpl;
    private static WorkHoursDaoImpl workHoursDaoImpl;
    private static PriceCategoryDaoImpl priceCategoryDaoImpl;
    private static ShoppingPlacesDaoImpl shoppingPlacesDaoImpl;

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase dbWritableConnection;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = DatabaseHelper.getInstance(this.context);
        this.dbWritableConnection = dbHelper.getWritableDatabase();

        return this;
    }
    public void close() {
        dbHelper.close();
    }



    public static PlaceDaoImpl getPlaceDaoImpl() {
        return placeDaoImpl;
    }

    public static void setPlaceDaoImpl(PlaceDaoImpl placeDaoImpl) {
        DBManager.placeDaoImpl = placeDaoImpl;
    }

    public static HotelDaoImpl getHotelDaoImpl() {
        return hotelDaoImpl;
    }

    public static void setHotelDaoImpl(HotelDaoImpl hotelDaoImpl) {
        DBManager.hotelDaoImpl = hotelDaoImpl;
    }

    public static LandmarkDaoImpl getLandmarkDaoImpl() {
        return landmarkDaoImpl;
    }

    public static void setLandmarkDaoImpl(LandmarkDaoImpl landmarkDaoImpl) {
        DBManager.landmarkDaoImpl = landmarkDaoImpl;
    }

    public static RestaurantDaoImpl getRestaurantDaoImpl() {
        return restaurantDaoImpl;
    }

    public static void setRestaurantDaoImpl(RestaurantDaoImpl restaurantDaoImpl) {
        DBManager.restaurantDaoImpl = restaurantDaoImpl;
    }

    public static ImageDaoImpl getImageDaoImpl() {
        return imageDaoImpl;
    }

    public static void setImageDaoImpl(ImageDaoImpl imageDaoImpl) {
        DBManager.imageDaoImpl = imageDaoImpl;
    }

    public static WorkHoursDaoImpl getWorkHoursDaoImpl() {
        return workHoursDaoImpl;
    }

    public static void setWorkHoursDaoImpl(WorkHoursDaoImpl workHoursDaoImpl) {
        DBManager.workHoursDaoImpl = workHoursDaoImpl;
    }

    public static PriceCategoryDaoImpl getPriceCategoryDaoImpl() {
        return priceCategoryDaoImpl;
    }

    public static void setPriceCategoryDaoImpl(PriceCategoryDaoImpl priceCategoryDaoImpl) {
        DBManager.priceCategoryDaoImpl = priceCategoryDaoImpl;
    }

    public static ShoppingPlacesDaoImpl getShoppingPlacesDaoImpl() {
        return shoppingPlacesDaoImpl;
    }

    public static void setShoppingPlacesDaoImpl(ShoppingPlacesDaoImpl shoppingPlacesDaoImpl) {
        DBManager.shoppingPlacesDaoImpl = shoppingPlacesDaoImpl;
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
