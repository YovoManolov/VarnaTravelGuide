package com.example.yovo_user.varnatravelguide.databasePackage;


import java.io.Serializable;

public class PriceCategory {

    private int id ;
    private String priceType;

    public PriceCategory(String priceType) {
        this.priceType = priceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public static PriceCategory[] populatePriceCategories() {
        return new PriceCategory[]{
                new PriceCategory("BUDGET"),
                new PriceCategory("MID_RANGE"),
                new PriceCategory("PREMIUM"),
                new PriceCategory("COMBINED")
        };
    }
}
