package com.example.lab5;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.Navigation;

public class FirstFragment extends Fragment {

    private EditText etName;
    private EditText etPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        etName = view.findViewById(R.id.etName);
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String userName = etName.getText().toString();

            int imageResId = R.drawable.white;

            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(userName, imageResId);
            Navigation.findNavController(view).navigate(action);
        });

        return view;
    }
}
