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
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;

import org.bson.types.ObjectId;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SinglePlaceInfo extends AppCompatActivity {

    private ViewPager viewPager;
    private DBManager dbManager;
    private Place chosenPlace;
    private ObjectId placeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_place_info);

        dbManager = new DBManager();
        dbManager.open();

        Bundle bundle = getIntent().getExtras();
        this.placeId = new ObjectId( bundle.getByteArray("PLACE_ID"));

        chosenPlace = dbManager.getPlaceDaoImpl().getPlaceById(placeId);

        generateViewPager();
        //to switch the pictures of specific place

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
        ArrayList<Image> images = new ArrayList<Image>();
        images = dbManager.getPlaceDaoImpl().getPlaceById(placeId).getImages();

        setViewPager((ViewPager) findViewById(R.id.viewPagerId));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,images);
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
