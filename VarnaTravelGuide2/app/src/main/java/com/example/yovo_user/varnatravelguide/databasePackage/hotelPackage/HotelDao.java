package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import java.util.List;

public interface HotelDao {
    public List<Hotel> getAllHotels() throws InterruptedException;
    public Hotel getHotelByPlaceId(Object placeId);
}
