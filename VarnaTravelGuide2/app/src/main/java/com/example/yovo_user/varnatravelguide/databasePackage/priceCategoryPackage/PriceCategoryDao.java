package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

public interface PriceCategoryDao {
    public void createPriceCategoryTable(SQLiteDatabase dbWritableConnection);
    public void addPriceCategory(SQLiteDatabase dbWritableConnection, PriceCategory[] priceCategories);
    public PriceCategory getPriceCategoryById(int placeCategoryId);
}
