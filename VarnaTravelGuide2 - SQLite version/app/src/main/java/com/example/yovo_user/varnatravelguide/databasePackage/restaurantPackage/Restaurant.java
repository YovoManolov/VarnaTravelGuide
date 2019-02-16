package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

public class Restaurant {
    private int id ;
    private int placeId;
    private int priceCategoryId;
    private String cousine;

    public Restaurant(int id, int placeId, int priceCategoryId, String cousine) {
        this.id = id;
        this.placeId = placeId;
        this.priceCategoryId = priceCategoryId;
        this.cousine = cousine;
    }

    public Restaurant(int placeId, int priceCategoryId, String cousine) {
        this.placeId = placeId;
        this.priceCategoryId = priceCategoryId;
        this.cousine = cousine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getPriceCategoryId() {
        return priceCategoryId;
    }

    public void setPriceCategoryId(int priceCategoryId) {
        this.priceCategoryId = priceCategoryId;
    }

    public String getCousine() {
        return cousine;
    }

    public void setCousine(String cousine) {
        this.cousine = cousine;
    }


    public static Restaurant[] populateRestaurants() {
        return new Restaurant[]{
                new Restaurant(1, 2,
                        "Bulgarian, European and Mediterranean cuisine."),
                new Restaurant(2, 1,
                        "Cuisine: PIZZA, PASTA & WINE "),
                new Restaurant(3, 3,
                        "\n" +
                                " Cuisine: The dishes are a combination of modern cuisine" +
                                " with elements of the highest French cuisine and traditional" +
                                "Bulgarian dishes."),
                new Restaurant(4, 3, "European cuisine."),
                new Restaurant(5, 1,
                        "\n Cousine: european, american cuisine, sandwiches, cold, pancakes,"
                                + " cocktails, alcohol and beverages, draft beer," +
                                "fast food, desserts, burgers.")
        };
    }
}
