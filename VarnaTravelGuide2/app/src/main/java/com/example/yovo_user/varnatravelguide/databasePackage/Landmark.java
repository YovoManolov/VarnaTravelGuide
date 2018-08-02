package com.example.yovo_user.varnatravelguide.databasePackage;

public class Landmark {

    private int id ;
    private int placeId;
    private String entranceTicket;

    public Landmark(int id, int placeId, String entranceTicket) {
        this.id = id;
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

   /* public static Landmark[] populateLandmarks() {
        return new Landmark[] {
               new Landmark(
                       "Adults: 3.00 leva\n" +
                               " Children/Students: 2.00 leva" ),
                new Landmark(,
                        "Information not available. " ),
                new Landmark(,
                        "No ticket required "),
                new Landmark(,
                        "аdults: 4.00 leva\n" +
                                "students: 1.00 leva\n"+
                                "organized groups: 0.50 leva \n"+
                                "lecture: 4.00 leva"),
                new Landmark(
                        "Adults:  10.00  leva\n" +
                                " Children/Students: 2.00 leva")
        };
    }*/
}
