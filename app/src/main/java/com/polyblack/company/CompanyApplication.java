package com.polyblack.company;

import android.app.Application;

import com.polyblack.company.data.CompanyDatabase;
import com.polyblack.company.data.SharedRepository;

public class CompanyApplication extends Application {
    public static CompanyApplication instance;
    private CompanyDatabase database;
    private SharedRepository sharedRepository;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = CompanyDatabase.getInstance(this);
        sharedRepository = new SharedRepository();
    }

    public static CompanyApplication getInstance() {
        return instance;
    }

    public CompanyDatabase getDatabase() {
        return database;
    }

    public SharedRepository getSharedRepository() {
        return sharedRepository;
    }

}

