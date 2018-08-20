package com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage;

import android.database.sqlite.SQLiteDatabase;


public interface WorkHoursDao {
    public void createWorkHoursTable(SQLiteDatabase dbWritableConnection);
    public void addWorkHours(SQLiteDatabase dbWritableConnection,
                          WorkHours[] workHours);
}
