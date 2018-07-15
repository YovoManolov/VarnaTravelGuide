package com.example.yovo_user.varnatravelguide;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import com.example.yovo_user.varnatravelguide.databasePackage.Place;

import java.util.List;

@Entity (tableName = "WORK_HOURS")
public class WorkHours {
    @PrimaryKey
    private Long id;
    @Relation(parentColumn = "id", entityColumn = "workHoursId", entity = Place.class)
    private List<Place> places;
    @ColumnInfo(name="MON_TO_FRI")
    private String monFri;
    @ColumnInfo(name="SAT")
    private String sat;
    @ColumnInfo(name="SUN")
    private String sun;

    public String getMonFri() {
        return monFri;
    }

    public void setMonFri(String monFri) {
        this.monFri = monFri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
