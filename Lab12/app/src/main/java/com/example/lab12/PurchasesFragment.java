package com.example.lab12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab12.adapters.PurchaseAdapter;
import com.example.lab12.viewModel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PurchasesFragment extends Fragment {
    private static final String ARG_CUSTOMER_ID = "customer_id";
    private long customerId;

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddPurchase;
    private PurchaseAdapter adapter;
    private MainViewModel viewModel;

    public static PurchasesFragment newInstance(long customerId) {
        PurchasesFragment fragment = new PurchasesFragment();
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
        View view = inflater.inflate(R.layout.fragment_purchases, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_purchases);
        fabAddPurchase = view.findViewById(R.id.fab_add_purchase);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PurchaseAdapter(purchase -> openPurchaseDetails(purchase.id));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getPurchasesByCustomer(customerId).observe(getViewLifecycleOwner(), adapter::submitList);

        fabAddPurchase.setOnClickListener(v -> openAddPurchase());
        return view;
    }

    private void openAddPurchase() {
        AddPurchaseFragment fragment = AddPurchaseFragment.newInstance(customerId);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void openPurchaseDetails(long purchaseId) {
        PurchaseDetailsFragment fragment = PurchaseDetailsFragment.newInstance(purchaseId);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

