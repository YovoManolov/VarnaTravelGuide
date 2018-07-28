package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.yovo_user.varnatravelguide.databasePackage.Place;

import java.util.List;

@Entity (tableName = "WORK_HOURS")
public class WorkHours {
    @PrimaryKey(autoGenerate = true)
    private Long workHoursId;
    @ColumnInfo(name="IS_24H")
    private Integer is24h; // 0-false 1-true; there is no boolean type :(
    @ColumnInfo(name="MON_TO_FRI")
    private String monFri;
    @ColumnInfo(name="SAT")
    private String sat;
    @ColumnInfo(name="SUN")
    private String sun;


    /**
     * @param is24h
     * @param monFri
     * @param sat
     * @param sun
     */
    public WorkHours(Integer is24h, String monFri, String sat, String sun) {
            this.is24h = is24h;
            this.monFri = monFri;
            this.sat = sat;
            this.sun = sun;
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

    public Long getWorkHoursId() {
        return workHoursId;
    }

    public void setWorkHoursId(Long workHoursId) {
        this.workHoursId = workHoursId;
    }

    public Integer getIs24h() {  return is24h; }

    public void setIs24h(Integer is24h) { this.is24h = is24h;  }
}
