package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class ImagesView extends AppCompatActivity {

    TextView name;
    TextView result;
    View button;
    ImageView image;
    String username;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_view);

        name = findViewById(R.id.name);
        result = findViewById(R.id.result);
        button = findViewById(R.id.button);
        image = findViewById(R.id.imagesView);

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