package com.example.yovo_user.varnatravelguide.databasePackage;

import android.util.Log;

import com.example.yovo_user.varnatravelguide.ListingPlacesActivity;
import com.example.yovo_user.varnatravelguide.SinglePlaceInfo;
import com.mongodb.stitch.android.core.auth.StitchAuth;
import com.mongodb.stitch.android.core.auth.StitchAuthListener;
import com.mongodb.stitch.android.core.auth.StitchUser;

import java.lang.ref.WeakReference;

import static android.support.constraint.Constraints.TAG;

public class MyAuthListener implements StitchAuthListener {

    private WeakReference<ListingPlacesActivity> listingPlacesActivityWR;
    private WeakReference<SinglePlaceInfo> singlePlaceInfoActivityWR;
    private StitchUser _user;

    public MyAuthListener(ListingPlacesActivity listingPlacesActivity) {
        listingPlacesActivityWR = new WeakReference<>(listingPlacesActivity);
    }

    public MyAuthListener(SinglePlaceInfo SinglePlaceInfoActivity) {
        singlePlaceInfoActivityWR = new WeakReference<>(SinglePlaceInfoActivity);
    }

    @Override
    public void onAuthEvent(final StitchAuth auth) {
        if (auth.isLoggedIn() && _user == null) {
            Log.d(TAG, "Logged into Stitch");
            _user = auth.getUser();
            return;
        }

        if (!auth.isLoggedIn() && _user != null) {
            _user = null;
        }
    }
}
