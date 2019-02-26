package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;


import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.BsonValue;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

//for MongoDb
public class HotelListAdapter  extends ArrayAdapter<Hotel> {

    private final RemoteMongoCollection _itemSource;
    private List<BsonValue> pendingChanges;

    public HotelListAdapter(
            final Context context,
            final int resource,
            final List<Hotel> items,
            final RemoteMongoCollection itemSource
    ) {
        super(context, resource, items);
        _itemSource = itemSource;
        pendingChanges = new ArrayList<>();
    }
}
