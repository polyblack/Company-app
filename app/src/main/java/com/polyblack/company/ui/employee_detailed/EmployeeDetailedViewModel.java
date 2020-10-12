package com.polyblack.company.ui.employee_detailed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.polyblack.company.CompanyApplication;
import com.polyblack.company.data.SharedRepository;
import com.polyblack.company.data.crossreference.EmployeesWithSpecialities;

public class EmployeeDetailedViewModel extends ViewModel {
    private SharedRepository sharedRepository = CompanyApplication.getInstance().getSharedRepository();

    public LiveData<EmployeesWithSpecialities> getEmployeeWithSpecialities(int employeeId) {
        return sharedRepository.getEmployeeWithSpecialities(employeeId);
    }
}