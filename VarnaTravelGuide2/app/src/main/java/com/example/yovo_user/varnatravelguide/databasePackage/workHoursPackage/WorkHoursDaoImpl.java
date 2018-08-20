package com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

public class WorkHoursDaoImpl implements WorkHoursDao {
    @Override
    public void createWorkHoursTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection, DbStringConstants.TABLE_WORK_HOURS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_WORK_HOURS_TABLE);
    }

    @Override
    public void addWorkHours(SQLiteDatabase dbWritableConnection, WorkHours[] workHours) {
        dbWritableConnection.beginTransaction();

        for(int i = 0 ;i < workHours.length ;i++){
            ContentValues values = new ContentValues();

            values.put(DbStringConstants.WH_PLACE_ID, workHours[i].getPlaceId());
            if(workHours[i].getIs24h() == -1){
                values.put(DbStringConstants.WH_IS_24H,workHours[i].getIs24h());
                values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
            }
            else if(workHours[i].getIs24h() == 0){
                values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
                values.put(DbStringConstants.WH_SAT, workHours[i].getSat());
                values.put(DbStringConstants.WH_SUN, workHours[i].getSun());
            }
            else if(workHours[i].getIs24h() == 1){
                values.put(DbStringConstants.WH_IS_24H, workHours[i].getIs24h());
            }
            else{
                values.put(DbStringConstants.WH_IS_24H, workHours[i].getPlaceId());
                values.put(DbStringConstants.WH_SAT, workHours[i].getPlaceId());
                values.put(DbStringConstants.WH_SUN, workHours[i].getPlaceId());
            }
            dbWritableConnection.insert(DbStringConstants.TABLE_WORK_HOURS, null, values);
        }

        dbWritableConnection.endTransaction();
    }
}
