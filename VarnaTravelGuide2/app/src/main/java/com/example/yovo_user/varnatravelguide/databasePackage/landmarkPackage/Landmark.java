package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import org.bson.Document;
import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Landmark {

    private String _id;
    private String place_id;
    private String entranceTicket;
    private Place place;

   /* public Landmark(String _id , String place_id, String entranceTicket) {
        this._id = _id;
        this.place_id = place_id;
        this.entranceTicket = entranceTicket;
    }

    public Landmark(String place_id, String entranceTicket) {
        this.place_id = place_id;
        this.entranceTicket = entranceTicket;
    }*/

//    public Landmark(final Document document) {
//        _id = document.getObjectId("_id");
//        place_id = document.getObjectId("place_id");
//        entranceTicket = document.getString("entranceTicket");
//    }

}
