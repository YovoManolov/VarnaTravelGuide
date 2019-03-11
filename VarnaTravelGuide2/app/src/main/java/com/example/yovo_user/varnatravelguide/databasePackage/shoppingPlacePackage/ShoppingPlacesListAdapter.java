package com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.BsonValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ShoppingPlacesListAdapter  extends ArrayAdapter<ShoppingPlace> {

    private final RemoteMongoCollection _itemSource;
    private List<BsonValue> pendingChanges;

    public ShoppingPlacesListAdapter (
            final Context context,
            final int resource,
            final List<ShoppingPlace> items,
            final RemoteMongoCollection itemSource
    ) {
        super(context, resource, items);
        _itemSource = itemSource;
        pendingChanges = new ArrayList<>();
    }

    public List<ShoppingPlace> getAllRestaurants(){
        List<ShoppingPlace> allShoppingPlaces = new LinkedList<>();
        for (int i = 0 ;i< super.getCount(); i++)
            allShoppingPlaces.add(getItem(i));
        return allShoppingPlaces;
    }
}
