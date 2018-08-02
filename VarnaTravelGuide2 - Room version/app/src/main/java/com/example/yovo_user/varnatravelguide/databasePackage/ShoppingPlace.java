package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity(tableName = "SHOPING_PLACES",
        foreignKeys = @ForeignKey(entity = Place.class,
                parentColumns = "placeId",
                childColumns = "shoppingPlaceId"))
public class ShoppingPlace {
    @PrimaryKey
    private long shoppingPlaceId;

/*    @Embedded
    private Place place;*/

    @TypeConverters(PriceCategory.class)
    @ColumnInfo(name = "PRICE_CATEGORY")
    private PriceCategory priceCategory;

    public ShoppingPlace(long shoppingPlaceId,
                         PriceCategory priceCategory) {
        //referes to placeId in PLACE table
        this.shoppingPlaceId = shoppingPlaceId;
        this.priceCategory = priceCategory;
    }

/*

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
*/

    public long getShoppingPlaceId() {
        return shoppingPlaceId;
    }

    public void setShoppingPlaceId(long shoppingPlaceId) {
        this.shoppingPlaceId = shoppingPlaceId;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
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
