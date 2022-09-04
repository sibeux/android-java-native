package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditText extends AppCompatActivity {

    TextView name;
    TextView result;
    View button;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        name = findViewById(R.id.name);
        result = findViewById(R.id.result);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = name.getText().toString();
                result.setText("Selamat Datang "+username);
            }
        });
    }
}