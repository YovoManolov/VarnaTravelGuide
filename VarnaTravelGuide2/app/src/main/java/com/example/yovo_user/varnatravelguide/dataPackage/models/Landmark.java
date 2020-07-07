package com.example.yovo_user.varnatravelguide.dataPackage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Landmark {

    private String _id;
    private String place_id;
    private String entranceTicket;
    private Place place;

}
