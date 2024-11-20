package com.example.lab12.entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(
        entity = Customer.class,
        parentColumns = "id",
        childColumns = "customer_id",
        onDelete = CASCADE
))
public class Purchase {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String product;
    public Integer count;
    public Double price;
    @ColumnInfo(name = "customer_id")
    public long customerId;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
