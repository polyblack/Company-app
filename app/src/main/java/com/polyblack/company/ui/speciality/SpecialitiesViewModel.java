package com.polyblack.company.ui.speciality;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.polyblack.company.CompanyApplication;
import com.polyblack.company.data.SharedRepository;
import com.polyblack.company.pojo.Speciality;

import java.util.List;

public class SpecialitiesViewModel extends ViewModel {
    private SharedRepository sharedRepository;
    public MutableLiveData<Throwable> errors;
    public MutableLiveData<Boolean> isLoading;
    public SpecialitiesViewModel() {
        sharedRepository = CompanyApplication.getInstance().getSharedRepository();
        errors = sharedRepository.errors;
        isLoading = sharedRepository.isLoading;
    }

    public LiveData<List<Speciality>> getSpecialities() {
        return sharedRepository.getSpecialities();
    }

    @Override
    protected void onCleared() {
        sharedRepository.compositeDisposable.clear();
        super.onCleared();
    }

    public void loadData() {
        sharedRepository.loadData();
    }

    public void clearErrors() {
        errors.setValue(null);
    }
}