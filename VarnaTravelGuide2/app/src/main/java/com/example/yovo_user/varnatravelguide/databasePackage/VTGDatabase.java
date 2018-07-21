package com.example.yovo_user.varnatravelguide.databasePackage;
/*

database class should have these things at the minimum:
@Database annotation
class to be abstract and extend from RoomDatabase class
class have an abstract method with no parameters and returns
        the class that is annotated with @Dao (in our case: getRepoDao())
*/

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

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

    static synchronized VTGDatabase getInstance(Context context) {
        if (INSTANCE == null) INSTANCE = buildDatabase(context);
        return INSTANCE;
    }

    public abstract VTGDatabase getVTGDBDao();

    private static VTGDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,VTGDatabase.class, DB_NAME)
                .addCallback(new Callback(){

            @Override
            public void OnCreate(@NonNull SupportSQLiteDatabase db){
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        //here we access the dao objects with the queries we want to execute

                        //getInstance(context).dataDao().insertAll(DataEntity.populateData());
                    }
                });
            }

        }).build();
    }
}