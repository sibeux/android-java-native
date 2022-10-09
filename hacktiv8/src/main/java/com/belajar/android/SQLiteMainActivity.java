package com.belajar.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SQLiteMainActivity extends AppCompatActivity implements View.OnClickListener,
        CountryAdapterSQLite.EditTextListener, CountryAdapterSQLite.RemoveTextListener{

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
            popUpFormCountry("ADD NEW COUNTRY", 1,"","");
        }
    }

    void popUpFormCountry(String title, int id,String name, String pop){

        AlertDialog.Builder popUpBuilder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.form_country,null);

        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);

        TextView countryNameInput = view.findViewById(R.id.countryNameInput);
        TextView countryPopulationInput = view.findViewById(R.id.populationInput);
        Button saveButton = view.findViewById(R.id.saveButton);

        if (title.contains("Edit")){
            countryNameInput.setText(name);
            countryPopulationInput.setText(pop);
        }

        popUpBuilder.setView(view);

        AlertDialog popupForm  = popUpBuilder.create();
        popupForm.show();

        saveButton.setOnClickListener(v -> {
            // insert data

            String countryName = countryNameInput.getText().toString().replaceAll(" ","");
            String countryPopulation = countryPopulationInput.getText().toString().replaceAll(" ","");

            if (countryName.length() ==0){
                countryNameInput.setError("Country name cannot empty");
            } else if (countryPopulation.length() == 0){
                countryPopulationInput.setError("Population cannot empty");
            } else{
                CountrySQLite countrySQLite = new CountrySQLite(countryName.trim(), Long.parseLong(countryPopulation.trim()));

                if(title.contains("ADD")){
                    db.addCountry(countrySQLite);
                    Toast.makeText(this,"Add Data Successful",Toast.LENGTH_SHORT).show();
                    loadDataCountry();
                } else {
                    db.editCountry(countrySQLite,id);
                    Toast.makeText(this,"Edit Data Successful",Toast.LENGTH_SHORT).show();
                    loadDataCountry();
                }
                popupForm.dismiss();
            }
        });
    }

    private void removeCountry(String name,int id){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Remove Country")
                .setMessage("Do you want to remove "+name+"?")
                .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                .setPositiveButton("Yes", (dialog, which) -> {
                    db.removeCountry(id);
                    Toast.makeText(this,"Remove Data Successful",Toast.LENGTH_SHORT).show();
                    loadDataCountry();
                }).show();
        alertDialog.create();
    }

    private void loadDataCountry(){
        countrySQLiteList = db.getAllCountries();
        CountryAdapterSQLite countryAdapter = new CountryAdapterSQLite(countrySQLiteList, this, this);
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void onEditTextClick(int position) {
        CountrySQLite countrySQLite = countrySQLiteList.get(position);
        Toast.makeText(this,"Edit Data " + countrySQLite.getCountryName(),Toast.LENGTH_SHORT).show();
        popUpFormCountry("Edit Data "+countrySQLite.getCountryName(),countrySQLite.getId(),countrySQLite.getCountryName(),
                String.valueOf(countrySQLite.getPopulations()));
    }

    @Override
    public void onRemoveTextClick(int position) {
        CountrySQLite countrySQLite = countrySQLiteList.get(position);
        removeCountry(countrySQLite.getCountryName(),countrySQLite.id);
    }
}