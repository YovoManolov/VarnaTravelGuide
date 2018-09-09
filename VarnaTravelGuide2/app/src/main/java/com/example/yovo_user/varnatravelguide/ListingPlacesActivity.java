package com.example.yovo_user.varnatravelguide;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
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
    private VTGDatabase vtgDatabase;
    private SQLiteDatabase dbWritableConnection;

    private PlaceDaoImpl placeDaoImpl = new PlaceDaoImpl();
    private HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
    private LandmarkDaoImpl landmarkDaoImpl = new LandmarkDaoImpl();
    private RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl();
    private ImageDaoImpl imageDaoImpl = new ImageDaoImpl();
    private WorkHoursDaoImpl workHoursDaoImpl = new WorkHoursDaoImpl();
    private PriceCategoryDaoImpl priceCategoryDaoImpl = new PriceCategoryDaoImpl();
    private ShoppingPlacesDaoImpl shoppingPlacesDaoImpl = new ShoppingPlacesDaoImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        vtgDatabase = VTGDatabase.getInstance(this);
        dbWritableConnection = vtgDatabase.getWritableDatabase();

        placeDaoImpl.createPlacesTable(dbWritableConnection);
        priceCategoryDaoImpl.createPriceCategoryTable(dbWritableConnection);
        imageDaoImpl.createImageTable(dbWritableConnection);
        hotelDaoImpl.createHotelTable(dbWritableConnection);
        landmarkDaoImpl.createLandmarkTable(dbWritableConnection);
        restaurantDaoImpl.createRestaurantTable(dbWritableConnection);
        shoppingPlacesDaoImpl.createShoppingPlacesTable(dbWritableConnection);
        workHoursDaoImpl.createWorkHoursTable(dbWritableConnection);

        //==================================================
        placeDaoImpl.addPlaces( dbWritableConnection, Place.populatePlaces());
        priceCategoryDaoImpl.addPriceCategory(dbWritableConnection, PriceCategory.populatePriceCategories());
        imageDaoImpl.addImage(dbWritableConnection,Image.populateImages());
        hotelDaoImpl.addHotels(dbWritableConnection,Hotel.populateHotels());
        landmarkDaoImpl.addLandmarks( dbWritableConnection, Landmark.populateLandmarks());
        restaurantDaoImpl.addRestaurant( dbWritableConnection, Restaurant.populateRestaurants() );
        shoppingPlacesDaoImpl.addShoppingPlaces( dbWritableConnection,ShoppingPlace.populateShoppingPlaces() );
        workHoursDaoImpl.addWorkHours( dbWritableConnection, WorkHours.populateWorkHours() );


        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        ImageView imageViewPlacesHeaderId;
        switch(typeOfPlacesToLoad){
            case "hotels":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.hotelslabel);

                HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
                ArrayList<Hotel> allHotels = (ArrayList<Hotel>)
                        hotelDaoImpl.getAllHotels(dbWritableConnection);


                ArrayList<Place> hotelPlaces = new ArrayList<> ();
                for(int i = 0 ;i < allHotels.size() ; i++){
                    hotelPlaces.add(placeDaoImpl.getPlaceById(
                                 dbWritableConnection,allHotels.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(hotelPlaces);

                break;
            case "restaurants":


                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.restaurantslabel);


                ArrayList<Restaurant> allRestaurants =
                        (ArrayList<Restaurant>)restaurantDaoImpl
                                .getAllResaturants(dbWritableConnection);

                ArrayList<Place> restaurantPlaces = new ArrayList<> ();
                for(int i = 0 ;i < restaurantPlaces.size() ; i++){
                    restaurantPlaces.add(placeDaoImpl.getPlaceById(
                                    dbWritableConnection,allRestaurants.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(restaurantPlaces);
                break;
            case "landmarks":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.landmarkslabel);

                ArrayList<Landmark> allLandmarks =
                        (ArrayList<Landmark>)landmarkDaoImpl
                                .getAllLandmarks(dbWritableConnection);

                ArrayList<Place> landmarkPlaces = new ArrayList<> ();
                for(int i = 0 ;i < landmarkPlaces.size() ; i++){
                    landmarkPlaces.add(placeDaoImpl.getPlaceById(
                                    dbWritableConnection,allLandmarks.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(landmarkPlaces);
                break;
            case "shoppingPlaces":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.shoppinglabel);

                ArrayList<ShoppingPlace> allShoppingPlaces =
                        (ArrayList<ShoppingPlace>)shoppingPlacesDaoImpl
                                .getAllShoppingPlaces(dbWritableConnection);

                ArrayList<Place> shoppingPlacePlaces = new ArrayList<> ();
                for(int i = 0 ;i < shoppingPlacePlaces.size() ; i++){
                    shoppingPlacePlaces.add(placeDaoImpl.getPlaceById(
                                    dbWritableConnection,allShoppingPlaces.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(shoppingPlacePlaces);
                break;
        }
    }


    private void generateListOfPlaces(ArrayList<Place> placeListToLoad){

        ListView listLinksItemsFromView = (ListView) findViewById(R.id.listOfPlaces);

        for(Place place : placeListToLoad ) {

            Image mainImage = imageDaoImpl.
                    getMainImageForPlace(dbWritableConnection,place.getId());

            Drawable mainImageDrowable = loadImageFromWebOperations(mainImage.getImageURL());

            listItems.add(new ListLinksItem(
                            place.getId(),
                            place.getName(),
                            place.getDescription(),
                            mainImageDrowable));
        }

        ArrayAdapter<ListLinksItem> adapter = new CustomAdapter();
        listLinksItemsFromView.setAdapter(adapter);

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
}
