package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;


import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.annotation.Documented;

public class PriceCategory {
    private ObjectId _id;
    private Integer priceCategory_id ;
    private String descr;

    public PriceCategory(){}
    public PriceCategory(String descr) {
        this.descr = descr;
    }

    public PriceCategory(Document document) {
        _id = document.getObjectId("_id");
        priceCategory_id = document.getInteger("priceCategory_id");
        descr = document.getString("descr");
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Integer getPriceCategory_id() {
        return priceCategory_id;
    }

    public void setPriceCategory_id(Integer priceCategory_id) {
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
