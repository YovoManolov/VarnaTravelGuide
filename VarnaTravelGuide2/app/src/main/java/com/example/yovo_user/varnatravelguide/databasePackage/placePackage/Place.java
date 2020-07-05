package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;


import com.cocoahero.android.geojson.Point;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage.WorkHours;

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

}
