package com.example.lab12.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab12.entities.Purchase;

import java.util.List;

@Dao
public interface PurchaseDao {
    @Query("SELECT * FROM purchase")
    LiveData<List<Purchase>> getAll();

    @Query("SELECT * FROM purchase WHERE id = :id")
    LiveData<Purchase> getById(long id);

    @Query("SELECT * FROM purchase WHERE customer_id = :customerId")
    LiveData<List<Purchase>> getAllByCustomerId(long customerId);

    @Insert
    void insert(Purchase purchase);

    @Update
    void update(Purchase purchase);

    @Delete
    void delete(Purchase purchase);
}
