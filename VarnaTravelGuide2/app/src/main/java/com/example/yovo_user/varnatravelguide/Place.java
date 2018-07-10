package com.example.yovo_user.varnatravelguide;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class Place {

    @PrimaryKey
    @ColumnInfo(name = "placeId")
    private Long id;
    @ColumnInfo(name = "placeName")
    private String name;
    @ColumnInfo(name = "placeAddress")
    private String address;
    @ColumnInfo(name = "placeName")
    private double latitude;
    @ColumnInfo(name = "placeName")
    private double longitude;
    @ColumnInfo(name = "placeName")
    private String workTime;
    @ColumnInfo(name = "placeName")
    private String contacts;
    @ColumnInfo(name = "placeName")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
