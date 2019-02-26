package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Landmark {

    private ObjectId _id ;
    private ObjectId place_id;
    private String entranceTicket;

    public Landmark(ObjectId _id , ObjectId place_id, String entranceTicket) {
        this._id = _id;
        this.place_id = place_id;
        this.entranceTicket = entranceTicket;
    }

    public Landmark(ObjectId place_id, String entranceTicket) {
        this.place_id = place_id;
        this.entranceTicket = entranceTicket;
    }

    public Landmark(final Document document) {
        _id = document.getObjectId("_id");
        place_id = document.getObjectId("place_id");
        entranceTicket = document.getString("entranceTicket");
    }

    public String getEntranceTicket() {
        return entranceTicket;
    }

    public void setEntranceTicket(String entranceTicket) {
        this.entranceTicket = entranceTicket;
    }


/*   public static Landmark[] populateLandmarks() {
        return new Landmark[] {
               new Landmark(16,"Adults: 3.00 leva\n" +
                                                 " Children/Students: 2.00 leva" ),
                new Landmark(17,"Information not available. " ),
                new Landmark(18,"No ticket required "),
                new Landmark(19,
                        "Adults: 4.00 leva\n" +
                                "Students: 1.00 leva\n"+
                                "Organized groups: 0.50 leva \n"+
                                "Lecture: 4.00 leva"),
                new Landmark(20,"Adults:  10.00  leva\n" +
                                                     "Children/Students: 2.00 leva")
        };
    }*/
}
