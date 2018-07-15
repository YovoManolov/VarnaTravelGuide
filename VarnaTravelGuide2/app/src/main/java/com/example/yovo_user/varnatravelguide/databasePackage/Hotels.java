package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "HOTELS")
public class Hotels {
    @PrimaryKey
    private long id ;
    @Embedded
    private Place place ;
    @ColumnInfo(name = "NUMB_OF_STARS")
    private int numbOfStars;

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int getNumbOfStars() {
        return numbOfStars;
    }

    //TODO make a validation for min and maks numb of stars
    public void setNumbOfStars(int numbOfStars) {
        this.numbOfStars = numbOfStars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
