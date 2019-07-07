package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import org.bson.types.ObjectId;

import java.util.List;

public interface RestaurantDao {
/*    public void createRestaurantTable();
    public void addRestaurant(Restaurant[] restaurants);*/
    public List<Restaurant> getAllResaturants() throws InterruptedException;
    public Restaurant getRestaurantByPlaceId(ObjectId place_id);
}
