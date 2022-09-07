package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class TextViews extends AppCompatActivity {

    TextView textViews;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_views);

        textViews = findViewById(R.id.textView);

        textViews.setTextColor(Color.WHITE);
        textViews.setText("M. Nasrul Wahabi");
        textViews.setOnClickListener(v -> {

            textViews.setTextColor(Color.GREEN);
            textViews.setBackgroundColor(Color.BLACK);
        });
    }
}