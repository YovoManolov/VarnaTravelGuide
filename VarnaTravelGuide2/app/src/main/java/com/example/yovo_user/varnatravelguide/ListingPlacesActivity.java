package com.example.yovo_user.varnatravelguide;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;

public class ListingPlacesActivity extends AppCompatActivity {
    private String typeOfPlacesToLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        VTGDatabase vtgDatabase = VTGDatabase.getInstance(this.getApplicationContext());

        switch(typeOfPlacesToLoad){
            case "hotels":
                vtgDatabase.getHotelDaoImpl().
                break;
            case "restaurants":
                break;
            case "landmarks":
                break;
            case "shoppingPlaces":
                break;

        }

    }
}
