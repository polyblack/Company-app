package com.polyblack.company.data.crossreference;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.polyblack.company.pojo.Employee;
import com.polyblack.company.pojo.Speciality;

import java.util.List;

@Dao
public abstract class SpecialityEmployeeDao {

    @Insert
    public abstract void insertManyEmployees(List<Employee> employeeList);

    @Insert
    public abstract void insertManySpecialities(List<Speciality> specialityList);

    @Insert
    public abstract void insertManyCrossRefs(List<SpecialityEmployeeCrossRef> crossRefList);

    @Query("DELETE FROM employee")
    public abstract void deleteAllEmployees();

    @Query("DELETE FROM speciality")
    public abstract void deleteAllSpecialities();

    @Query("DELETE FROM speciality_employee_crossref")
    public abstract void deleteAllCrossRef();

    @Query("SELECT * FROM speciality")
    public abstract LiveData<List<Speciality>> getAllSpecialities();

    @Query("SELECT * FROM employee WHERE employee_id=:employeeId")
    @SuppressWarnings("RELATION_QUERY_WITHOUT_TRANSACTION")
    public abstract LiveData<EmployeesWithSpecialities> getEmployeeWithSpecialities(int employeeId);

    @Query("SELECT * FROM speciality WHERE speciality_id=:specialityId")
    @SuppressWarnings("RELATION_QUERY_WITHOUT_TRANSACTION")
    public abstract LiveData<SpecialitiesWithEmployees> getSpecialityWithEmployees(int specialityId);

    @Transaction
    public void updateEmployeeAndSpeciality(List<Speciality> specialities, List<Employee> employees, List<SpecialityEmployeeCrossRef> crossRefs) {
        deleteAllEmployees();
        deleteAllSpecialities();
        deleteAllCrossRef();
        insertManyEmployees(employees);
        insertManySpecialities(specialities);
        insertManyCrossRefs(crossRefs);
    }
}
