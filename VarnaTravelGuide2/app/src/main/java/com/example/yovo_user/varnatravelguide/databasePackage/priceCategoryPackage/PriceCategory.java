package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;


import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.annotation.Documented;

public class PriceCategory {

    private ObjectId priceCategory_id ;
    private String descr;

    public PriceCategory(){}
    public PriceCategory(String descr) {
        this.descr = descr;
    }

    public PriceCategory(Document document) {
        priceCategory_id = document.getObjectId("priceCategory_id");
        descr = document.getString("descr");
    }

    public ObjectId getPriceCategory_id() {
        return priceCategory_id;
    }

    public void setPriceCategory_id(ObjectId priceCategory_id) {
        this.priceCategory_id = priceCategory_id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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
