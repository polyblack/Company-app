package com.polyblack.company.data.crossreference;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.polyblack.company.pojo.Employee;
import com.polyblack.company.pojo.Speciality;

import java.util.List;

public class EmployeesWithSpecialities {
    @Embedded
    Employee employees;
    @Relation(
            parentColumn = "employee_id",
            entity = Speciality.class,
            entityColumn = "speciality_id",
            associateBy = @Junction(
                    value = SpecialityEmployeeCrossRef.class,
                    parentColumn = "employee_id",
                    entityColumn = "speciality_id")
    )
    List<Speciality> specialities;

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
