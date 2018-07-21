package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import com.example.yovo_user.varnatravelguide.databasePackage.Place;

import java.util.List;

@Entity (tableName = "WORK_HOURS")
public class WorkHours {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @Relation(parentColumn = "id", entityColumn = "workHoursId", entity = Place.class)
    private List<Place> places;
    @ColumnInfo(name="IS_24H")
    private Integer is24H; // 0-false 1-true; there is no boolean type :(
    @ColumnInfo(name="MON_TO_FRI")
    private String monFri;
    @ColumnInfo(name="SAT")
    private String sat;
    @ColumnInfo(name="SUN")
    private String sun;

    public WorkHours(){}
    public WorkHours(Integer is24H, String monFri, String sat, String sun) {
        if(is24H != null && is24H == 1){
            this.is24H = is24H;
        }else{
            this.monFri = monFri;
            this.sat = sat;
            this.sun = sun;
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static WorkHours[] populateWorkHours() {
        return new WorkHours[] {
                new WorkHours(1,null,null,null),
                //shops
                    /*malls*/
                new WorkHours(0,"10:00 AM - 10:00 PM",
                                            "10:00 AM - 10:00 PM",
                                                "10:00 AM - 10:00 PM"),

                new WorkHours(0,null,null,null),
                new WorkHours(0,null,null,null),
                new WorkHours(0,null,null,null),
        };
    }
}
