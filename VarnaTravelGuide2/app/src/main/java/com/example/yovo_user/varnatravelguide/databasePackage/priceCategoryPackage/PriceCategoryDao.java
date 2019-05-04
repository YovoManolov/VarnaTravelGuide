package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

import org.bson.Document;

import java.util.ArrayList;

public interface PriceCategoryDao {
  /*  public void createPriceCategoryTable();
    public void addPriceCategory(PriceCategory[] priceCategories);*/
   public PriceCategory getPriceCategoryById(int priceCategoryId);
}
