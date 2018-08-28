package com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;


public interface WorkHoursDao {
    public void createWorkHoursTable(SQLiteDatabase dbWritableConnection);
    public void addWorkHours(SQLiteDatabase dbWritableConnection,
                          WorkHours[] workHours);
    public List<WorkHours> getWorkHoursByPlaceId(SQLiteDatabase dbReadableConnection,int placeId);
}
