package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.CheckBox;

public class CheckButton extends AppCompatActivity {

    TextView name;
    TextView result;
    View button;
    ImageView image;
    String username;
    CheckBox male,female;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_button);

        name = findViewById(R.id.name);
        result = findViewById(R.id.result);
        button = findViewById(R.id.button);
        image = findViewById(R.id.imagesView);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        male.setOnClickListener(v -> {
            if (male.isChecked()){
                result.setText("Male");
                female.setChecked(false);
            } else{
                result.setText("What tf your gender?");
            }
        });

        female.setOnClickListener(v -> {
            if (female.isChecked()){
                result.setText("Female");
                male.setChecked(false);
            } else{
                result.setText("What tf your gender?");
            }
        });

        button.setOnClickListener(v -> {
            username = name.getText().toString();
            if (username.length() == 0){
                result.setText("Who tf are you?");
            } else{
                result.setText("Selamat Datang "+username);
            }
            image.setImageResource(R.drawable.square);
        });
    }
}