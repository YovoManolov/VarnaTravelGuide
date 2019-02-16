package com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;


public interface WorkHoursDao {
    public void createWorkHoursTable();
    public void addWorkHours(WorkHours[] workHours);
    public List<WorkHours> getWorkHoursByPlaceId(int placeId);
}
