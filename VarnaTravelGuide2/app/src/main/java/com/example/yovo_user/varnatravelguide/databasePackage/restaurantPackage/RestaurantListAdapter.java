package com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.BsonValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private final RemoteMongoCollection _itemSource;
    private List<BsonValue> pendingChanges;

    public RestaurantListAdapter(
            final Context context,
            final int resource,
            final List<Restaurant> items,
            final RemoteMongoCollection itemSource
    ) {
        super(context, resource, items);
        _itemSource = itemSource;
        pendingChanges = new ArrayList<>();
    }

    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> allRestaurants = new LinkedList<>();
        for (int i = 0 ;i< super.getCount(); i++)
            allRestaurants.add(getItem(i));
        return allRestaurants;
    }
}
