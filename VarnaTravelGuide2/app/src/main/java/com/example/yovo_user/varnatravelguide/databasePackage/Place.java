package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity(tableName = "PLACE")
public class Place {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    @Relation(parentColumn = "id", entityColumn = "placeId", entity = Image.class)
    private List<Image> images;
    @Embedded
    private WorkHours workHours ;
    @ColumnInfo(name = "NAME")
    private String name;
    @ColumnInfo(name = "ADDRESS")
    private String address;
    @ColumnInfo(name = "LATITUDE")
    private double latitude;
    @ColumnInfo(name = "LONGITUDE")
    private double longitude;
    @ColumnInfo(name = "CONTACTS")
    private String contacts;
    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    public Place(String name,
                 String address,
                 double latitude,
                 double longitude,
                 String contacts,
                 String description,
                 Integer is24h,
                 String monFri,
                 String sun,
                 String sat){

        this.images = images;
        this.workHours = new WorkHours(is24h, monFri, sat, sun);
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contacts = contacts;
        this.description = description;
    }

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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

}
