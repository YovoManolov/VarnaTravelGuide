package com.example.yovo_user.varnatravelguide.dataPackage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {

    private String _id ;
    private String place_id;
    private int numbOfStars;
    private Place place;
}
