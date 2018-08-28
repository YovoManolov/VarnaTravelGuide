package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface HotelDao {
    public void createHotelTable(SQLiteDatabase dbWritableConnection);
    public void addHotels(SQLiteDatabase dbWritableConnection,
                                                    Hotel[] hotels);
    public List<Hotel> getAllHotels(SQLiteDatabase dbReadableConnection);
}
