package com.example.lab11;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab11.entities.University;

import java.util.List;

public class UniversytiesViewModel extends ViewModel {
    private final MutableLiveData<List<University>> listUniversities = new MutableLiveData<>();
    public void addUniversities(List<University> listUniversities) {
        this.listUniversities.setValue(listUniversities);
    }

    public LiveData<List<University>> getUniversities() {
        return listUniversities;
    }
}
