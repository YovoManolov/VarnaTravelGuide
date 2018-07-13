package com.example.yovo_user.varnatravelguide;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "SHOPING_PLACES")
public class ShoppingPlace {

    @PrimaryKey
    private long id ;

    @Embedded
    private Place place ;

    @ColumnInfo(name = "priceCategory")
    private int priceCategory;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(int priceCategory) {
        this.priceCategory = priceCategory;
    }
}
