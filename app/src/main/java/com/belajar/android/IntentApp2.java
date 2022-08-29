package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class IntentApp2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_app2);

        TextView nilaiTextView = findViewById(R.id.textIntenApp2);

        Bundle data = getIntent().getExtras();
        int result = data.getString("fullname").length();
        if (result == 0){
            nilaiTextView.setText("What your name buddy?");
        } else{
            nilaiTextView.setText("Welcome "+data.getString("fullname"));
        }
    }
}