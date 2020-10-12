package com.polyblack.company.data.crossreference;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(tableName = "speciality_employee_crossref", primaryKeys = {"employee_id", "speciality_id"})
public class SpecialityEmployeeCrossRef {
    @ColumnInfo(name = "employee_id")
    private int employeeId;
    @ColumnInfo(name = "speciality_id", index = true)
    private int specialityId;

    public SpecialityEmployeeCrossRef(int employeeId, int specialityId) {
        this.employeeId = employeeId;
        this.specialityId = specialityId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }
}
