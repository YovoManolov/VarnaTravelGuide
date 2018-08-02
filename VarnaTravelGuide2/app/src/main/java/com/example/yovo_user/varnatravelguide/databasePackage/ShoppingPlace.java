package com.example.yovo_user.varnatravelguide.databasePackage;

public class ShoppingPlace {

    private int id;
    private int placeId;
    private int priceCategoryId;

    public ShoppingPlace(int id, int placeId, int priceCategoryId) {
        this.id = id;
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

    /*public static ShoppingPlace[] populateShoppingPlaces() {
        return new ShoppingPlace[]{
                new ShoppingPlace(
                        PriceCategory.COMBINED
                ),
                new ShoppingPlace(
                        PriceCategory.MID_RANGE
                ),
                new ShoppingPlace(
                        PriceCategory.COMBINED
                ),
                new ShoppingPlace(
                        PriceCategory.COMBINED
                ),
                new ShoppingPlace(
                        PriceCategory.COMBINED
                )
        };
    }*/
}
