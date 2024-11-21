package com.example.lab12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab12.databinding.FragmentPurchaseDetailsBinding;
import com.example.lab12.entities.Purchase;
import com.example.lab12.viewModel.PurchaseViewModel;

public class PurchaseDetailsFragment extends Fragment {

    private FragmentPurchaseDetailsBinding binding;
    private static final String ARG_PURCHASE_ID = "purchase_id";
    private long purchaseId;

    private PurchaseViewModel viewModel;

    public static PurchaseDetailsFragment newInstance(long purchaseId) {
        PurchaseDetailsFragment fragment = new PurchaseDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PURCHASE_ID, purchaseId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            purchaseId = getArguments().getLong(ARG_PURCHASE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPurchaseDetailsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PurchaseViewModel.class);
        observePurchaseDetails();
        return binding.getRoot();
    }

    private void observePurchaseDetails() {
        viewModel.getPurchaseById(purchaseId).observe(getViewLifecycleOwner(), purchase -> {
            if (purchase != null) {
                displayPurchaseDetails(purchase);
            }
        });
    }

    private void displayPurchaseDetails(Purchase purchase) {
        binding.tvProduct.setText(purchase.product);
        binding.tvCount.setText(String.valueOf(purchase.count));
        binding.tvPrice.setText(String.format("$%.2f", purchase.price));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
