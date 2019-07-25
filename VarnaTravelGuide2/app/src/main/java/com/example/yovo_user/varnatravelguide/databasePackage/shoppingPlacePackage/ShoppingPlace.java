package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Restaurant {
    private ObjectId _id ;
    private ObjectId place_id;
    private String cuisine;

    public Restaurant(ObjectId place_id, String cousine) {
        this.place_id = place_id;
        this.cuisine = cousine;
    }

    public Restaurant(int placeId, String cousine) {
        this.cuisine = cousine;
    }

    public Restaurant(final Document document) {
        place_id = document.getObjectId("place_id");
        cuisine = document.getString("cuisine");
    }

    public ObjectId getPlace_id() {
        return place_id;
    }

    public void setPlace_id(ObjectId place_id) {
        this.place_id = place_id;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /* public static Restaurant[] populateRestaurants() {
        return new Restaurant[]{
                new Restaurant(1, 2, "Bulgarian, European and Mediterranean cuisine."),
                new Restaurant(2, 1, "PIZZA, PASTA & WINE "),
                new Restaurant(3, 3, "\n" + "The dishes are a combination of modern cuisine" +
                                " with elements of the highest French cuisine and traditional" +
                                "Bulgarian dishes."),
                new Restaurant(4, 3, "European cuisine."),
                new Restaurant(5, 1, "\n European, american cuisine, sandwiches, cold, pancakes,"
                                + " cocktails, alcohol and beverages, draft beer," +
                                "fast food, desserts, burgers.")
        };
    }*/
}
