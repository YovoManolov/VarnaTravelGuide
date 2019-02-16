package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;

public interface HotelDao {
    public void createHotelTable();
    public void addHotels(Hotel[] hotels);
    public List<Hotel> getAllHotels();
    public Hotel getHotelByPlaceId(Integer placeId);
}
