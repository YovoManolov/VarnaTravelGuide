package com.example.yovo_user.varnatravelguide.databasePackage;

public class Hotel {

    private int id ;
    private int placeId;
    private int numbOfStars;
    private int priceCategoryId;

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
