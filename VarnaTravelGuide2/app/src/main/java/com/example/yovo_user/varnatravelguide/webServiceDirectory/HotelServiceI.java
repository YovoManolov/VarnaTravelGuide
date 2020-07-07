package com.example.yovo_user.varnatravelguide.webServiceDirectory;
import com.example.yovo_user.varnatravelguide.dataPackage.models.Hotel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HotelServiceI {

    String hotelPref = "hotels";

    @GET(hotelPref + "/getAll")
    Call<List<Hotel>> getAllHotels();

    @GET(hotelPref + "/getOneById/{id}")
    Call<Hotel> getHotelById(@Path("id") String hotelId);

    @PUT(hotelPref + "/update/{id}")
    Call<Hotel> updateHotel(Hotel newHotel, @Path("id") String hotelId);

    @POST(hotelPref + "/create")
    Call<Hotel> createHotel(Hotel newHotel);

    @DELETE(hotelPref + "/deleteById/{id}")
    Call<Object> deleteHotel(@Path("id") String hotelId);

}