package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import org.bson.Document;
import org.bson.types.ObjectId;


//за сега нямам нужда от този клас понеже цялата информация за
//местата за пазаруване се пази в Places
public class ShoppingPlace {
    
    private ObjectId place_id;
    private int priceCategoryId;

    public ShoppingPlace(ObjectId place_id, int priceCategoryId) {
        this.place_id = place_id;
        this.priceCategoryId = priceCategoryId;
    }

    public ShoppingPlace(Document document) {
        place_id = document.getObjectId("place_id");
        this.priceCategoryId = priceCategoryId;
    }
}
