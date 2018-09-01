package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

public class ShoppingPlace {

    private int id;
    private int placeId;
    private int priceCategoryId;

    public ShoppingPlace(int id, int placeId, int priceCategoryId) {
        this.id = id;
        this.placeId = placeId;
        this.priceCategoryId = priceCategoryId;
    }

    public ShoppingPlace(int placeId, int priceCategoryId) {
        this.placeId = placeId;
        this.priceCategoryId = priceCategoryId;
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

    public static ShoppingPlace[] populateShoppingPlaces() {
        return new ShoppingPlace[]{
                new ShoppingPlace(6,4 ),
                new ShoppingPlace(7,2 ),
                new ShoppingPlace(8,4 ),
                new ShoppingPlace(9,4 ),
                new ShoppingPlace(10,4)
        };
    }
}
