package com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.BsonValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class PriceCategoryListAdapter extends ArrayAdapter<PriceCategory> {

    private final RemoteMongoCollection _itemSource;
    private List<BsonValue> pendingChanges;

    public PriceCategoryListAdapter(
            final Context context,
            final int resource,
            final List<PriceCategory> items,
            final RemoteMongoCollection itemSource
    ) {
        super(context, resource, items);
        _itemSource = itemSource;
        pendingChanges = new ArrayList<>();
    }

    public List<PriceCategory> getAllRestaurants(){
        List<PriceCategory> allPriceCategories = new LinkedList<>();
        for (int i = 0 ;i< super.getCount(); i++)
            allPriceCategories.add(getItem(i));
        return allPriceCategories;
    }
}
