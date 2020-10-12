package com.polyblack.company.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polyblack.company.CompanyApplication;
import com.polyblack.company.api.ApiFactory;
import com.polyblack.company.api.ApiService;
import com.polyblack.company.data.crossreference.EmployeesWithSpecialities;
import com.polyblack.company.data.crossreference.SpecialitiesWithEmployees;
import com.polyblack.company.data.crossreference.SpecialityEmployeeCrossRef;
import com.polyblack.company.pojo.Employee;
import com.polyblack.company.pojo.Response;
import com.polyblack.company.pojo.Speciality;
import com.polyblack.company.util.DataUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class SharedRepository {
    private static CompanyDatabase database;
    private ApiService apiService;
    public CompositeDisposable compositeDisposable;
    public MutableLiveData<Boolean> isLoading;
    public MutableLiveData<Throwable> errors;

    public SharedRepository() {
        database = CompanyApplication.getInstance().getDatabase();
        apiService = ApiFactory.getInstance().getApiService();
        compositeDisposable = new CompositeDisposable();
        isLoading = new MutableLiveData<>();
        errors = new MutableLiveData<>();
        loadData();

    }

    public LiveData<SpecialitiesWithEmployees> getSpecialityWithEmployees(int specialityId) {
        return database.specialityEmployeeDao().getSpecialityWithEmployees(specialityId);
    }

    public LiveData<EmployeesWithSpecialities> getEmployeeWithSpecialities(int specialityId) {
        return database.specialityEmployeeDao().getEmployeeWithSpecialities(specialityId);
    }

    public LiveData<List<Speciality>> getSpecialities() {
        return database.specialityEmployeeDao().getAllSpecialities();
    }

    private void updateDataInDb(Response response) {
        int fakeId = 0;
        ArrayList<SpecialityEmployeeCrossRef> crossRef = new ArrayList<>();
        HashMap<Integer, Speciality> specialityHashMap = new HashMap<>();
        for (Employee employee : response.getEmployees()) {
            DataUtils.beautifyEmployee(employee);
            employee.setEmployeeId(fakeId);
            for (Speciality speciality : employee.getSpeciality()) {
                specialityHashMap.put(speciality.getSpecialityId(), speciality);
                crossRef.add(new SpecialityEmployeeCrossRef(employee.getEmployeeId(), speciality.getSpecialityId()));
            }
            fakeId++;
        }
        compositeDisposable.add(Completable.fromAction(new Action() {
            @Override
            public void run() {
                database.specialityEmployeeDao().updateEmployeeAndSpeciality(new ArrayList<Speciality>(specialityHashMap.values()), response.getEmployees(), crossRef);
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(() -> isLoading.postValue(false)));
    }

    public void loadData() {
        compositeDisposable.add(apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(__ -> isLoading.postValue(true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateDataInDb, error -> {
                    isLoading.postValue(false);
                    errors.postValue(error);
                }));
    }

}
