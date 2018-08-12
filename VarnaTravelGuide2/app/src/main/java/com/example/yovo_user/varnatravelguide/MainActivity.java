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

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    final VTGDatabase vtgDatabase = new VTGDatabase(this) ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager((ViewPager) findViewById(R.id.viewPagerId));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(viewPagerAdapter),3000,3000);

        //vtgDatabase.addPlaces(Place.populatePlaces());
        ////vtgDatabase.createWorkHoursTable();
        //vtgDatabase.addWorkHours(WorkHours.populateWorkHours());
        ////vtgDatabase.createPriceCategoryTable();
        //vtgDatabase.addPriceCategory(PriceCategory.populatePriceCategories());
        //vtgDatabase.addRestaurants(Restaurant.populateRestaurants());
        //vtgDatabase.addShoppingPlaces(ShoppingPlace.populateShoppingPlaces());
        ////vtgDatabase.createHotelTable();
        //vtgDatabase.addHotels(Hotel.populateHotels());
        //vtgDatabase.addLandmarks(Landmark.populateLandmarks());
        //vtgDatabase.addImages(Image.populateImages());

    }

    public class MyTimerTask extends TimerTask {
        private ViewPagerAdapter viewPagerAdapter;
        public MyTimerTask(ViewPagerAdapter viewPagerAdapter) {
            this.viewPagerAdapter = viewPagerAdapter;
        }

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == viewPagerAdapter.getCount()-1) {
                        viewPager.setCurrentItem (0);
                    }else {
                        viewPager.setCurrentItem(
                                viewPager.getCurrentItem()+1
                        );
                    }
                }
            });
        }
    }


    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

}
