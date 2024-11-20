package com.example.lab12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab12.entities.Purchase;
import com.example.lab12.viewModel.MainViewModel;

public class AddPurchaseFragment extends Fragment {
    private static final String ARG_CUSTOMER_ID = "customer_id";
    private long customerId;

    private EditText etProduct, etCount, etPrice;
    private Button btnAddPurchase;
    private MainViewModel viewModel;

    public static AddPurchaseFragment newInstance(long customerId) {
        AddPurchaseFragment fragment = new AddPurchaseFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_CUSTOMER_ID, customerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customerId = getArguments().getLong(ARG_CUSTOMER_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_purchase, container, false);

        etProduct = view.findViewById(R.id.et_product);
        etCount = view.findViewById(R.id.et_count);
        etPrice = view.findViewById(R.id.et_price);
        btnAddPurchase = view.findViewById(R.id.btn_add_purchase);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        btnAddPurchase.setOnClickListener(v -> {
            String product = etProduct.getText().toString().trim();
            String countStr = etCount.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();

            if (!product.isEmpty() && !countStr.isEmpty() && !priceStr.isEmpty()) {
                Purchase purchase = new Purchase();
                purchase.product = product;
                purchase.count = Integer.parseInt(countStr);
                purchase.price = Double.parseDouble(priceStr);
                purchase.customerId = customerId;

                viewModel.insertPurchase(purchase);
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}

