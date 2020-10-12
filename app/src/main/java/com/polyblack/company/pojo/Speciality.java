package com.polyblack.company.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "speciality")
public class Speciality {
    @ColumnInfo(name = "speciality_id")
    @PrimaryKey
    @SerializedName("specialty_id")
    @Expose
    private int specialityId;
    @SerializedName("name")
    @Expose
    private String name;

    public Speciality(int specialityId, String name) {
        this.specialityId = specialityId;
        this.name = name;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}