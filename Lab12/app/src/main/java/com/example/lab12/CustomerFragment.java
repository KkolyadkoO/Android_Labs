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

import com.example.lab12.adapters.CustomerAdapter;
import com.example.lab12.viewModel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomerFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddCustomer;
    private CustomerAdapter adapter;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_customers);
        fabAddCustomer = view.findViewById(R.id.fab_add_customer);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomerAdapter(customer -> openPurchases(customer.getId()));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getCustomers().observe(getViewLifecycleOwner(), adapter::submitList);

        fabAddCustomer.setOnClickListener(v -> openAddCustomer());
        return view;
    }

    private void openAddCustomer() {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new AddCustomerFragment())
                .addToBackStack(null)
                .commit();
    }

    private void openPurchases(long customerId) {
        PurchasesFragment fragment = PurchasesFragment.newInstance(customerId);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

