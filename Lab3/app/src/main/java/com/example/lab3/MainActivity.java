package com.example.lab3;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 1;
    public static final String NAME = "name";
    public static final String PHONE = "phone";

    EditText etName, etPhone;
    Button btnLogin, btnCall, btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhoneNumber);
        btnLogin = findViewById(R.id.btnLogin);
        btnCall = findViewById(R.id.btnCall);
        btnOpen = findViewById(R.id.btnOpenLab2);

        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            Bundle bundle = new Bundle();
            bundle.putString(NAME, etName.getText().toString());
            bundle.putString(PHONE, etPhone.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        });

        btnOpen.setOnClickListener(view -> {
//            Intent launchLab2 = getPackageManager().getLaunchIntentForPackage("com.example.lab2");
//            Intent launchLab2 = new Intent();
//            launchLab2.setAction("com.Lab2");
            Intent launchLab2 = new Intent();
            launchLab2.setComponent(new ComponentName("com.example.lab2","com.example.lab2.MainActivity"));
            if (launchLab2 != null) {
                startActivity(launchLab2);
            } else {
                Toast.makeText(MainActivity.this, "Приложение Лаб2 не найдено", Toast.LENGTH_SHORT).show();
            }
        });

        btnCall.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
            } else {
                makePhoneCall();
            }

        });
    }

    private void makePhoneCall() {
        if (etPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + etPhone.getText().toString()));
            startActivity(callIntent);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Разрешение на звонок не предоставлено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}