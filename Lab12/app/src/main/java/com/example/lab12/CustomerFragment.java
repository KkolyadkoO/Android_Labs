package com.example.lab12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab12.adapters.CustomerAdapter;
import com.example.lab12.databinding.FragmentCustomersBinding;
import com.example.lab12.viewModel.MainViewModel;

public class CustomerFragment extends Fragment {

    private FragmentCustomersBinding binding;
    private CustomerAdapter adapter;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomersBinding.inflate(inflater, container, false);

        setupRecyclerView();
        setupViewModel();
        setupFab();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        binding.recyclerViewCustomers.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomerAdapter(customer -> openPurchases(customer.getId()));
        binding.recyclerViewCustomers.setAdapter(adapter);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getCustomers().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    private void setupFab() {
        binding.fabAddCustomer.setOnClickListener(v -> openAddCustomer());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
