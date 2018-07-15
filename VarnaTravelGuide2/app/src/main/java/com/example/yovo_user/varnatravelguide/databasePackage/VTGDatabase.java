package com.example.yovo_user.varnatravelguide.databasePackage;
/*

database class should have these things at the minimum:
@Database annotation
class to be abstract and extend from RoomDatabase class
class have an abstract method with no parameters and returns
        the class that is annotated with @Dao (in our case: getRepoDao())
*/


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.yovo_user.varnatravelguide.Image;
import com.example.yovo_user.varnatravelguide.WorkHours;

@Database(entities = {Hotels.class,
                        Image.class,
                        Landmark.class,
                        Place.class,
                        Restaurant.class,
                        ShoppingPlace.class,
                        WorkHours.class}, version = 1)
public abstract class VTGDatabase extends RoomDatabase {
    private static final String DB_NAME = "varnaTravelGuide.db";
    private static volatile VTGDatabase instance;

    static synchronized VTGDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static VTGDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                VTGDatabase.class,
                DB_NAME).build();
    }
    public abstract VTGDatabase getVTGDBDao();
}