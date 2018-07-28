package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "LANDMARK",
                foreignKeys = @ForeignKey(entity = Place.class,
                parentColumns = "placeId",
                childColumns = "landmarkId"))
public class Landmark {

    @PrimaryKey(autoGenerate = true)
    private long landmarkId ;

/*    @Embedded
    private Place place ;*/

    @ColumnInfo(name = "entranceTicket")
    private String entranceTicket;

    public Landmark(   long landmarkId,
                       String entranceTicket) {

        this.landmarkId = landmarkId;
        this.entranceTicket = entranceTicket;
    }

   /* public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
*/
    public long getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(long landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getEntranceTicket() {
        return entranceTicket;
    }

    public void setEntranceTicket(String entranceTicket) {
        this.entranceTicket = entranceTicket;
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
