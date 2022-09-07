package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.LinearLayout;

public class RadioButtons extends AppCompatActivity {

    TextView result;
    View button;
    RadioButton red,blue,yellow;
    LinearLayout layout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        result = findViewById(R.id.result);
        button = findViewById(R.id.button);
        red = findViewById(R.id.radioButtonRed);
        blue = findViewById(R.id.radioButtonBlue);
        yellow = findViewById(R.id.radioButtonYellow);
        layout = findViewById(R.id.linier);

        button.setOnClickListener(v -> {
            if (red.isChecked()){
                layout.setBackgroundColor(Color.RED);
                result.setText("Color is RED");
            } else if(blue.isChecked()){
                layout.setBackgroundColor(Color.BLUE);
                result.setText("Color is BLUE");
            } else if (yellow.isChecked()){
                layout.setBackgroundColor(Color.YELLOW);
                result.setText("Color is YELLOW");
            } else{
                result.setText("Please Choose the Color!");
            }
        });
    }
}