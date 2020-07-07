package com.example.yovo_user.varnatravelguide;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.cocoahero.android.geojson.Position;
import com.example.yovo_user.varnatravelguide.dataPackage.models.Image;
import com.example.yovo_user.varnatravelguide.dataPackage.models.Place;
import com.example.yovo_user.varnatravelguide.webServiceDirectory.PlaceServiceI;
import com.example.yovo_user.varnatravelguide.webServiceDirectory.VTGWebServClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListingPlacesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    private Retrofit retrofit;
    private PlaceServiceI placeService;
    private Call<List<Place>> listOfPlacesCall;

    private String typeOfPlacesToLoad;
    private List<ListLinksItem> listItems = new ArrayList<>();
    List<Place> placeListToLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_places);

        retrofit = VTGWebServClient.getApiClient();
        placeService = retrofit.create(PlaceServiceI.class);
        listOfPlacesCall = placeService.getAllPlaces();

        Bundle bundle = getIntent().getExtras();
        typeOfPlacesToLoad = bundle.getString("TYPE_OF_PLACES");

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        showInputMethod();

        initActivity(typeOfPlacesToLoad);
    }

    public void buttonClick(View view){
        switch (view.getId()) {
            case R.id.searchButtonId:

                Intent intent = this.getIntent();
                Bundle bundle = new Bundle();

                EditText textToSearch = (EditText)findViewById(R.id.textToSearchId);
                String nameToSearchWith = textToSearch.getText().toString();

                bundle.putString("TYPE_OF_PLACES",typeOfPlacesToLoad);
                bundle.putString("nameToSearchWith",nameToSearchWith.isEmpty() ? "": nameToSearchWith);
                intent.putExtras(bundle);

                this.startActivity(intent);
                break;
        }

    }

    public void showInputMethod() {
       InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
       imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
   }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void initActivity(String typeOfPlacesToLoad){

        ImageView imageViewPlacesHeaderId;
        switch(typeOfPlacesToLoad){
            case "hotels":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.hotelslabel);

                listOfPlacesCall.enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call,
                                           Response<List<Place>> response) {
                        generateListOfPlaces(
                                response.body().stream()
                                        .filter(place -> place.getTypeOfPlace() == 2 ).collect(Collectors.toList())
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Place>> call,Throwable t) {
                        t.getCause();
                    }
                });
            break;

            case "restaurants":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.restaurantslabel);


                listOfPlacesCall.enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call,
                                           Response<List<Place>> response) {
                        generateListOfPlaces(
                                response.body().stream()
                                        .filter(place -> place.getTypeOfPlace() == 1 ).collect(Collectors.toList())
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Place>> call,Throwable t) {
                        t.getCause();
                    }
                });
            break;

            case "landmarks":

                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.landmarkslabel);

                listOfPlacesCall.enqueue(new Callback<List<Place>>() {
                   @Override
                    public void onResponse(Call<List<Place>> call,
                                           Response<List<Place>> response) {
                        generateListOfPlaces(
                                response.body().stream()
                                        .filter(place -> place.getTypeOfPlace() == 4 ).collect(Collectors.toList())
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Place>> call,Throwable t) {
                        t.getCause();
                    }
                });

            break;
            case "shoppingPlaces":
                imageViewPlacesHeaderId = (ImageView)findViewById(R.id.imageViewPlacesHeaderId);
                imageViewPlacesHeaderId.setImageResource(R.drawable.shoppinglabel);

                listOfPlacesCall.enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call,
                                            Response<List<Place>> response) {
                        generateListOfPlaces(
                                response.body().stream()
                                        .filter(place -> place.getTypeOfPlace() == 3 ).collect(Collectors.toList())
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Place>> call,Throwable t) {
                        t.getCause();
                    }
                });

            break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Place> filterPlacesByString(List<Place> placeListToLoad){

        Bundle bundle = getIntent().getExtras();
        String nameToSearchWith = bundle.getString("nameToSearchWith");

        if(nameToSearchWith == null || nameToSearchWith.isEmpty()){
            return placeListToLoad;
        }else{

            return placeListToLoad.stream()
                    .filter(place->place.getName().toLowerCase().contains(nameToSearchWith.toLowerCase())
            ).collect(Collectors.toList());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void generateListOfPlaces(List<Place> placeListToLoad){

        ListView listLinksItemsFromView = (ListView) findViewById(R.id.listOfPlaces);
        this.placeListToLoad = filterPlacesByString(placeListToLoad);

        for(Place place : this.placeListToLoad ) {

            Image mainImage = place.getImages().stream()
                    .filter(image -> image.getMainImage() == 1).findFirst().get();

            String imageUrl = getResources().getString(R.string.URL_prefix)
                                                + mainImage.getImageURL();

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
                bundle.putString("PLACE_ID",listLinksItem.getPlaceId());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        for(Place p : this.placeListToLoad){
            Position position = p.getLocation().getPosition();
            LatLng location = new LatLng(position.getLatitude(), position.getLongitude());
            CameraPosition target = CameraPosition.builder().target(location).zoom(10).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
            googleMap.addMarker(new MarkerOptions().position(location).title(p.getName()));
        }

    }

}
