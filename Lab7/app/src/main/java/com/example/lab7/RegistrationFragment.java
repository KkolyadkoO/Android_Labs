package com.example.lab7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrationFragment extends Fragment {
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        TextInputEditText loginInput = view.findViewById(R.id.login_input);
        TextInputEditText passwordInput = view.findViewById(R.id.password_input);
        Button registerButton = view.findViewById(R.id.register_button);

        registerButton.setOnClickListener(v -> {
            String login = loginInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (!login.isEmpty() && !password.isEmpty()) {
                saveUserCredentials(login, password);
                Toast.makeText(getContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_registrationFragment_to_mainFragment);
            } else {
                Toast.makeText(getContext(), "Введите логин и пароль", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserCredentials(String login, String password) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(login, password);
        editor.apply();
    }
}
