package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.yovo_user.varnatravelguide.databasePackage.Place;

import java.util.List;

@Entity (tableName = "WORK_HOURS")
public class WorkHours {
    private int id;
    private int placeId;
    private int is24h;
    private String monFri;
    private String sat;
    private String sun;

    public WorkHours(int id, int placeId,
                     int is24h, String monFri,
                     String sat, String sun) {
        this.id = id;
        this.placeId = placeId;
        this.is24h = is24h;
        this.monFri = monFri;
        this.sat = sat;
        this.sun = sun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

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

    public int getIs24h() {  return is24h; }

    public void setIs24h(int is24h) { this.is24h = is24h;  }

}
