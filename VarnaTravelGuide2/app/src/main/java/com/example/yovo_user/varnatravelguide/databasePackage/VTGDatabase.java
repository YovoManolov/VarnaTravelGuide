package com.example.yovo_user.varnatravelguide.databasePackage;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VTGDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "varnaTravelGuide.db";
    private static volatile VTGDatabase INSTANCE;

    // Всички променливи са статични ! // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "varnaTravelGuide";

    //TABLE NAMES
    private static final String TABLE_PLACES = "PLACES";
    private static final String TABLE_IMAGES = "IMAGES";
    private static final String TABLE_HOTELS = "HOTELS";
    private static final String TABLE_LANDMARKS = "LANDMARKS";
    private static final String TABLE_RESTAURANTS = "RESTAURANTS";
    private static final String TABLE_SHOPPING_PLACES = "SHOPPING_PLACES";
    private static final String TABLE_WORK_HOURS = "WORK_HOURS";
    private static final String TABLE_PRICE_CATEGORIES = "PRICE_CATEGORIES";

    //====================================
    //names of columns of table - PLACES :
    //====================================
    private static final String PL_ID = "ID";
    private static final String PL_PLACE_ID = "PLACE_ID";
    private static final String PL_HOTEL_ID = "HOTEL_ID";
    private static final String PL_LANDMARK_ID = "LANDMARK_ID";
    private static final String PL_RESTAURANT_ID = "RESTAURANT_ID";
    private static final String
                        PL_SHOPPING_PLACES_ID = "SHOPPING_PLACES_ID";
    private static final String
                        PL_WORK_HOURS_ID = "WORK_HOURS_ID";
    private static final String PL_NAME = "NAME";
    private static final String PL_ADDRESS = "ADDRESS";
    private static final String PL_LATITUDE = "LATITUDE";
    private static final String PL_LONGITUDE = "LONGITUDE";
    private static final String PL_CONTACTS = "CONTACTS";
    private static final String PL_DESCRIPTION = "DESCRIPTION";
    //====================================


    //====================================
    //names of columns of table - IMAGES :
    //====================================
    private static final String IM_ID = "ID";
    private static final String IM_PLACE_ID = "PLACE_ID";
    private static final String IMAGE_URL= "IMAGE_URL";
    //====================================


    //====================================
    //names of columns of table - HOTELS :
    //====================================
    private static final String H_ID = "ID";
    private static final String H_PLACE_ID = "PLACE_ID";
    private static final String H_NUMB_OF_STARS = "NUMB_OF_STARS";
    private static final String H_PRICE_CATEGORY_ID = "PRICE_CATEGORY_ID";
    //====================================

    //====================================
    //names of columns of table - LANDMARKS :
    //====================================
    private static final String L_ID = "ID";
    private static final String L_PLACE_ID = "PLACE_ID";
    private static final String L_ENTRANCE_TICKET = "ENTRANCE_TICKET";
    //====================================

    //====================================
    //names of columns of table - RESTAURANTS :
    //====================================
    private static final String R_ID = "ID";
    private static final String R_PLACE_ID = "PLACE_ID";
    private static final String R_PRICE_CATEGORY_ID = "PRICE_CATEGORY_ID";
    private static final String R_COUSINE = "COUSINE";
    //====================================


    //====================================
    //names of columns of table - SHOPPING_PLACES :
    //====================================
    private static final String SP_ID = "ID";
    private static final String SP_PLACE_ID = "PLACE_ID";
    private static final String SP_PRICE_CATEGORY_ID = "PRICE_CATEGORY_ID";
    //====================================


    //====================================
    //names of columns of table - WORK_HOURS :
    //====================================
    private static final String WH_ID = "ID";
    private static final String WH_PLACE_ID = "PLACE_ID";
    private static final String WH_IS_24H = "IS_24H";
    private static final String WH_MON_FRI = "MON_FRI";
    private static final String WH_SAT = "SAT";
    private static final String WH_SUN = "SUN";
    //====================================

    //====================================
    //names of columns of table - PRICE_CATEGORIES :
    //====================================
    private static final String PC_ID = "ID";
    private static final String PC_PRICE_TYPE = "PRICE_TYPE";
    //====================================

    public VTGDatabase(Context context)
                    { super(context, DATABASE_NAME, null, DATABASE_VERSION); }


    @Override public void onCreate(SQLiteDatabase db) {
        String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_PLACES + "("
                + PL_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + PL_PLACE_ID + " INTEGER NOT NULL,"
                + PL_HOTEL_ID + " INTEGER,"
                + PL_LANDMARK_ID + " INTEGER,"
                + PL_RESTAURANT_ID + " INTEGER,"
                + PL_SHOPPING_PLACES_ID + " INTEGER,"
                + PL_WORK_HOURS_ID + " INTEGER,"
                + PL_NAME + " TEXT NOT NULL ,"
                + PL_ADDRESS + " TEXT NOT NULL ,"
                + PL_LATITUDE + " TEXT NOT NULL ,"
                + PL_LONGITUDE + " TEXT NOT NULL ,"
                + PL_CONTACTS + " TEXT ,"
                + PL_DESCRIPTION + " TEXT ,"
                + " FOREIGN KEY("+PL_PLACE_ID +") " +
                                    "REFERENCES "+TABLE_PLACES +"(ID),"
                + " FOREIGN KEY("+PL_HOTEL_ID+") " +
                                    "REFERENCES "+TABLE_HOTELS+"(ID),"
                + " FOREIGN KEY("+PL_LANDMARK_ID +") " +
                                    "REFERENCES "+ TABLE_LANDMARKS +"(ID),"
                + " FOREIGN KEY("+PL_RESTAURANT_ID +") " +
                                    "REFERENCES "+ TABLE_RESTAURANTS+"(ID),"
                + " FOREIGN KEY("+PL_SHOPPING_PLACES_ID +") " +
                                    "REFERENCES "+TABLE_SHOPPING_PLACES +"(ID),"
                + " FOREIGN KEY("+PL_WORK_HOURS_ID +") " +
                                    "REFERENCES "+TABLE_WORK_HOURS +"(ID)"+
        ")";


        String CREATE_IMAGES_TABLE = "CREATE TABLE " + TABLE_IMAGES + "("
                + IM_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + IM_PLACE_ID + " INTEGER NOT NULL,"
                + IMAGE_URL + " TEXT NOT NULL,"
                + "FOREIGN KEY("+PL_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_HOTELS_TABLE = "CREATE TABLE " + TABLE_HOTELS + "("
                + H_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + H_PLACE_ID + " INTEGER NOT NULL,"
                + H_NUMB_OF_STARS + " INTEGER NOT NULL,"
                + "FOREIGN KEY("+PL_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_LANDMARKS_TABLE = "CREATE TABLE " + TABLE_LANDMARKS + "("
                + L_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + L_PLACE_ID + " INTEGER NOT NULL,"
                + L_ENTRANCE_TICKET + " TEXT NOT NULL,"
                + "FOREIGN KEY("+PL_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_RESTAURANTS_TABLE = "CREATE TABLE " + TABLE_RESTAURANTS + "("
                + R_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + R_PLACE_ID + " INTEGER NOT NULL,"
                + R_PRICE_CATEGORY_ID + "  NOT NULL,"
                + R_COUSINE + " TEXT NOT NULL,"
                + "FOREIGN KEY("+PL_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID),"
                + "FOREIGN KEY("+R_PRICE_CATEGORY_ID +") "
                + "REFERENCES "+TABLE_PRICE_CATEGORIES +"(ID)"
                + ")";


        String SHOPPING_PLACES = "CREATE TABLE " + TABLE_SHOPPING_PLACES + "("
                + SP_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + SP_PLACE_ID + " INTEGER NOT NULL,"
                + SP_PRICE_CATEGORY_ID + " INTEGER NOT NULL,"
                + "FOREIGN KEY("+PL_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID),"
                + "FOREIGN KEY("+SP_PRICE_CATEGORY_ID +") "
                + "REFERENCES "+TABLE_PRICE_CATEGORIES +"(ID)"
                + ")";


        String CREATE_WORK_HOURS_TABLE = "CREATE TABLE " + TABLE_LANDMARKS + "("
                + WH_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + WH_PLACE_ID + " INTEGER NOT NULL,"
                + WH_IS_24H + " INTEGER ,"
                + WH_MON_FRI + " TEXT ,"
                + WH_SAT + "  TEXT ,"
                + WH_SUN + " TEXT ,"
                + "FOREIGN KEY("+WH_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_PRICE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_LANDMARKS + "("
                + PC_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + PC_PRICE_TYPE + " TEXT NOT NULL"
                + ")";


        db.execSQL(CREATE_PLACES_TABLE);
        db.execSQL(CREATE_IMAGES_TABLE);
        db.execSQL(CREATE_HOTELS_TABLE);
        db.execSQL(CREATE_LANDMARKS_TABLE);
        db.execSQL(CREATE_RESTAURANTS_TABLE);
        db.execSQL(SHOPPING_PLACES);
        db.execSQL(CREATE_WORK_HOURS_TABLE);
        db.execSQL(CREATE_PRICE_CATEGORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOTELS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LANDMARKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_PLACES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK_HOURS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRICE_CATEGORIES);
        // Повторно създаване на базата от данни
        onCreate(db);
    }

}