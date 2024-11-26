package com.example.lab12.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab12.App;
import com.example.lab12.DAO.CustomerDao;
import com.example.lab12.DAO.PurchaseDao;
import com.example.lab12.entities.Customer;
import com.example.lab12.entities.Purchase;

import java.util.List;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {
    private final CustomerDao customerDao = App.getInstance().getDatabase().customerDao();
    private final PurchaseDao purchaseDao = App.getInstance().getDatabase().purchaseDao();

    public LiveData<List<Customer>> getCustomers() {
        return customerDao.getAll();
    }

    public LiveData<List<Purchase>> getPurchasesByCustomer(long customerId) {
        return purchaseDao.getAllByCustomerId(customerId);
    }

    public void insertCustomer(Customer customer) {
        Executors.newSingleThreadExecutor().execute(() -> customerDao.insert(customer));
    }

    public void insertPurchase(Purchase purchase) {
        Executors.newSingleThreadExecutor().execute(() -> purchaseDao.insert(purchase));
    }
}

