package com.example.lab9;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmartphoneViewModel extends ViewModel {
    private final MutableLiveData<Smartphone> selectedSmartphone = new MutableLiveData<>();

    public void selectSmartphone(Smartphone smartphone) {
        selectedSmartphone.setValue(smartphone);
    }

    public LiveData<Smartphone> getSelectedSmartphone() {
        return selectedSmartphone;
    }
}