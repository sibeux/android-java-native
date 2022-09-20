package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Services extends AppCompatActivity {

    Button buttonStart, buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStop =  findViewById(R.id.buttonStop);

        buttonStart.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(),ServicesExample.class);
            startService(intent);
        });

        buttonStop.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(),ServicesExample.class);
            stopService(intent);
        });
    }
}