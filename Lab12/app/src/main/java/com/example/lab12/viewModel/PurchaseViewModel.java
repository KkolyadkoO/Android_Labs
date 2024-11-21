package com.example.lab12.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab12.App;
import com.example.lab12.DAO.PurchaseDao;
import com.example.lab12.entities.Purchase;

import java.util.List;

public class PurchaseViewModel extends ViewModel {
    private final PurchaseDao purchaseDao;

    public PurchaseViewModel() {
        purchaseDao = App.getInstance().getDatabase().purchaseDao();
    }

    public LiveData<Purchase> getPurchaseById(long purchaseId) {
        return purchaseDao.getById(purchaseId);
    }


}

