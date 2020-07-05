package com.example.yovo_user.varnatravelguide.webServiceDirectory;

import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestaurantServiceI {

    String restaurantsPref = "/restaurants";

    @GET(restaurantsPref + "/getAll")
    Call<List<Restaurant>> getAllRestaurants();

    @GET(restaurantsPref + "/getOneById/{id}")
    Call<Restaurant> getRestaurantById(@Path("id") String restaurantId);

    @PUT(restaurantsPref + "/update/{id}")
    Call<Restaurant> updateHotel(Restaurant newRestaurant,
                                 @Path("id") String restaurantId);

    @POST(restaurantsPref + "/create")
    Call<Restaurant> createRestaurant(Restaurant restaurant);

    @DELETE(restaurantsPref + "/deleteById/{id}")
    Call<Object> deleteRestaurant(@Path("id") String restaurantId);

}