package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLifecycle extends AppCompatActivity {

    TextView textView;
    Button button1;
    Button button2;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(v -> {
            counter += 1;
            textView.setText("" + counter);
        });

        button2.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
        });

        Log.d("Message", "First Application onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Message","First Application onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Message", "First Application onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Message", "First Application onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Message", "First Application onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Message", "First Application onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Message", "First Application onRestart");
    }
}