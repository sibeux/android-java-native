package com.belajar.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SQLiteMainActivity extends AppCompatActivity implements View.OnClickListener,CountryAdapterSQLite.EditTextListener, CountryAdapterSQLite.RemoveTextListener{

    RecyclerView recyclerView;
    Button addButton;

    DatabaseHelperSQLite db;
    List<CountrySQLite> countrySQLiteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_main);

        db = new DatabaseHelperSQLite(this);

        recyclerView = findViewById(R.id.country_list_rv);
        addButton = findViewById(R.id.buttonNewCountry);
        addButton.setOnClickListener(this);

        loadDataCountry();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNewCountry){
            // add data
            Toast.makeText(SQLiteMainActivity.this, "Add data", Toast.LENGTH_LONG).show();
            popUpFormCountry("TAMBAH DATA NEGARA");
        }
    }

    void popUpFormCountry(String title){

        AlertDialog.Builder popUpBuilder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.form_country,null);

        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);

        TextView countryNameInput = view.findViewById(R.id.countryNameInput);
        TextView countryPopulationInput = view.findViewById(R.id.populationInput);
        Button saveButton = view.findViewById(R.id.saveButton);

        popUpBuilder.setView(view);

        AlertDialog popupForm  = popUpBuilder.create();
        popupForm.show();

        saveButton.setOnClickListener(v -> {
            // insert data

            String countryName = countryNameInput.getText().toString();
            String countryPopulation = countryPopulationInput.getText().toString();

            CountrySQLite countrySQLite = new CountrySQLite(countryName, Long.parseLong(countryPopulation));

            db.addCountry(countrySQLite);

            loadDataCountry();

            popupForm.dismiss();
        });
    }

    private void loadDataCountry(){
        countrySQLiteList = db.getAllCountries();
        CountryAdapterSQLite countryAdapter = new CountryAdapterSQLite(this, countrySQLiteList, this, this);
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void onEditTextClick(int position) {
        CountrySQLite countrySQLite = countrySQLiteList.get(position);
        Toast.makeText(this,"edit country " + countrySQLite.getCountryName(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRemoveTextClick(int position) {
        CountrySQLite countrySQLite = countrySQLiteList.get(position);
        Toast.makeText(this,"remove "+countrySQLite.getCountryName(),Toast.LENGTH_LONG).show();
    }
}