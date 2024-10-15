package com.example.lab5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class ThirdFragment extends Fragment {

    private EditText etImageNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        etImageNumber = view.findViewById(R.id.etImageNumber);
        Button btnChoose = view.findViewById(R.id.btnChoose);

        btnChoose.setOnClickListener(v -> {
            String userName = getArguments().getString("userName");

            String imageNumber = etImageNumber.getText().toString();

            if (!imageNumber.isEmpty()) {
                if (Integer.parseInt(imageNumber) > 0 && Integer.parseInt(imageNumber) <= 4) {
                    int imageResId = getImageResource(imageNumber);
                    ThirdFragmentDirections.ActionThirdFragmentToSecondFragment action =
                            ThirdFragmentDirections.actionThirdFragmentToSecondFragment(userName, imageResId);
                    Navigation.findNavController(view).navigate(action);
                } else {
                    Toast.makeText(requireContext(), "Доступные картинки от 1 до 4", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    private int getImageResource(String imageNumber) {
        switch (imageNumber) {
            case "1":
                return R.drawable.image1;
            case "2":
                return R.drawable.image2;
            case "3":
                return R.drawable.image3;
            case "4":
                return R.drawable.image4;
            default:
                return R.drawable.image1;
        }
    }
}




