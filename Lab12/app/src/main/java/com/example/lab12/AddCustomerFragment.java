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

import com.example.lab12.entities.Customer;
import com.example.lab12.viewModel.MainViewModel;

import java.util.Date;

public class AddCustomerFragment extends Fragment {
    private EditText etName, etLastName, etBirthday, etPhone;
    private Button btnAddCustomer;
    private MainViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);

        etName = view.findViewById(R.id.et_name);
        etLastName = view.findViewById(R.id.et_last_name);
        etBirthday = view.findViewById(R.id.et_birthday);
        etPhone = view.findViewById(R.id.et_phone);
        btnAddCustomer = view.findViewById(R.id.btn_add_customer);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        btnAddCustomer.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String birthday = etBirthday.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (!name.isEmpty() && !lastName.isEmpty() && !birthday.isEmpty() && !phone.isEmpty()) {
                Customer customer = new Customer();
                customer.name = name;
                customer.lastName = lastName;
                customer.birthday = new Date(); // Можно добавить парсинг даты
                customer.phone = phone;

                viewModel.insertCustomer(customer);
                getParentFragmentManager().popBackStack();
            }
        });

        return view;
    }
}

