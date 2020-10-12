package com.polyblack.company.ui.employee;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.polyblack.company.CompanyApplication;
import com.polyblack.company.data.SharedRepository;
import com.polyblack.company.data.crossreference.SpecialitiesWithEmployees;
import com.polyblack.company.pojo.Employee;

import java.util.List;

public class EmployeesViewModel extends ViewModel {
    private SharedRepository sharedRepository = CompanyApplication.getInstance().getSharedRepository();

    public LiveData<List<Employee>> getEmployeesBySpecialityId(int specialityId) {
        return Transformations.map(sharedRepository.getSpecialityWithEmployees(specialityId), new Function<SpecialitiesWithEmployees, List<Employee>>() {
            @Override
            public List<Employee> apply(SpecialitiesWithEmployees input) {
                return input.getEmployees();
            }
        });
    }


}