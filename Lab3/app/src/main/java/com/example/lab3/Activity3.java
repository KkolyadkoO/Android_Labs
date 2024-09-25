package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {

    public static final String FILM = "";
    RadioGroup rg;
    Button btnChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        rg = findViewById(R.id.rgFilms);
        btnChoose = findViewById(R.id.btnChoose);

        btnChoose.setOnClickListener(view -> {
            int selectedRBId = rg.getCheckedRadioButtonId();
            RadioButton selectedRB = findViewById(selectedRBId);
            String selectedMovie = selectedRB.getText().toString();
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString(FILM, selectedMovie);
            resultIntent.putExtras(bundle);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}