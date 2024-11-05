package com.example.lab9;
import java.util.ArrayList;
import java.util.List;

public class SmartphoneStore {
    public static List<Smartphone> getSmartphones() {
        List<Smartphone> smartphones = new ArrayList<>();

        smartphones.add(new Smartphone(R.drawable.phone1, "Samsung1", "Android", 899.99, 10, "Samsung Electronics", 2023, 128));
        smartphones.add(new Smartphone(R.drawable.phone2, "Apple1", "iOS", 1199.99, 5, "Apple Inc.", 2023, 256));
        smartphones.add(new Smartphone(R.drawable.phone3, "Xiaomi1", "Android", 699.99, 20, "Xiaomi Corporation", 2022, 64));
        smartphones.add(new Smartphone(R.drawable.phone1, "Samsung2", "Android", 899.99, 10, "Samsung Electronics", 2023, 128));
        smartphones.add(new Smartphone(R.drawable.phone2, "Apple2", "iOS", 1199.99, 5, "Apple Inc.", 2023, 256));
        smartphones.add(new Smartphone(R.drawable.phone3, "Xiaomi2", "Android", 699.99, 20, "Xiaomi Corporation", 2022, 64));
        smartphones.add(new Smartphone(R.drawable.phone1, "Samsung3", "Android", 899.99, 10, "Samsung Electronics", 2023, 128));
        smartphones.add(new Smartphone(R.drawable.phone2, "Apple3", "iOS", 1199.99, 5, "Apple Inc.", 2023, 256));
        smartphones.add(new Smartphone(R.drawable.phone3, "Xiaomi3", "Android", 699.99, 20, "Xiaomi Corporation", 2022, 64));
        smartphones.add(new Smartphone(R.drawable.phone1, "Samsung4", "Android", 899.99, 10, "Samsung Electronics", 2023, 128));
        smartphones.add(new Smartphone(R.drawable.phone2, "Apple4", "iOS", 1199.99, 5, "Apple Inc.", 2023, 256));
        smartphones.add(new Smartphone(R.drawable.phone3, "Xiaomi4", "Android", 699.99, 20, "Xiaomi Corporation", 2022, 64));

        return smartphones;
    }
}
