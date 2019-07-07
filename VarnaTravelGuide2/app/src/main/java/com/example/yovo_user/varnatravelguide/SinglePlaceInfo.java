package com.example.yovo_user.varnatravelguide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.databasePackage.DBManager;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategoryDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SinglePlaceInfo extends AppCompatActivity implements OnMapReadyCallback {

    private ViewPager viewPager;
    private DBManager dbManager;
    private Place chosenPlace;
    private ObjectId placeId;

    private TextView workHoursInfo;
    private TextView contactsInfo;
    private TextView descriptionInfo;

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

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        PriceCategoryDaoImpl priceCategoryDao = dbManager.getPriceCategoryDaoImpl();
        PriceCategory priceCategory = priceCategoryDao.
                getPriceCategoryById(chosenPlace.getPriceCategoryId());
        setPriceCategory(priceCategory);

        setWorkHoursInfo((TextView) findViewById(R.id.WorkHoursInfoId));
        getWorkHoursInfo().setText(chosenPlace.getWorkHours().toString());

        setContactsInfo((TextView) findViewById(R.id.ContactsInfoId));
        getContactsInfo().setText(chosenPlace.getContacts());

        setDescriptionInfo((TextView) findViewById(R.id.descriptionInfoId));
        getDescriptionInfo().setText(chosenPlace.getDescription());



    }
    @Override
    public void onMapReady(GoogleMap map) {
        LatLng location = new LatLng(chosenPlace.getLatitude(), chosenPlace.getLongitude());
        CameraPosition target = CameraPosition.builder().target(location).zoom(15).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
        map.addMarker(new MarkerOptions().position(location).title(chosenPlace.getName()));
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
    public TextView getDescriptionInfo() {
        return descriptionInfo;
    }
    public void setDescriptionInfo(TextView descriptionInfo) {
        this.descriptionInfo = descriptionInfo;
    }

}
