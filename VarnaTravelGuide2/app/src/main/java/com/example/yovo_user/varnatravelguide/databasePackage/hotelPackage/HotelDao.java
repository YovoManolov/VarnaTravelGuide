package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import android.database.sqlite.SQLiteDatabase;

public interface HotelDao {
    public void createHotelTable(SQLiteDatabase dbWritableConnection);
    public void addHotels(SQLiteDatabase dbWritableConnection,
                                                    Hotel[] hotels);
}
