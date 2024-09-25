package com.example.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends AppCompatActivity {

    String name = "";
    String phone = "";
    String film = "";
    TextView tvHello, tvFilm;
    Button btnChooseFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tvHello = findViewById(R.id.tvHello);
        tvFilm = findViewById(R.id.tvFilm);
        btnChooseFilm = findViewById(R.id.btnChooseFilm);

        name = getIntent().getStringExtra(MainActivity.NAME);
        phone = getIntent().getStringExtra(MainActivity.PHONE);

        tvHello.setText(String.format("Привет, %s", name));

        btnChooseFilm.setOnClickListener(view -> {
            Intent intent = new Intent(Activity2.this, Activity3.class);
            startForResultLauncher.launch(intent);
        });
    }


    ActivityResultLauncher<Intent> startForResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        film = intent.getStringExtra(Activity3.FILM);
                        tvFilm.setText(String.format("Давайте посмотрим: %s", film));
                    }
                }
            }
    );
}