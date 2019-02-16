package com.example.yovo_user.varnatravelguide.databasePackage;

public class DbStringConstants {
    public static final String TRUNCATE_TABLE_X = "DELETE FROM ";
    public static final String DROP_TABLE_X = "DROP TABLE IF EXISTS ";

    //TABLE NAMES
    public static final String TABLE_PLACES = "PLACES";
    public static final String TABLE_IMAGES = "IMAGES";
    public static final String TABLE_HOTELS = "HOTELS";
    public static final String TABLE_LANDMARKS = "LANDMARKS";
    public static final String TABLE_RESTAURANTS = "RESTAURANTS";
    public static final String TABLE_SHOPPING_PLACES = "SHOPPING_PLACES";
    public static final String TABLE_WORK_HOURS = "WORK_HOURS";
    public static final String TABLE_PRICE_CATEGORIES = "PRICE_CATEGORIES";


    //====================================
    //names of columns of table - PLACES :
    //====================================
    public static final String PL_ID = "ID";
    public static final String PL_NAME = "NAME";
    public static final String PL_ADDRESS = "ADDRESS";
    public static final String PL_LATITUDE = "LATITUDE";
    public static final String PL_LONGITUDE = "LONGITUDE";
    public static final String PL_CONTACTS = "CONTACTS";
    public static final String PL_DESCRIPTION = "DESCRIPTION";
    //====================================

    //====================================
    //names of columns of table - IMAGES :
    //====================================
    public static final String IM_ID = "ID";
    public static final String IM_PLACE_ID = "PLACE_ID";
    public static final String IM_IMAGE_URL= "IMAGE_URL";
    public static final String IM_MAIN_IMAGE= "MAIN_IMAGE";
    //====================================


    //====================================
    //names of columns of table - HOTELS :
    //====================================
    public static final String H_ID = "ID";
    public static final String H_PLACE_ID = "PLACE_ID";
    public static final String H_NUMB_OF_STARS = "NUMB_OF_STARS";
    public static final String H_PRICE_CATEGORY_ID = "PRICE_CATEGORY_ID";
    //====================================

    //====================================
    //names of columns of table - LANDMARKS :
    //====================================
    public static final String L_ID = "ID";
    public static final String L_PLACE_ID = "PLACE_ID";
    public static final String L_ENTRANCE_TICKET = "ENTRANCE_TICKET";
    //====================================

    //====================================
    //names of columns of table - RESTAURANTS :
    //====================================
    public static final String R_ID = "ID";
    public static final String R_PLACE_ID = "PLACE_ID";
    public static final String R_PRICE_CATEGORY_ID = "PRICE_CATEGORY_ID";
    public static final String R_COUSINE = "COUSINE";
    //====================================


    //====================================
    //names of columns of table - SHOPPING_PLACES :
    //====================================
    public static final String SP_ID = "ID";
    public static final String SP_PLACE_ID = "PLACE_ID";
    public static final String SP_PRICE_CATEGORY_ID = "PRICE_CATEGORY_ID";
    //====================================


    //====================================
    //names of columns of table - WORK_HOURS :
    //====================================
    public static final String WH_ID = "ID";
    public static final String WH_PLACE_ID = "PLACE_ID";
    public static final String WH_IS_24H = "IS_24H";
    public static final String WH_MON_FRI = "MON_FRI";
    public static final String WH_SAT = "SAT";
    public static final String WH_SUN = "SUN";
    //====================================

    //====================================
    //names of columns of table - PRICE_CATEGORIES :
    //====================================
    public static final String PC_ID = "ID";
    public static final String PC_PRICE_TYPE = "PRICE_TYPE";
    //====================================


    //!!!!!=======!!!!!!!!========!!!!!!!!
    //====================================
    //!!!!!!!CREATE TABLE SCRIPTS!!!!!!!!
    //====================================
    //!!!!!=======!!!!!!!!========!!!!!!!!


    public static String CREATE_PLACES_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PLACES + " ("
            + PL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PL_NAME + " TEXT NOT NULL, "
            + PL_ADDRESS + " TEXT NOT NULL , "
            + PL_LATITUDE + " REAL NOT NULL , "
            + PL_LONGITUDE + " REAL NOT NULL , "
            + PL_CONTACTS + " TEXT , "
            + PL_DESCRIPTION + " TEXT );";

    public static String CREATE_IMAGES_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_IMAGES + " ("
            + IM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IM_PLACE_ID + " INTEGER NOT NULL, "
            + IM_IMAGE_URL + " TEXT NOT NULL, "
            + IM_MAIN_IMAGE + " INTEGER NOT NULL, "
            + "FOREIGN KEY ("+IM_PLACE_ID +") "
            + "REFERENCES "+TABLE_PLACES +" (ID)"
            + " );";


