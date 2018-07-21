package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "LANDMARK")
public class Landmark {

    @PrimaryKey(autoGenerate = true)
    private long id ;
    @Embedded
    private Place place ;
    @ColumnInfo(name = "entranceTicket")
    private double entranceTicket;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getEntranceTicket() {
        return entranceTicket;
    }

    public void setEntranceTicket(double entranceTicket) {
        this.entranceTicket = entranceTicket;
    }

}
