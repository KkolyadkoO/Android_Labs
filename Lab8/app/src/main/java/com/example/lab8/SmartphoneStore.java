package com.example.lab8;

import java.util.ArrayList;
import java.util.List;

public class SmartphoneStore {
    public static List<Smartphone> getSmartphones() {
        List<Smartphone> smartphones = new ArrayList<>();

        smartphones.add(new Smartphone(R.drawable.phone1, "Samsung", "Android", 899.99, 10, "Samsung Electronics", 2023, 128));
        smartphones.add(new Smartphone(R.drawable.phone2, "Apple", "iOS", 1199.99, 5, "Apple Inc.", 2023, 256));
        smartphones.add(new Smartphone(R.drawable.phone3, "Xiaomi", "Android", 699.99, 20, "Xiaomi Corporation", 2022, 64));

        return smartphones;
    }
}
