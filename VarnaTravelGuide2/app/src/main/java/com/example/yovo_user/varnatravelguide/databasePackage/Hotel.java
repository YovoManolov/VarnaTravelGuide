package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity(tableName = "HOTELS",
        foreignKeys = @ForeignKey(entity = Place.class,
                      parentColumns = "placeId",
                      childColumns = "hotelId"))
public class Hotel {
    @PrimaryKey
    private long hotelId ;

    /*
    @Embedded
    private Place place ;
    */

    @ColumnInfo(name = "NUMB_OF_STARS")
    private Integer numbOfStars;

    @TypeConverters(PriceCategory.class)
    @ColumnInfo(name = "PRICE_CATEGORY")
    private PriceCategory priceCategory;

    public Hotel(        long hotelId,
                         Integer numbOfStars,
                         PriceCategory priceCategory
                         ) {
        this.hotelId = hotelId;
        this.numbOfStars = numbOfStars;
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

    public Integer getNumbOfStars() {
        return numbOfStars;
    }

    //TODO make a validation for min and maks numb of stars
    public void setNumbOfStars(Integer numbOfStars) {
        this.numbOfStars = numbOfStars;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

   /* public static Hotel[] populateHotels() {
        return new Hotel[] {
                //43.199070, 27.919569
                //LL - landline phone abbriviation


                new Hotel(,2,
                        PriceCategory.BUDGET
                ),

                new Hotel(2, PriceCategory.BUDGET
                ),
                new Hotel(3, PriceCategory.MID_RANGE
                ),
                new Hotel(,3, PriceCategory.MID_RANGE
                ),
                new Hotel(4,  PriceCategory.MID_RANGE
                ),

                new Hotel(5, PriceCategory.PREMIUM
                )
        };
    }*/
}
