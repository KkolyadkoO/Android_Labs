package com.example.lab8;

import android.os.Parcel;
import android.os.Parcelable;

public class Smartphone implements Parcelable {
    private int imageResourceId;
    private String brand;
    private String osType;
    private double price;
    private int memorySize;
    private String manufacturer;
    private int releaseYear;
    private int quantity;

    // Конструктор
    public Smartphone(int imageResourceId, String brand, String osType, double price, int memorySize,
                      String manufacturer, int releaseYear, int quantity) {
        this.imageResourceId = imageResourceId;
        this.brand = brand;
        this.osType = osType;
        this.price = price;
        this.memorySize = memorySize;
        this.manufacturer = manufacturer;
        this.releaseYear = releaseYear;
        this.quantity = quantity;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // Геттеры и сеттеры для всех полей...

    // Реализация Parcelable
    protected Smartphone(Parcel in) {
        imageResourceId = in.readInt();
        brand = in.readString();
        osType = in.readString();
        price = in.readDouble();
        memorySize = in.readInt();
        manufacturer = in.readString();
        releaseYear = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<Smartphone> CREATOR = new Creator<Smartphone>() {
        @Override
        public Smartphone createFromParcel(Parcel in) {
            return new Smartphone(in);
        }

        @Override
        public Smartphone[] newArray(int size) {
            return new Smartphone[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResourceId);
        dest.writeString(brand);
        dest.writeString(osType);
        dest.writeDouble(price);
        dest.writeInt(memorySize);
        dest.writeString(manufacturer);
        dest.writeInt(releaseYear);
        dest.writeInt(quantity);
    }

    // Дополнительно, если нужны геттеры/сеттеры, добавьте их
}
