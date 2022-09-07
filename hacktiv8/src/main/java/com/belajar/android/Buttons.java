package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class Buttons extends AppCompatActivity {

    View button;
    View button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        button = findViewById(R.id.click);
        button2 = findViewById(R.id.unclick);

        button.setOnClickListener(v -> {
            button.setBackgroundColor(Color.CYAN);
            button2.setBackgroundColor(Color.BLUE);
            button2.setClickable(true);
            button2.setOnClickListener(v1 -> button.setBackgroundColor(Color.GREEN));
        });
    }
}