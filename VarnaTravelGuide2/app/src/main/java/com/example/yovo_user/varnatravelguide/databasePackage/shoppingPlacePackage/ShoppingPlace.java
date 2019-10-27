package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import org.bson.Document;
import org.bson.types.ObjectId;

public class ShoppingPlace {
    private ObjectId place_id;
    private int priceCategoryId;

    public ShoppingPlace(ObjectId place_id, int priceCategoryId) {
        this.place_id = place_id;
        this.priceCategoryId = priceCategoryId;
    }

    public ObjectId getPlace_id() {
        return place_id;
    }

    public void setPlace_id(ObjectId place_id) {
        this.place_id = place_id;
    }

    public int getPriceCategoryId() {
        return priceCategoryId;
    }

    public void setPriceCategoryId(int priceCategoryId) {
        this.priceCategoryId = priceCategoryId;
    }

     /*
    public static ShoppingPlace[] populateShoppingPlaces() {
                return new ShoppingPlace[]{
                        new ShoppingPlace(6,4 ),
        @@ -51,5 +31,5 @@ public void setPriceCategoryId(int priceCategoryId) {
                        new ShoppingPlace(9,4 ),
                        new ShoppingPlace(10,4)
                };
            }
    }*/
}
