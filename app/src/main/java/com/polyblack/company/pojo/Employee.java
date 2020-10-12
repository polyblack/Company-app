package com.polyblack.company.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "employee")
public class Employee {
    @ColumnInfo(name = "employee_id")
    @PrimaryKey
    private int employeeId;
    @SerializedName("f_name")
    @Expose
    private String fName;
    @SerializedName("l_name")
    @Expose
    private String lName;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("avatr_url")
    @Expose
    private String avatarUrl;
    @SerializedName("specialty")
    @Expose
    @Ignore
    private List<Speciality> speciality = null;

    public Employee(int employeeId, String fName, String lName, String birthday, String avatarUrl) {
        this.employeeId = employeeId;
        this.fName = fName;
        this.lName = lName;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatrUrl) {
        this.avatarUrl = avatrUrl;
    }

    public List<Speciality> getSpeciality() {
        return speciality;
    }

    public void setSpecialty(List<Speciality> speciality) {
        this.speciality = speciality;
    }

}