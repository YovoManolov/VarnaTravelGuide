package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

public interface PriceCategoryDao {
    public void createPriceCategoryTable();
    public void addPriceCategory(PriceCategory[] priceCategories);
    public PriceCategory getPriceCategoryById(int placeCategoryId);
}
