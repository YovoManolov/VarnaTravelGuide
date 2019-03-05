package com.example.yovo_user.varnatravelguide;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yovo_user.varnatravelguide.databasePackage.DBManager;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;

import org.bson.types.ObjectId;
import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class SinglePlaceInfo extends AppCompatActivity {

    private ViewPager viewPager;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_place_info);

        dbManager = new DBManager();
        dbManager.open();
        //set text font for price category label :)
        /* Typeface myCustomFont =
                Typeface.createFromAsset(getAssets(),"font/montserrat_italic.otf");*/
        /* TextView priceCategory = (TextView)findViewById(R.id.priceCategoryId);
        priceCategory.setTypeface(myCustomFont);*/

        /* databaseHelper = DatabaseHelper.getInstance(SinglePlaceInfo.this);
        dbWritableConnection = databaseHelper.getWritableDatabase();*/

        //to switch the pictures of specific place
        generateViewPager();

        Bundle bundle = getIntent().getExtras();
        ObjectId placeId = new ObjectId( bundle.getByteArray("PLACE_ID"));

        Place chosenPlace = dbManager.getPlaceDaoImpl().
                getPlaceById(placeId);

        /*
        typeOfPlace :
        1 - restaurants
        2 - hotel
        3 - shopping places
        4 - landmarks
        */
        switch(chosenPlace.getTypeOfPlace()){

            case 1:
                Restaurant restaurant = dbManager.getRestaurantDaoImpl().
                        getRestaurantByPlaceId(placeId);
                break;
            case 2:
                Hotel hotel = dbManager.getHotelDaoImpl().
                        getHotelByPlaceId(placeId);
                break;
            case 3:
                Landmark landmark = dbManager.getLandmarkDaoImpl().
                        getLandmarkByPlaceId(placeId);
                break;
            case 4:
                Place shoppingPlace = dbManager.getPlaceDaoImpl().
                        getPlaceById(placeId);
                break;
        }

        //setting the name of the activity = name of the place
        setTitle(chosenPlace.getName());
    }

    private void generateViewPager(){

        setViewPager((ViewPager) findViewById(R.id.viewPagerId));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                 new SinglePlaceInfo.MyTimerTask(viewPagerAdapter),
                3000,3000
        );
    }

    public class MyTimerTask extends TimerTask {
        private ViewPagerAdapter viewPagerAdapter;
        public MyTimerTask(ViewPagerAdapter viewPagerAdapter) {
            this.viewPagerAdapter = viewPagerAdapter;
        }

        @Override
        public void run() {
            SinglePlaceInfo.this.runOnUiThread(new Runnable() {
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
