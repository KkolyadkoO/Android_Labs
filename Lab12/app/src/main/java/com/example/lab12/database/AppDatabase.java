package com.example.lab12.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.lab12.DAO.CustomerDao;
import com.example.lab12.DAO.PurchaseDao;
import com.example.lab12.entities.Customer;
import com.example.lab12.entities.Purchase;
import com.example.lab12.utils.Converters;

@Database(entities = {Customer.class, Purchase.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();
    public abstract PurchaseDao purchaseDao();
}
