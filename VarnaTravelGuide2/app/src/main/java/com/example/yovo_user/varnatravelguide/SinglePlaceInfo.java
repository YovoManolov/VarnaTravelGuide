package com.example.yovo_user.varnatravelguide;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.databasePackage.DBManager;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategoryDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHoursDaoImpl;
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

    private TextView workHoursInfo;
    private TextView contactsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_place_info);

        dbManager = new DBManager();
        dbManager.open();

        Bundle bundle = getIntent().getExtras();
        this.placeId = new ObjectId( bundle.getByteArray("PLACE_ID"));
        chosenPlace = dbManager.getPlaceDaoImpl().getPlaceById(placeId);
        initActivity(chosenPlace);

        setTitle(chosenPlace.getName());

        //to switch the pictures of specific place


        //setting the name of the activity = name of the place

    }

    private void initActivity(Place chosenPlace) {
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

        generateViewPager();
        setWorkHoursInfo((TextView) findViewById(R.id.WorkHoursInfoId));
        getWorkHoursInfo().setText(chosenPlace.getWorkHours().toString());

        setContactsInfo((TextView) findViewById(R.id.ContactsInfoId));
        getWorkHoursInfo().setText(chosenPlace.getContacts());

        PriceCategoryDaoImpl priceCategoryDao = dbManager.getPriceCategoryDaoImpl();
        PriceCategory priceCategory = priceCategoryDao.
                getPriceCategoryById(chosenPlace.getPriceCategoryId());

        setPriceCategory(priceCategory);

    }

    private void setPriceCategory(PriceCategory priceCategory) {
        ImageView firstCoin = (ImageView) findViewById(R.id.coint1);
        ImageView secondCoin = (ImageView) findViewById(R.id.coint2);
        ImageView thirdCoin = (ImageView) findViewById(R.id.coint3);

        switch (priceCategory.getDescription()){
            case "BUDGET":
                firstCoin.setVisibility(View.INVISIBLE);
                secondCoin.setVisibility(View.INVISIBLE);
                break;
            case "MID_RANGE":
                firstCoin.setVisibility(View.INVISIBLE);
                break;
            case "PREMIUM":
                break;
            case "COMBINED":
                firstCoin.setVisibility(View.INVISIBLE);
                secondCoin.setVisibility(View.INVISIBLE);
                thirdCoin.setVisibility(View.INVISIBLE);
                break;
        }

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
    public TextView getWorkHoursInfo() {
        return workHoursInfo;
    }

    public void setWorkHoursInfo(TextView workHoursInfo) {
        this.workHoursInfo = workHoursInfo;
    }

    public TextView getContactsInfo() {
        return contactsInfo;
    }

    public void setContactsInfo(TextView contactsInfo) {
        this.contactsInfo = contactsInfo;
    }

}
