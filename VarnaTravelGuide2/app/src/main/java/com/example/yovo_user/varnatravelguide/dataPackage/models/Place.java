package com.example.yovo_user.varnatravelguide.dataPackage.models;


import com.cocoahero.android.geojson.Point;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Place {

    private String _id;
    private String name;
    private String address;
    private Point location;
    private String contacts;
    private String description;
    private int typeOfPlace;
    private ArrayList<Image> images;
    private WorkHours workHours;
    private String priceCategoryDescription;

}
