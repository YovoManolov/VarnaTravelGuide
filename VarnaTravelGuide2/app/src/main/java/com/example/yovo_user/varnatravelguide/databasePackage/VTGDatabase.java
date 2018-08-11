package com.example.yovo_user.varnatravelguide.databasePackage;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VTGDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "varnaTravelGuide.db";
    private static volatile VTGDatabase INSTANCE;

    // Всички променливи са статични ! // Версия на Базата от Данни
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "varnaTravelGuide";

    private static final String TRUNCATE_TABLE_X = "DELETE FROM ";


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

        String CREATE_PLACES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLACES + "("
                + PL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + PL_HOTEL_ID + " INTEGER DEFAULT NULL,"
                + PL_LANDMARK_ID + " INTEGER DEFAULT NULL,"
                + PL_RESTAURANT_ID + " INTEGER DEFAULT NULL,"
                + PL_SHOPPING_PLACES_ID + " INTEGER DEFAULT NULL,"
                + PL_WORK_HOURS_ID + " INTEGER,"
                + PL_NAME + " TEXT NOT NULL ,"
                + PL_ADDRESS + " TEXT NOT NULL ,"
                + PL_LATITUDE + " TEXT NOT NULL ,"
                + PL_LONGITUDE + " TEXT NOT NULL ,"
                + PL_CONTACTS + " TEXT ,"
                + PL_DESCRIPTION + " TEXT ,"
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


        String CREATE_IMAGES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGES + "("
                + IM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + IM_PLACE_ID + " INTEGER NOT NULL,"
                + IMAGE_URL + " TEXT NOT NULL,"
                + "FOREIGN KEY("+IM_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_HOTELS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HOTELS + "("
                + H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + H_PLACE_ID + " INTEGER NOT NULL,"
                + H_NUMB_OF_STARS + " INTEGER NOT NULL,"
                + "FOREIGN KEY("+H_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_LANDMARKS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_LANDMARKS + "("
                + L_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + L_PLACE_ID + " INTEGER NOT NULL,"
                + L_ENTRANCE_TICKET + " TEXT NOT NULL,"
                + "FOREIGN KEY("+L_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_RESTAURANTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_RESTAURANTS + "("
                + R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + R_PLACE_ID + " INTEGER NOT NULL,"
                + R_PRICE_CATEGORY_ID + "  NOT NULL,"
                + R_COUSINE + " TEXT NOT NULL,"
                + " FOREIGN KEY("+R_PLACE_ID +") "
                + " REFERENCES "+TABLE_PLACES +"(ID),"
                + " FOREIGN KEY("+R_PRICE_CATEGORY_ID +") "
                + " REFERENCES "+TABLE_PRICE_CATEGORIES +"(ID)"
                + ")";


        String SHOPPING_PLACES = "CREATE TABLE IF NOT EXISTS " + TABLE_SHOPPING_PLACES + "("
                + SP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + SP_PLACE_ID + " INTEGER NOT NULL,"
                + SP_PRICE_CATEGORY_ID + " INTEGER NOT NULL,"
                + " FOREIGN KEY("+SP_PLACE_ID +") "
                + " REFERENCES "+TABLE_PLACES +"(ID),"
                + " FOREIGN KEY("+SP_PRICE_CATEGORY_ID +") "
                + " REFERENCES "+TABLE_PRICE_CATEGORIES +"(ID)"
                + ")";


        String CREATE_WORK_HOURS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_WORK_HOURS + "("
                + WH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + WH_PLACE_ID + " INTEGER NOT NULL,"
                + WH_IS_24H + " INTEGER ,"
                + WH_MON_FRI + " TEXT ,"
                + WH_SAT + "  TEXT ,"
                + WH_SUN + " TEXT ,"
                + " FOREIGN KEY("+WH_PLACE_ID +") "
                + " REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";


        String CREATE_PRICE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRICE_CATEGORIES + "("
                + PC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
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

    public void addPlaces(Place[] places){
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i = 0 ;i < places.length ;i++){
            ContentValues values = new ContentValues();

            if(places[i].getHotelId() != 0){
                values.put(PL_HOTEL_ID,places[i].getHotelId());
            }else if(places[i].getLandmarkId() != 0){
                values.put(PL_LANDMARK_ID,places[i].getLandmarkId());
            }else if(places[i].getRestaurantId() != 0){
                values.put(PL_RESTAURANT_ID,places[i].getRestaurantId());
            }else if(places[i].getShoppingPlaceId() != 0){
                values.put(PL_SHOPPING_PLACES_ID,places[i].getShoppingPlaceId());
            }
            values.put(PL_WORK_HOURS_ID, places[i].getWorkHoursId());
            values.put(PL_NAME, places[i].getName());
            values.put(PL_ADDRESS, places[i].getAddress());
            values.put(PL_LATITUDE, places[i].getLatitude());
            values.put(PL_LONGITUDE, places[i].getLongitude());
            values.put(PL_CONTACTS, places[i].getContacts());
            values.put(PL_DESCRIPTION, places[i].getDescription());

            db.insert(TABLE_PLACES, null, values);
        }
        db.close();
    }

    public void addWorkHours(WorkHours[] workHours){
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i = 0 ;i < workHours.length ;i++){
            ContentValues values = new ContentValues();

            values.put(WH_PLACE_ID, workHours[i].getPlaceId());
            if(workHours[i].getIs24h() == -1){
                values.put(WH_IS_24H,workHours[i].getIs24h());
                values.put(WH_MON_FRI, workHours[i].getMonFri());
            }
            else if(workHours[i].getIs24h() == 0){
                values.put(WH_MON_FRI, workHours[i].getMonFri());
                values.put(WH_SAT, workHours[i].getSat());
                values.put(WH_SUN, workHours[i].getSun());
            }
            else if(workHours[i].getIs24h() == 1){
                values.put(WH_IS_24H, workHours[i].getIs24h());
            }
            else{
                values.put(WH_IS_24H, workHours[i].getPlaceId());
                values.put(WH_SAT, workHours[i].getPlaceId());
                values.put(WH_SUN, workHours[i].getPlaceId());
            }
            db.insert(TABLE_WORK_HOURS, null, values);
        }
        db.close();
    }

    public void createWorkHoursTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK_HOURS);
        String CREATE_WORK_HOURS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_WORK_HOURS + "("
                + WH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + WH_PLACE_ID + " INTEGER NOT NULL,"
                + WH_IS_24H + " INTEGER ,"
                + WH_MON_FRI + " TEXT ,"
                + WH_SAT + "  TEXT ,"
                + WH_SUN + " TEXT ,"
                + " FOREIGN KEY("+WH_PLACE_ID +") "
                + " REFERENCES "+TABLE_PLACES +"(ID)"
                + ")";
        db.execSQL(CREATE_WORK_HOURS_TABLE);
    }

    public void createPriceCategoryTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRICE_CATEGORIES);
        String CREATE_PRICE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRICE_CATEGORIES + "("
                + PC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + PC_PRICE_TYPE + " TEXT NOT NULL"
                + ")";
        db.execSQL(CREATE_PRICE_CATEGORIES_TABLE);
    }

    public void addPriceCategory(PriceCategory[] priceCategories){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(TRUNCATE_TABLE_X + TABLE_PRICE_CATEGORIES);

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < priceCategories.length ;i++){
            values.put(PC_PRICE_TYPE,priceCategories[i].getPriceType());
            db.insert(TABLE_PRICE_CATEGORIES, null, values);
        }
    }

    public void addRestaurants (Restaurant[] restaurants) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < restaurants.length ;i++){
            values.put(R_PLACE_ID,restaurants[i].getPlaceId());
            values.put(R_PRICE_CATEGORY_ID,restaurants[i].getPriceCategoryId());
            values.put(R_COUSINE,restaurants[i].getCousine());
            db.insert(TABLE_RESTAURANTS, null, values);
        }
    }

    public void addShoppingPlaces(ShoppingPlace[] shoppingPlaces) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < shoppingPlaces.length ;i++){
            values.put(SP_PLACE_ID,shoppingPlaces[i].getPlaceId());
            values.put(SP_PRICE_CATEGORY_ID,shoppingPlaces[i].getPriceCategoryId());

            db.insert(TABLE_SHOPPING_PLACES, null, values);
        }
    }


    public void createHotelTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOTELS);
        String CREATE_HOTELS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HOTELS + "("
                + H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + H_PLACE_ID + " INTEGER NOT NULL,"
                + H_NUMB_OF_STARS + " INTEGER NOT NULL,"
                + H_PRICE_CATEGORY_ID + " INTEGER NOT NULL,"
                + "FOREIGN KEY("+H_PLACE_ID +") "
                + "REFERENCES "+TABLE_PLACES +"(ID),"
                + " FOREIGN KEY("+H_PRICE_CATEGORY_ID +") "
                + " REFERENCES "+TABLE_PRICE_CATEGORIES +"(ID)"
                + ")";
        db.execSQL(CREATE_HOTELS_TABLE);
    }
    public void addHotels(Hotel[] hotels) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < hotels.length ;i++){
            values.put(H_PLACE_ID, hotels[i].getPlaceId());
            values.put(H_NUMB_OF_STARS, hotels[i].getNumbOfStars());
            values.put(H_PRICE_CATEGORY_ID, hotels[i].getPriceCategoryId());

            db.insert(TABLE_HOTELS, null, values);
        }
    }

    public void addLandmarks(Landmark[] landmarks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < landmarks.length ;i++){
            values.put(L_PLACE_ID, landmarks[i].getPlaceId());
            values.put(L_ENTRANCE_TICKET, landmarks[i].getEntranceTicket());

            db.insert(TABLE_LANDMARKS, null, values);
        }
    }


    public void addImages(Image[] images) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < images.length ;i++){
            values.put(IM_PLACE_ID, images[i].getPlaceId());
            values.put(IMAGE_URL, images[i].getImageURL());

            db.insert(TABLE_IMAGES, null, values);
        }
    }
}