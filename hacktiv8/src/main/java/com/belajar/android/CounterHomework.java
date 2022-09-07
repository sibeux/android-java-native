package com.belajar.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CounterHomework extends AppCompatActivity {

    Button counter;
    TextView number;
    private int sum =  0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_homework);

        number = findViewById(R.id.number);
        counter = findViewById(R.id.counter);
        counter.setOnClickListener(v -> {
            sum++;
            displayCounter();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Put int value
        outState.putInt("number",sum);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // get values from saved state
        sum = savedInstanceState.getInt("number");
        displayCounter();
    }

    protected void displayCounter(){
        number.setText(String.valueOf(sum));
    }
}