package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

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

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (male.isChecked()){
                    result.setText("Male");
                    female.setChecked(false);
                } else{
                    result.setText("What tf your gender?");
                }
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (female.isChecked()){
                    result.setText("Female");
                    male.setChecked(false);
                } else{
                    result.setText("What tf your gender?");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = name.getText().toString();
                if (username.length() == 0){
                    result.setText("Who tf are you?");
                } else{
                    result.setText("Selamat Datang "+username);
                }
                image.setImageResource(R.drawable.square);
            }
        });
    }
}