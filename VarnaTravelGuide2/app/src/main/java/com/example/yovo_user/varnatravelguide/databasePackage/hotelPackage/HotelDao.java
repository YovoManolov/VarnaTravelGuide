package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;

public interface HotelDao {
    /*public void createHotelTable();*/
    /*public void addHotels(Hotel[] hotels);*/
    public List<Hotel> getAllHotels() throws InterruptedException;
    public Hotel getHotelByPlaceId(Object placeId);
    //public Hotel getHotelByPlaceId(Integer placeId);
}