    public static String CREATE_HOTELS_TABLE =  "CREATE TABLE IF NOT EXISTS "
            + TABLE_HOTELS + " ("
            + H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + H_PLACE_ID + " INTEGER UNIQUE NOT NULL, "
            + H_NUMB_OF_STARS + " INTEGER NOT NULL, "
            + H_PRICE_CATEGORY_ID + " INTEGER NOT NULL,"
            + "FOREIGN KEY ("+H_PLACE_ID +") "
            + "REFERENCES "+TABLE_PLACES +" (ID),"
            + " FOREIGN KEY ("+H_PRICE_CATEGORY_ID +") "
            + " REFERENCES "+TABLE_PRICE_CATEGORIES +" (ID)"
            + " );";

    public static String CREATE_LANDMARKS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LANDMARKS + " ("
            + L_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + L_PLACE_ID + " INTEGER UNIQUE NOT NULL, "
            + L_ENTRANCE_TICKET + " TEXT NOT NULL, "
            + "FOREIGN KEY ("+L_PLACE_ID +") "
            + "REFERENCES "+TABLE_PLACES +" ("+PL_ID+")"
            + " );";


    public static String CREATE_RESTAURANTS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_RESTAURANTS + " ("
            + R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + R_PLACE_ID + " INTEGER UNIQUE NOT NULL, "
            + R_PRICE_CATEGORY_ID + " INTEGER  NOT NULL, "
            + R_COUSINE + " TEXT NOT NULL, "
            + " FOREIGN KEY ("+R_PLACE_ID +") "
            + " REFERENCES "+TABLE_PLACES +" (ID), "
            + " FOREIGN KEY ("+R_PRICE_CATEGORY_ID +") "
            + " REFERENCES "+TABLE_PRICE_CATEGORIES +" (ID) "
            + " )";


    public static String CREATE_SHOPPING_PLACES_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SHOPPING_PLACES + " ("
            + SP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SP_PLACE_ID + " INTEGER UNIQUE NOT NULL, "
            + SP_PRICE_CATEGORY_ID + " INTEGER NOT NULL, "
            + " FOREIGN KEY ("+SP_PLACE_ID +") "
            + " REFERENCES "+TABLE_PLACES +" (ID), "
            + " FOREIGN KEY ("+SP_PRICE_CATEGORY_ID +") "
            + " REFERENCES "+TABLE_PRICE_CATEGORIES +" (ID) "
            +  " );";


    public static String CREATE_WORK_HOURS_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_WORK_HOURS + " ("
            + WH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WH_PLACE_ID + " INTEGER UNIQUE NOT NULL,"
            + WH_IS_24H + " INTEGER ,"
            + WH_MON_FRI + " TEXT ,"
            + WH_SAT + "  TEXT ,"
            + WH_SUN + " TEXT ,"
            + " FOREIGN KEY ("+WH_PLACE_ID +") "
            + " REFERENCES "+TABLE_PLACES +" (ID)"
            + " );";

    public static String CREATE_PRICE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PRICE_CATEGORIES + " ("
                + PC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PC_PRICE_TYPE + " TEXT NOT NULL"+ " );";


    public static String GET_PRICE_CATEGORIES_BY_ID = "SELECT * FROM "+
            TABLE_PRICE_CATEGORIES
            + "WHERE "+PC_ID+" = ?"+ ";";
    public static String GET_PLACE_BY_ID = "SELECT * FROM "+ TABLE_PLACES
            + "WHERE "+PL_ID+" = ? " + ";";
    public static String GET_IMAGES_FOR_PLACE = "SELECT * FORM " +TABLE_IMAGES
            + "WHERE " + IM_PLACE_ID + " = ? " + ";";
    public static String GET_MAIN_IMAGE_FOR_PLACE = "SELECT * FORM " +TABLE_IMAGES
            + "WHERE " + IM_PLACE_ID + " = ?  AND "+ IM_MAIN_IMAGE + "= ? " + ";";
    public static String GET_WORK_HOURS_BY_ID = "SELECT * FROM " + TABLE_WORK_HOURS
            +  "WHERE PLACE_ID = ? " + ";";
    public static String GET_ALL_RESTAURANTS = "SELECT * FROM " + TABLE_RESTAURANTS + ";";
    public static String GET_ALL_HOTELS = "SELECT * FROM " + TABLE_HOTELS + ";";
    public static String GET_ALL_LANDMARKS = "SELECT * FROM " + TABLE_LANDMARKS + ";";
    public static String GET_ALL_SHOPPING_PLACES = "SELECT * FROM " + TABLE_SHOPPING_PLACES + ";";

}