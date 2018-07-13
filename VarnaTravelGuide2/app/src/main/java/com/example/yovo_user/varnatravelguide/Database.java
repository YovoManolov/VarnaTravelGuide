package com.example.yovo_user.varnatravelguide;
/*

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Hotels.class,
                        Image.class,
                        Landmark.class,
                        Place.class,
                        Restaurant.class,
                        ShoppingPlace.class,
                        WorkHours.class}, version = 1)
public abstract class VarnaTravelGuideDatabase extends RoomDatabase {
    private static Database INSTANCE;

    public abstract Place userDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
*/
