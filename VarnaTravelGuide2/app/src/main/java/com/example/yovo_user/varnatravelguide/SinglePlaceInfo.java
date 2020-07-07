package com.example.yovo_user.varnatravelguide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cocoahero.android.geojson.Position;
import com.example.yovo_user.varnatravelguide.dataPackage.models.Image;
import com.example.yovo_user.varnatravelguide.dataPackage.models.Place;
import com.example.yovo_user.varnatravelguide.webServiceDirectory.PlaceServiceI;
import com.example.yovo_user.varnatravelguide.webServiceDirectory.VTGWebServClient;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SinglePlaceInfo extends AppCompatActivity implements OnMapReadyCallback {

    private Retrofit retrofit;

    private ViewPager viewPager;
    private Place chosenPlace;
    private String placeId;

    private TextView workHoursInfo;
    private TextView contactsInfo;
    private TextView descriptionInfo;

    private PlaceServiceI placeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_place_info);

        retrofit = VTGWebServClient.getApiClient();

        placeService = retrofit.create(PlaceServiceI.class);

        Bundle bundle = getIntent().getExtras();
        placeId = new ObjectId( bundle.getByteArray("PLACE_ID")).toString();

        Call<Place> placeCall = placeService.getPlaceById(placeId);

        placeCall.enqueue(new Callback<Place>()  {
            @Override
            public void onResponse(Call<Place> call,
                                   Response<Place> response) {
                chosenPlace =  response.body();
            }

            @Override
            public void onFailure(Call<Place> call,Throwable t) {
                t.getCause();
            }
        });

        initActivity(chosenPlace);

        setTitle(chosenPlace.getName());
    }
    private void initActivity(Place chosenPlace) {


        generateViewPager();

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setPriceCategory(chosenPlace.getPriceCategoryDescription());

        setWorkHoursInfo((TextView) findViewById(R.id.WorkHoursInfoId));
        getWorkHoursInfo().setText(chosenPlace.getWorkHours().toString());

        setContactsInfo((TextView) findViewById(R.id.ContactsInfoId));
        getContactsInfo().setText(chosenPlace.getContacts());

        setDescriptionInfo((TextView) findViewById(R.id.descriptionInfoId));
        getDescriptionInfo().setText(chosenPlace.getDescription());
    }
    @Override
    public void onMapReady(GoogleMap map) {
        Position position = chosenPlace.getLocation().getPosition();
        LatLng location = new LatLng(position.getLatitude(), position.getLongitude());
        CameraPosition target = CameraPosition.builder().target(location).zoom(15).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
        map.addMarker(new MarkerOptions().position(location).title(chosenPlace.getName()));
    }
    private void setPriceCategory(String priceCategoryDescription) {
        ImageView firstCoin = (ImageView) findViewById(R.id.coint1);
        ImageView secondCoin = (ImageView) findViewById(R.id.coint2);
        ImageView thirdCoin = (ImageView) findViewById(R.id.coint3);

        switch (priceCategoryDescription){
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
        ArrayList<Image> images = chosenPlace.getImages();

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
                    if (viewPager.getCurrentItem() == viewPagerAdapter.getCount() - 1) {
                        viewPager.setCurrentItem(0);
                    } else {
                        viewPager.setCurrentItem(
                                viewPager.getCurrentItem() + 1
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
