package com.example.yovo_user.varnatravelguide;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.Hotel;
import com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage.HotelDaoImpl;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;
import com.example.yovo_user.varnatravelguide.databasePackage.priceCategoryPackage.PriceCategory;
import com.example.yovo_user.varnatravelguide.databasePackage.restaurantPackage.Restaurant;
import com.example.yovo_user.varnatravelguide.databasePackage.shoppingPlacePackage.ShoppingPlace;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHours;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHoursDao;

import java.util.ArrayList;
import java.util.HashMap;

public class ListingPlacesActivity extends AppCompatActivity {
    private String typeOfPlacesToLoad;
    private HashMap<Integer,PriceCategory> placePriceCategory = new HashMap<>();
    private HashMap<Integer,WorkHours> placeWorkHours = new HashMap<>();
    private ArrayList<WorkHours> workHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        VTGDatabase vtgDatabase = VTGDatabase.getInstance(this.getApplicationContext());
        SQLiteDatabase dbReadableConnection = vtgDatabase.getDbReadableConnection();

        switch(typeOfPlacesToLoad){
            case "hotels":
                ArrayList<Hotel> allHotels =
                            (ArrayList<Hotel>)vtgDatabase.getHotelDaoImpl()
                                    .getAllHotels(dbReadableConnection);
                for(int i = 0 ;i < allHotels.size() ; i++){
                    PriceCategory priceCategory = vtgDatabase.getPriceCategoryDaoImpl().
                            getPriceCategoryById(dbReadableConnection,allHotels.get(i).getPlaceId());

                    placePriceCategory.put(allHotels.get(i).getPlaceId(),priceCategory);

                    workHours = (ArrayList<WorkHours>)vtgDatabase.getWorkHoursDaoImpl().
                                getWorkHoursByPlaceId(dbReadableConnection,allHotels.get(i).getPlaceId());
                    placeWorkHours.put(allHotels.get(i).getPlaceId(),workHours.get(0));
                }
                break;
            case "restaurants":
                ArrayList<Restaurant> allRestaurants =
                        (ArrayList<Restaurant>)vtgDatabase.getRestaurantDaoImpl().getAllResaturants(dbReadableConnection);

                for(int i = 0 ;i < allRestaurants.size() ; i++){
                    PriceCategory priceCategory = vtgDatabase.getPriceCategoryDaoImpl().
                            getPriceCategoryById(dbReadableConnection,allRestaurants.get(i).getPlaceId());

                    placePriceCategory.put(allRestaurants.get(i).getPlaceId(),priceCategory);

                    workHours = (ArrayList<WorkHours>)vtgDatabase.getWorkHoursDaoImpl().
                            getWorkHoursByPlaceId(dbReadableConnection,allRestaurants.get(i).getPlaceId());
                    placeWorkHours.put(allRestaurants.get(i).getPlaceId(),workHours.get(0));
                }
                break;
            case "landmarks":
                ArrayList<Landmark> allLandamarks =
                        (ArrayList<Landmark>)vtgDatabase.getLandmarkDaoImpl().getAllLandmarks(dbReadableConnection);

                for(int i = 0 ;i < allLandamarks.size() ; i++){
                    //PriceCategory priceCategory = vtgDatabase.getPriceCategoryDaoImpl().
                       //     getPriceCategoryById(dbReadableConnection,allLandamarks.get(i).getPlaceId());

                    //landmarks does not have price category
                    //placePriceCategory.put(allHotels.get(i).getPlaceId(),priceCategory);

                    workHours = (ArrayList<WorkHours>)vtgDatabase.getWorkHoursDaoImpl().
                            getWorkHoursByPlaceId(dbReadableConnection,allLandamarks.get(i).getPlaceId());
                    placeWorkHours.put(allLandamarks.get(i).getPlaceId(),workHours.get(0));
                }
                break;
            case "shoppingPlaces":
                ArrayList<ShoppingPlace> allShoppingPlaces =
                        (ArrayList<ShoppingPlace>)vtgDatabase.getShoppingPlacesDaoImpl().getAllShoppingPlaces(dbReadableConnection);

                for(int i = 0 ;i < allShoppingPlaces.size() ; i++){
                    PriceCategory priceCategory = vtgDatabase.getPriceCategoryDaoImpl().
                            getPriceCategoryById(dbReadableConnection,allShoppingPlaces.get(i).getPlaceId());

                    placePriceCategory.put(allShoppingPlaces.get(i).getPlaceId(),priceCategory);

                    workHours = (ArrayList<WorkHours>)vtgDatabase.getWorkHoursDaoImpl().
                            getWorkHoursByPlaceId(dbReadableConnection,allShoppingPlaces.get(i).getPlaceId());
                    placeWorkHours.put(allShoppingPlaces.get(i).getPlaceId(),workHours.get(0));
                }
                break;
        }

    }

}
