package com.polyblack.company.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.polyblack.company.data.crossreference.SpecialityEmployeeCrossRef;
import com.polyblack.company.data.crossreference.SpecialityEmployeeDao;
import com.polyblack.company.pojo.Employee;
import com.polyblack.company.pojo.Speciality;

@Database(entities = {Employee.class, Speciality.class, SpecialityEmployeeCrossRef.class}, version = 6, exportSchema = false)
public abstract class CompanyDatabase extends RoomDatabase {
    public abstract SpecialityEmployeeDao specialityEmployeeDao();
    private static final String DATABASE_NAME = "company.db";
    private static CompanyDatabase database;

    public static CompanyDatabase getInstance(Context context) {
        synchronized (CompanyDatabase.class) {
            if (database == null) {
                database = Room.databaseBuilder(context, CompanyDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
            }
            return database;
        }
    }

}
