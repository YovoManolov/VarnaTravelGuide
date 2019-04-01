package com.example.yovo_user.varnatravelguide;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.databasePackage.DBManager;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;
import com.squareup.picasso.Picasso;
import com.google.android.gms.maps.MapView;


import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListingPlacesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    private String typeOfPlacesToLoad;
    private List<ListLinksItem> listItems = new ArrayList<>();
    private DBManager dbManager;
    List<Place> placeListToLoad ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        dbManager = new DBManager();
        dbManager.open();
        initActivity(typeOfPlacesToLoad);
    }

    private void initActivity(String typeOfPlacesToLoad){
        ImageView imageViewPlacesHeaderId;
        switch(typeOfPlacesToLoad){
            case "hotels":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.hotelslabel);

                List<Hotel> allHotels =
                        dbManager.getHotelDaoImpl().getAllHotels();

                List<Place> hotelPlaces = new ArrayList<> ();
                for(int i = 0 ;i < allHotels.size() ; i++){
                    hotelPlaces.add(dbManager.getPlaceDaoImpl()
                            .getPlaceById(allHotels.get(i).getplace_id())
                    );
                };

                generateListOfPlaces(hotelPlaces);
            break;

            case "restaurants":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.restaurantslabel);


                ArrayList<Restaurant> allRestaurants =
                        (ArrayList<Restaurant>)dbManager.getRestaurantDaoImpl()
                                .getAllResaturants();

                ArrayList<Place> restaurantPlaces = new ArrayList<> ();
                for(int i = 0 ;i < allRestaurants.size() ; i++){
                    restaurantPlaces.add(
                            dbManager.getPlaceDaoImpl().getPlaceById(
                                    allRestaurants.get(i).getPlace_id())
                    );
                }

                generateListOfPlaces(restaurantPlaces);

            break;

            case "landmarks":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.landmarkslabel);

                ArrayList<Landmark> allLandmarks =
                        (ArrayList<Landmark>)dbManager.getLandmarkDaoImpl()
                                .getAllLandmarks();

                ArrayList<Place> landmarkPlaces = new ArrayList<> ();
                for(int i = 0 ;i < allLandmarks.size() ; i++){
                    landmarkPlaces.add(dbManager.getPlaceDaoImpl()
                            .getPlaceById(allLandmarks.get(i).getPlace_id()   )
                    );
                }

                generateListOfPlaces(landmarkPlaces);

            break;
            case "shoppingPlaces":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.shoppinglabel);

                // typeOfPlace= 3 stands for shoppingPlaces
                ArrayList<Place> allShoppingPlaces =
                        (ArrayList<Place>)dbManager.getPlaceDaoImpl()
                                .getPlacesByTypeOfPlace(3);

                generateListOfPlaces(allShoppingPlaces);

            break;
        }
    }


    private void generateListOfPlaces(List<Place> placeListToLoad){

        ListView listLinksItemsFromView = (ListView) findViewById(R.id.listOfPlaces);
        this.placeListToLoad = placeListToLoad;

        for(Place place : this.placeListToLoad ) {

            Image mainImage = dbManager.getPlaceDaoImpl().getMainImageForPlace(place.getImages());

            String imageUrl = getResources().getString(R.string.URL_prefix)
                                                + mainImage.getImageURL();
            //Drawable mainImageDrowable = getDrawableFromURL("https://drive.google.com/uc?id=" + mainImage.getImageURL());

            listItems.add(new ListLinksItem(
                            place.get_id(),
                            place.getName(),
                            place.getDescription(),
                            imageUrl));
        }

        ArrayAdapter<ListLinksItem> adapter = new CustomAdapter();
        listLinksItemsFromView.setAdapter(adapter);
        setClickEvents(listLinksItemsFromView);

    }

    private class CustomAdapter extends ArrayAdapter<ListLinksItem> {
        public CustomAdapter() {
            super(ListingPlacesActivity.this, R.layout.list_item2, listItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = getLayoutInflater().
                        inflate(R.layout.list_item2, parent,false);
            }

            ListLinksItem currentItem = listItems.get(position);

            ImageView linkImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);

            heading.setText(currentItem.getHeading());
            desc.setText(currentItem.getDesc());

            Picasso picasso = Picasso.get();
            picasso.load(currentItem.getImageUrl()).into(linkImage);

            return convertView;
        }
    }

    private void setClickEvents( ListView listOfPlaces ){

        listOfPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                Intent intent = new Intent(ListingPlacesActivity.this,
                        SinglePlaceInfo.class);
                Bundle bundle = new Bundle();

                ListLinksItem listLinksItem = (ListLinksItem) adapter.getItemAtPosition(position);
                bundle.putByteArray("PLACE_ID",listLinksItem.getPlaceId().toByteArray());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

       /* listOfPlaces.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View v, int position,
                                   long arg3)
            {
                ListLinksItem listLinksItem = (ListLinksItem) adapter.getItemAtPosition(position);
                Place selectedPlace = dbManager.getPlaceDaoImpl()
                        .getPlaceById(listLinksItem.getPlaceId());

                LatLng ny = new LatLng(selectedPlace.getLatitude(),selectedPlace.getLongitude());
                gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
                return false;
            }
        });*/

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        for(Place p : this.placeListToLoad){
            LatLng location = new LatLng(p.getLatitude(), p.getLongitude());
            CameraPosition target = CameraPosition.builder().target(location).zoom(10).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
            googleMap.addMarker(new MarkerOptions().position(location).title(p.getName()));
        }

    }

}
