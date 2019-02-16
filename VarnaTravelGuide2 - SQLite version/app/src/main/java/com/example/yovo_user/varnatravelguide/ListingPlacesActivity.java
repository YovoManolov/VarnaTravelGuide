package com.example.yovo_user.varnatravelguide;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.databasePackage.DBManager;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.ImageDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.LandmarkDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.PlaceDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategoryDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.RestaurantDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlacesDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHours;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHoursDaoImpl;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListingPlacesActivity extends AppCompatActivity {

    private String typeOfPlacesToLoad;
    private List<ListLinksItem> listItems = new ArrayList<>();
    private DBManager dbManager;

  /* private PlaceDaoImpl placeDaoImpl = new PlaceDaoImpl();
    private HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
    private LandmarkDaoImpl landmarkDaoImpl = new LandmarkDaoImpl();
    private RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl();
    private ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
    private WorkHoursDaoImpl workHoursDaoImpl = new WorkHoursDaoImpl();
    private PriceCategoryDaoImpl priceCategoryDaoImpl = new PriceCategoryDaoImpl();
    private ShoppingPlacesDaoImpl shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        dbManager = new DBManager(this.getApplicationContext());
        dbManager.open();
        /*Typeface myCustomFont =
                Typeface.createFromAsset(getAssets(),"font/montserrat_italic.otf");*/
        initActivity(typeOfPlacesToLoad);
    }

    private void initActivity(String typeOfPlacesToLoad){
        ImageView imageViewPlacesHeaderId;
        switch(typeOfPlacesToLoad){
            case "hotels":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.hotelslabel);

                ArrayList<Hotel> allHotels =(ArrayList<Hotel>)
                        dbManager.getHotelDaoImpl().getAllHotels();

                ArrayList<Place> hotelPlaces = new ArrayList<> ();
                for(int i = 0 ;i < allHotels.size(); i++){
                    hotelPlaces.add(dbManager.getPlaceDaoImpl()
                            .getPlaceById(allHotels.get(i).getPlaceId())
                    );
                }

                generateListOfPlaces(hotelPlaces);
            break;

            case "restaurants":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.restaurantslabel);


                ArrayList<Restaurant> allRestaurants =
                        (ArrayList<Restaurant>)dbManager.getRestaurantDaoImpl()
                                .getAllResaturants();

                ArrayList<Place> restaurantPlaces = new ArrayList<> ();
                for(int i = 0 ;i < restaurantPlaces.size() ; i++){
                    restaurantPlaces.add(dbManager.getPlaceDaoImpl()
                            .getPlaceById(allRestaurants.get(i).getPlaceId())
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
                for(int i = 0 ;i < landmarkPlaces.size() ; i++){
                    landmarkPlaces.add(dbManager.getPlaceDaoImpl()
                            .getPlaceById(allLandmarks.get(i).getPlaceId())
                    );
                }

                generateListOfPlaces(landmarkPlaces);

            break;
            case "shoppingPlaces":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.shoppinglabel);

                ArrayList<ShoppingPlace> allShoppingPlaces =
                        (ArrayList<ShoppingPlace>)dbManager.getShoppingPlacesDaoImpl()
                                .getAllShoppingPlaces();

                ArrayList<Place> shoppingPlacePlaces = new ArrayList<> ();
                for(int i = 0 ;i < shoppingPlacePlaces.size() ; i++){
                    shoppingPlacePlaces.add(dbManager.getPlaceDaoImpl()
                            .getPlaceById(allShoppingPlaces.get(i).getPlaceId())
                    );
                }

                generateListOfPlaces(shoppingPlacePlaces);

            break;
        }
    }


    private void generateListOfPlaces(ArrayList<Place> placeListToLoad){

        ListView listLinksItemsFromView = (ListView) findViewById(R.id.listOfPlaces);

        for(Place place : placeListToLoad ) {

            Image mainImage = dbManager.getImageDaoImpl().
                    getMainImageForPlace(place.getId());

            Drawable mainImageDrowable = loadImageFromWebOperations(mainImage.getImageURL());

            listItems.add(new ListLinksItem(
                            place.getId(),
                            place.getName(),
                            place.getDescription(),
                            mainImageDrowable));
        }

        ArrayAdapter<ListLinksItem> adapter = new CustomAdapter();
        listLinksItemsFromView.setAdapter(adapter);
        setClickEvents(listLinksItemsFromView);

    }

    private class CustomAdapter extends ArrayAdapter<ListLinksItem> {
        public CustomAdapter() {
            super(ListingPlacesActivity.this, R.layout.list_item, listItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = getLayoutInflater().
                        inflate(R.layout.list_item, parent,false);
            }

            ListLinksItem currentItem = listItems.get(position);

            ImageView linkImage = (ImageView) convertView.findViewById(R.id.leftIco);
            TextView heading = (TextView) convertView.findViewById(R.id.heading);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);

            heading.setText(currentItem.getHeading());
            desc.setText(currentItem.getDesc());
            linkImage.setImageDrawable(currentItem.getImageDrawable());

            return convertView;
        }
    }

    public static Drawable loadImageFromWebOperations(String uriImageId) {
        try {
            InputStream is = (InputStream)
                    new URL("https://drive.google.com/uc?id=" + uriImageId).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
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
                Place chosenPlace = (Place) adapter.getItemAtPosition(position);
                bundle.putString("PLACE_ID",String.valueOf(chosenPlace.getId()));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
