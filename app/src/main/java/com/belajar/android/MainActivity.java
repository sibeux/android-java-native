package com.belajar.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView form;
    public static final String myPreference = "pref";
    public static final String Form = "former";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        form = findViewById(R.id.formText);
        sharedPreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Form)){
            form.setText(sharedPreferences.getString(Form,""));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart()",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop()",Toast.LENGTH_LONG).show();
//         save isi dari input // test
        String f = form.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Form, f);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause()",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume()",Toast.LENGTH_LONG).show();
        // call data yg saved dan showing
        form = findViewById(R.id.formText);
        sharedPreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);
        if (sharedPreferences.contains(Form)){
            form.setText(sharedPreferences.getString(Form,""));
        }
    }
}