package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;


import java.io.File;
import java.util.concurrent.Executors;

@Database(entities = {Hotel.class,
                        Image.class,
                        Landmark.class,
                        Place.class,
                        Restaurant.class,
                        ShoppingPlace.class,
                        WorkHours.class}, version = 1, exportSchema = false)

public abstract class VTGDatabase extends RoomDatabase {

    private static final String DB_NAME = "varnaTravelGuide.db";
    private static volatile VTGDatabase INSTANCE;

        public abstract HotelDao hotelDao();
        public abstract LandmarkDao landmarkDao();
        public abstract RestaurantDao restaurantDao();
        public abstract ShoppingPlaceDao shoppingPlaceDao();
    public abstract PlaceDao placeDao();

    //appliing singleton pattern to use the same instance of our
    // DB object
    public static synchronized VTGDatabase getInstance(Context context) {
        if (INSTANCE == null) INSTANCE = buildDatabase(context);

        File path=context.getDatabasePath("varnaTravelGuide.db");
        String db_path=path.getAbsolutePath();
        new File("E:\\MyCode\\VarnaTravelGuide\\VTG2currentDbState.db",db_path);
        Log.i("Path:",db_path);

        return INSTANCE;
    }


    private static VTGDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,VTGDatabase.class, DB_NAME)
                .addCallback(new Callback(){
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db){
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {

                        getInstance(context).placeDao().insertAll(Place.populatePlaces());
                        //here we access the dao objects with the queries we want to execute
                       /* getInstance(context).hotelDao().insertAll(Hotel.populateHotels());
                        getInstance(context).landmarkDao().insertAll
                                                                (Landmark.populateLandmarks());
                        getInstance(context).restaurantDao().insertAll
                                                                (Restaurant.populateRestaurants());
                        getInstance(context).shoppingPlaceDao().insertAll
                                                          (ShoppingPlace.populateShoppingPlaces());*/
                    }
                });
            }

        }).build();
    }
}