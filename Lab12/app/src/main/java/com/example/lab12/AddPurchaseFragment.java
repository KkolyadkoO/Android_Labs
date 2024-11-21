package com.example.lab12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab12.databinding.FragmentAddPurchaseBinding;
import com.example.lab12.entities.Purchase;
import com.example.lab12.viewModel.MainViewModel;

public class AddPurchaseFragment extends Fragment {

    private FragmentAddPurchaseBinding binding;
    private static final String ARG_CUSTOMER_ID = "customer_id";
    private long customerId;
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
        binding = FragmentAddPurchaseBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.btnAddPurchase.setOnClickListener(v -> addPurchase());
        return binding.getRoot();
    }

    private void addPurchase() {
        String product = binding.etProduct.getText().toString().trim();
        String countStr = binding.etCount.getText().toString().trim();
        String priceStr = binding.etPrice.getText().toString().trim();

        if (!product.isEmpty() && !countStr.isEmpty() && !priceStr.isEmpty()) {
            Purchase purchase = new Purchase();
            purchase.product = product;
            purchase.count = Integer.parseInt(countStr);
            purchase.price = Double.parseDouble(priceStr);
            purchase.customerId = customerId;

            viewModel.insertPurchase(purchase);
            getParentFragmentManager().popBackStack();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Очистка binding для предотвращения утечек памяти
    }
}
