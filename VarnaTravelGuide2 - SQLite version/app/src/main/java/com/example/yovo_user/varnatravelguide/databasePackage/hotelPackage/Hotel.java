package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

public class Hotel {

    private int id ;
    private int placeId;
    private int numbOfStars;
    private int priceCategoryId;

    public Hotel(int id, int placeId, int numbOfStars,
                 int priceCategoryId) {
        this.id = id;
        this.placeId = placeId;
        this.numbOfStars = numbOfStars;
        this.priceCategoryId = priceCategoryId;
    }

    public Hotel(int placeId, int numbOfStars,
                    int priceCategoryId) {
        this.placeId = placeId;
        this.numbOfStars = numbOfStars;
        this.priceCategoryId = priceCategoryId;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", placeId=" + placeId +
                ", numbOfStars=" + numbOfStars +
                ", priceCategoryId=" + priceCategoryId +
                '}';
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

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public int getNumbOfStars() {
        return numbOfStars;
    }

    public void setNumbOfStars(int numbOfStars) {
        this.numbOfStars = numbOfStars;
    }

    public int getPriceCategoryId() {
        return priceCategoryId;
    }

    public void setPriceCategoryId(int priceCategoryId) {
        this.priceCategoryId = priceCategoryId;
    }

    public static Hotel[] populateHotels() {
        return new Hotel[] {
                //43.199070, 27.919569
                //LL - landline phone abbriviation

                new Hotel(11,2,1),
                new Hotel(12,3, 2),
                new Hotel(13,3, 2),
                new Hotel(14,4, 2),
                new Hotel(15,5, 3)
        };
    }
}
