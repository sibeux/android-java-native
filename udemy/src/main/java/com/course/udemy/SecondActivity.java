package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = findViewById(R.id.goBackButton);

        button.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), ActivityLifecycle.class);
            startActivity(intent);
        });
        Log.d("Message", "Second Activity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Message","First Application onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Message", "Second Application onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Message", "Second Application onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Message", "Second Application onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Message", "Second Application onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Message", "Second Application onRestart");
    }
}