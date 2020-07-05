package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import org.bson.Document;
import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Hotel {

    private String _id ;
    private String place_id;
    private int numbOfStars;
    private Place place;
}
