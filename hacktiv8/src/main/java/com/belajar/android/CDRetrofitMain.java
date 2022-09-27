package com.belajar.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.belajar.android.covid.disease.api.CDAPI;
import com.belajar.android.covid.disease.api.CDAdapter;
import com.belajar.android.covid.disease.api.CDModelClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CDRetrofitMain extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CDModelClass data;
    CDAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdretrofit_main);

        recyclerView = findViewById(R.id.recyclerViewCD);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://disease.sh/v3/covid-19/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CDAPI cdapi = retrofit.create(CDAPI.class);

        Call<CDModelClass> call = cdapi.getModelClass();

        call.enqueue(new Callback<CDModelClass>() {
            @Override
            public void onResponse(@NonNull Call<CDModelClass> call, @NonNull Response<CDModelClass> response) {
                data = response.body();
                adapter = new CDAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<CDModelClass> call, @NonNull Throwable t) {
                Toast.makeText(CDRetrofitMain.this,"There is an error"
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}