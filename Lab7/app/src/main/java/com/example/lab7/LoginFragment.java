package com.example.lab7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        TextInputEditText loginInput = view.findViewById(R.id.login_input);
        TextInputEditText passwordInput = view.findViewById(R.id.password_input);
        Button loginButton = view.findViewById(R.id.login_button);
        Button registerButton = view.findViewById(R.id.register_button);

        loginButton.setOnClickListener(v -> {
            String login = loginInput.getText().toString();
            String password = passwordInput.getText().toString();
            if (authenticateUser(login, password)) {
                navController.navigate(R.id.action_loginFragment_to_mainFragment);
            } else {
                Toast.makeText(getContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(v -> navController.navigate(R.id.action_loginFragment_to_registrationFragment));
        return view;
    }

    private boolean authenticateUser(String login, String password) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String storedPassword = sharedPreferences.getString(login, null);
        return password.equals(storedPassword);
    }
}
