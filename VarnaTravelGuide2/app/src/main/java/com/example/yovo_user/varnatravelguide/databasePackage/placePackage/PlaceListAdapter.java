package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.BsonValue;

import java.util.ArrayList;
import java.util.List;

public class PlaceListAdapter extends ArrayAdapter<Place>{

    private final RemoteMongoCollection _itemSource;
    private List<BsonValue> pendingChanges;

    public PlaceListAdapter(
            final Context context,
            final int resource,
            final List<Place> items,
            final RemoteMongoCollection itemSource
    ) {
        super(context, resource, items);
        _itemSource = itemSource;
        pendingChanges = new ArrayList<>();
    }

}
