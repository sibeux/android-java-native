package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListView extends AppCompatActivity {

    android.widget.ListView listView;
    String[] countries;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.countries);
        countries = getResources().getStringArray(R.array.countries);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String country_name = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), "You selected "+country_name,Toast.LENGTH_LONG).show();
        });
    }
}