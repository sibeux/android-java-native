package com.belajar.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.belajar.android.covid.tracker.app.CovidRetrofitAPI;
import com.belajar.android.covid.tracker.app.CovidRetrofitAdapter;
import com.belajar.android.covid.tracker.app.CovidRetrofitModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidTrackerAppMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<CovidRetrofitModelClass> data;
    CovidRetrofitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tracker_app_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /***
         * Maaf Pak, ini saya memakai API yang berbeda
         * Soalnya jika memakai yang di kode.id, itu tidak tau mengapa tiba-tiba force close Pak
         * Lalu saya menemukan yang API ini tapi versi USA pak, sekali lagi mohon maaf Pak...
         */

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.covidtracking.com/v1/us/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidRetrofitAPI retrofitAPI = retrofit.create(CovidRetrofitAPI.class);

        Call<List<CovidRetrofitModelClass>> call = retrofitAPI.getModelClass();

        call.enqueue(new Callback<List<CovidRetrofitModelClass>>() {
            @Override
            public void onResponse(@NonNull Call<List<CovidRetrofitModelClass>> call, @NonNull Response<List<CovidRetrofitModelClass>> response) {

                data = response.body();
                adapter = new CovidRetrofitAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<CovidRetrofitModelClass>> call, @NonNull Throwable t) {
                Toast.makeText(CovidTrackerAppMainActivity.this,"There is an error"
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}