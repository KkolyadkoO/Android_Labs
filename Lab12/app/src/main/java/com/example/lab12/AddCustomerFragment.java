package com.example.lab12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab12.databinding.FragmentAddCustomerBinding;
import com.example.lab12.entities.Customer;
import com.example.lab12.viewModel.MainViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddCustomerFragment extends Fragment {

    private FragmentAddCustomerBinding binding;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddCustomerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.btnAddCustomer.setOnClickListener(v -> addCustomer());
        return binding.getRoot();
    }

    private void addCustomer() {
        String name = binding.etName.getText().toString().trim();
        String lastName = binding.etLastName.getText().toString().trim();
        String birthdayStr = binding.etBirthday.getText().toString().trim();
        String phone = binding.etPhone.getText().toString().trim();

        if (!name.isEmpty() && !lastName.isEmpty() && !birthdayStr.isEmpty() && !phone.isEmpty()) {
            Date birthday = parseDate(birthdayStr);
            if (birthday == null) {
                Toast.makeText(
                        requireContext(),
                        "Некорректная дата. Используйте формат yyyy-MM-dd.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Customer customer = new Customer();
            customer.name = name;
            customer.lastName = lastName;
            customer.birthday = birthday;
            customer.phone = phone;

            viewModel.insertCustomer(customer);
            getParentFragmentManager().popBackStack();
        }
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null; // Возвращаем null в случае ошибки
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
