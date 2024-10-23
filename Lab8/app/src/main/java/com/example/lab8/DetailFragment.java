package com.example.lab8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    private static final String ARG_SMARTPHONE = "smartphone";

    private Smartphone smartphone;

    public static DetailFragment newInstance(Smartphone smartphone) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_SMARTPHONE, smartphone); // Используем putParcelable вместо putSerializable
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            smartphone = getArguments().getParcelable(ARG_SMARTPHONE); // Используем getParcelable
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView imageView = view.findViewById(R.id.smartphone_image_detail);
        TextView brandTextView = view.findViewById(R.id.smartphone_brand_detail);
        TextView osTextView = view.findViewById(R.id.smartphone_os_detail);
        TextView priceTextView = view.findViewById(R.id.smartphone_price_detail);
        TextView memoryTextView = view.findViewById(R.id.smartphone_memory_detail);
        TextView manufacturerTextView = view.findViewById(R.id.smartphone_manufacturer_detail);
        TextView releaseYearTextView = view.findViewById(R.id.smartphone_release_year_detail);
        TextView quantityTextView = view.findViewById(R.id.smartphone_quantity_detail);

        imageView.setImageResource(smartphone.getImageResourceId());
        brandTextView.setText(smartphone.getBrand());
        osTextView.setText(smartphone.getOsType());
        priceTextView.setText(String.format("$%.2f", smartphone.getPrice()));
        memoryTextView.setText(smartphone.getMemorySize() + " GB");
        manufacturerTextView.setText(smartphone.getManufacturer());
        releaseYearTextView.setText(String.valueOf(smartphone.getReleaseYear()));
        quantityTextView.setText("In Stock: " + smartphone.getQuantity());

        return view;
    }
}
