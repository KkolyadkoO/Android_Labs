package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private ButtonViewModel viewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ButtonViewModel.class);

        textView = findViewById(R.id.textView);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonLoad = findViewById(R.id.buttonLoad);

        if (viewModel.getLastButtonPressed() != null) {
            updateTextView("Была нажата кнопка " + viewModel.getLastButtonPressed());
        }

        button1.setOnClickListener(view -> onButtonPressed("1"));
        button2.setOnClickListener(view -> onButtonPressed("2"));
        button3.setOnClickListener(view -> onButtonPressed("3"));
        button4.setOnClickListener(view -> onButtonPressed("4"));
        button5.setOnClickListener(view -> onButtonPressed("5"));
        button6.setOnClickListener(view -> onButtonPressed("6"));

        buttonSave.setOnClickListener(view -> saveButtonPressed());
        buttonLoad.setOnClickListener(view -> loadButtonPressed());
    }

    private void onButtonPressed(String buttonText) {
        viewModel.setLastButtonPressed(buttonText);
        updateTextView("Была нажата кнопка " + buttonText);
    }

    private void updateTextView(String text) {
        textView.setText(text);
    }

    private void saveButtonPressed() {
        viewModel.setLastSavedButton(viewModel.getLastButtonPressed());
    }

    private void loadButtonPressed() {
        if (viewModel.getLastSavedButton() != null) {
            updateTextView("Сохраненная кнопка: " + viewModel.getLastSavedButton());
            viewModel.setLastButtonPressed(viewModel.getLastSavedButton());
        } else {
            updateTextView("Нет сохраненной кнопки");
        }
    }
}