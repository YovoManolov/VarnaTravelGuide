package com.example.yovo_user.varnatravelguide;

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
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHours;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListingPlacesActivity extends AppCompatActivity {
    private String typeOfPlacesToLoad;
    private HashMap<Integer,PriceCategory> placePriceCategory = new HashMap<>();
    private HashMap<Integer,WorkHours> placeWorkHours = new HashMap<>();
    private HashMap<Integer,String> placeEntranceTicket = new HashMap<>();
    private ArrayList<WorkHours> workHours;
    private List<ListLinksItem> listItems = new ArrayList<>();
    private  VTGDatabase vtgDatabase = new VTGDatabase(this);
    private SQLiteDatabase dbReadableConnection = vtgDatabase.getDbReadableConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        ImageView imageViewPlacesHeaderId;
        switch(typeOfPlacesToLoad){
            case "hotels":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.hotelslabel);

                ArrayList<Hotel> allHotels =
                            (ArrayList<Hotel>)vtgDatabase.getHotelDaoImpl()
                                    .getAllHotels(dbReadableConnection);

                ArrayList<Place> hotelPlaces = new ArrayList<> ();
                for(int i = 0 ;i < allHotels.size() ; i++){
                    hotelPlaces.add(
                            vtgDatabase.getPlaceDaoImpl().getPlaceById(
                                 dbReadableConnection,allHotels.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(hotelPlaces);

                break;
            case "restaurants":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.restaurantslabel);

                ArrayList<Restaurant> allRestaurants =
                        (ArrayList<Restaurant>)vtgDatabase.getRestaurantDaoImpl()
                                .getAllResaturants(dbReadableConnection);

                ArrayList<Place> restaurantPlaces = new ArrayList<> ();
                for(int i = 0 ;i < restaurantPlaces.size() ; i++){
                    restaurantPlaces.add(
                            vtgDatabase.getPlaceDaoImpl().getPlaceById(
                                    dbReadableConnection,allRestaurants.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(restaurantPlaces);
                break;
            case "landmarks":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.landmarkslabel);

                ArrayList<Landmark> allLandmarks =
                        (ArrayList<Landmark>)vtgDatabase.getLandmarkDaoImpl()
                                .getAllLandmarks(dbReadableConnection);

                ArrayList<Place> landmarkPlaces = new ArrayList<> ();
                for(int i = 0 ;i < landmarkPlaces.size() ; i++){
                    landmarkPlaces.add(
                            vtgDatabase.getPlaceDaoImpl().getPlaceById(
                                    dbReadableConnection,allLandmarks.get(i).getPlaceId()
                            )
                    );
                }

                generateListOfPlaces(landmarkPlaces);
                break;
            case "shoppingPlaces":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.shoppinglabel);

                ArrayList<ShoppingPlace> allShoppingPlaces =
                        (ArrayList<ShoppingPlace>)vtgDatabase.getShoppingPlacesDaoImpl()
                                .getAllShoppingPlaces(dbReadableConnection);

                ArrayList<Place> shoppingPlacePlaces = new ArrayList<> ();
                for(int i = 0 ;i < shoppingPlacePlaces.size() ; i++){
                    shoppingPlacePlaces.add(
                            vtgDatabase.getPlaceDaoImpl().getPlaceById(
                                    dbReadableConnection,allShoppingPlaces.get(i).getPlaceId()
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

            Image mainImage = vtgDatabase.getImageDaoImpl().
                    getMainImageForPlace(dbReadableConnection,place.getId());

            Drawable mainImageDrowable = loadImageFromWebOperations(mainImage.getImageURL());

            listItems.add(new ListLinksItem(
                            place.getPlaceId(),
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
