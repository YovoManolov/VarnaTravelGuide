package com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

public class Landmark {

    private int id ;
    private int placeId;
    private String entranceTicket;

    public Landmark(int id, int placeId, String entranceTicket) {
        this.id = id;
        this.placeId = placeId;
        this.entranceTicket = entranceTicket;
    }

    public Landmark(int placeId, String entranceTicket) {
        this.placeId = placeId;
        this.entranceTicket = entranceTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntranceTicket() {
        return entranceTicket;
    }

    public void setEntranceTicket(String entranceTicket) {
        this.entranceTicket = entranceTicket;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

   public static Landmark[] populateLandmarks() {
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
    }
}
