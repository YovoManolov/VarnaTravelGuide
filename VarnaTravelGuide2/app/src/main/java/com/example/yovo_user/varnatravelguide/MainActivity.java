package com.example.yovo_user.varnatravelguide;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

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
    private GridLayout mainLinksGridL;
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

        mainLinksGridL = (GridLayout) findViewById(R.id.mainLinksGridL);
        //seting event
        setClickEvents(mainLinksGridL);

        //DATABASE SCRIPTS :)

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

    private void setClickEvents(GridLayout mainLinksGridL){
        CardView hotelsCV = (CardView)findViewById(R.id.hotelsCV_id);
        hotelsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for switching to another view !!!
            }
        });

        CardView restaurantsCV = (CardView)findViewById(R.id.restaurantsCV_id);
        hotelsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView landmarksCV = (CardView)findViewById(R.id.landmarksCV_id);
        hotelsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView shoppingPlacesCV = (CardView)findViewById(R.id.shopping_placesCV_id);
        hotelsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



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


    public GridLayout getMainLinksGridL() {
        return mainLinksGridL;
    }

    public void setMainLinksGridL(GridLayout mainLinksGridL) {
        this.mainLinksGridL = mainLinksGridL;
    }
}
