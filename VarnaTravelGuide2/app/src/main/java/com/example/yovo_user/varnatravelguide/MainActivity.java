package com.example.yovo_user.varnatravelguide;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yovo_user.varnatravelguide.databasePackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.ShoppingPlace;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
import com.example.yovo_user.varnatravelguide.databasePackage.WorkHours;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    final VTGDatabase vtgDatabase = new VTGDatabase(this) ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vtgDatabase.addImages(Image.populateImages());

        //vtgDatabase.createWorkHoursTable();
        //vtgDatabase.addWorkHours(WorkHours.populateWorkHours());

        //vtgDatabase.addPlaces(Place.populatePlaces());

        //vtgDatabase.createPriceCategoryTable();
        //vtgDatabase.addPriceCategory(PriceCategory.populatePriceCategories());

        //vtgDatabase.addRestaurants(Restaurant.populateRestaurants());
        //vtgDatabase.addShoppingPlaces(ShoppingPlace.populateShoppingPlaces());

        //vtgDatabase.createHotelTable();
        //vtgDatabase.addHotels(Hotel.populateHotels());
        //vtgDatabase.addLandmarks(Landmark.populateLandmarks());

        setViewPager((ViewPager) findViewById(R.id.viewPagerId));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
    }


    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

}
