package com.example.yovo_user.varnatravelguide.databasePackage;


import java.io.Serializable;

public class PriceCategory {
    /* BUDGET(0),
       MID_RANGE(1),
       PREMIUM(2),
       COMBINED(3);
    */
    private int id ;
    private String priceType;

    public PriceCategory(int id, String priceType) {
        this.id = id;
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
}
