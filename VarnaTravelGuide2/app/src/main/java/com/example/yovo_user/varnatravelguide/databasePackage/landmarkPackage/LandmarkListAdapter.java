package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;

import org.bson.BsonValue;

import java.util.ArrayList;
import java.util.List;

class LandmarkListAdapter extends ArrayAdapter<Landmark> {

    private final RemoteMongoCollection _itemSource;
    private List<BsonValue> pendingChanges;

    public LandmarkListAdapter(
            final Context context,
            final int resource,
            final List<Landmark> items,
            final RemoteMongoCollection itemSource
    ) {
        super(context, resource, items);
        _itemSource = itemSource;
        pendingChanges = new ArrayList<>();
    }
}
