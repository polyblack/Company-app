package com.polyblack.company.data.crossreference;

import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.polyblack.company.pojo.Employee;
import com.polyblack.company.pojo.Speciality;

import java.util.List;

public class SpecialitiesWithEmployees {
    @Embedded
    Speciality speciality;
    @Relation(
            parentColumn = "speciality_id",
            entity = Employee.class,
            entityColumn = "employee_id",
            associateBy = @Junction(
                    value = SpecialityEmployeeCrossRef.class,
                    parentColumn = "speciality_id",
                    entityColumn = "employee_id")
    )
    List<Employee> employees;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}