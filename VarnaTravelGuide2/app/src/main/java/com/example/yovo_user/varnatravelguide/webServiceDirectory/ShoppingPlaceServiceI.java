package com.example.yovo_user.varnatravelguide.webServiceDirectory;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShoppingPlaceServiceI {

    String shoppingPlacePref = "/shoppingPlaces";

    @GET(shoppingPlacePref + "/getAll")
    Call<List<Place>> getAllShoppingPlaces();

    @GET(shoppingPlacePref + "/getOneById/{id}")
    Call<Place> getShoppingPlaceById(@Path("id") String shoppingPlaceId);

    @PUT(shoppingPlacePref + "/update/{id}")
    Call<Place> updateShoppingPlace(Place newShoppingPlace, @Path("id") String shoppingPlaceId);

    @POST(shoppingPlacePref + "/create")
    Call<Place> createShoppingPlace(Place newShoppingPlace);

    @DELETE(shoppingPlacePref + "/deleteById/{id}")
    Call<Object> deleteShoppingPlace(@Path("id") String shoppingPlaceId);

}