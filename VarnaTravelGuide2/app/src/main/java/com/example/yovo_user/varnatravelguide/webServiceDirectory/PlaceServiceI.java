package com.example.yovo_user.varnatravelguide.webServiceDirectory;

import com.example.yovo_user.varnatravelguide.dataPackage.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceServiceI {

    String placesPref = "places";

    @GET(placesPref + "/getAll")
    Call<List<Place>> getAllPlaces();

    @GET(placesPref + "/getOneById/{id}")
    Call<Place> getPlaceById(@Path("id") String placeId);
}
