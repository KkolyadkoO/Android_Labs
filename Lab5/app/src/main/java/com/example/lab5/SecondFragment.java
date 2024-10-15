package com.example.lab5;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class SecondFragment extends Fragment {

    private TextView tvHello;
    private ImageView ivSelectedImage;
    private Button btnChooseImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        tvHello = view.findViewById(R.id.tvHello);
        ivSelectedImage = view.findViewById(R.id.ivSelectedImage);
        btnChooseImage = view.findViewById(R.id.btnChooseImage);

        // Получаем аргументы
        SecondFragmentArgs args = SecondFragmentArgs.fromBundle(getArguments());
        String userName = args.getUserName();
        int imageResId = args.getImageResId();

        // Устанавливаем текст и изображение
        tvHello.setText("Здравствуйте, " + userName);
        ivSelectedImage.setImageResource(imageResId);

        // Настройка кнопки для перехода на третий фрагмент
        btnChooseImage.setOnClickListener(v -> {
            // Переход на третий фрагмент
            SecondFragmentDirections.ActionSecondFragmentToThirdFragment action =
                    SecondFragmentDirections.actionSecondFragmentToThirdFragment(userName);
            Navigation.findNavController(view).navigate(action);
        });

        return view;
    }
}
