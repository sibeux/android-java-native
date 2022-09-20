package com.course.udemy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRecyclerView extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<RetrofitModelClass> data;
    RetrofitRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        Call<List<RetrofitModelClass>> call = retrofitApi.getModelClass();

        call.enqueue(new Callback<List<RetrofitModelClass>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<RetrofitModelClass>> call, @NonNull Response<List<RetrofitModelClass>> response) {

                data = response.body();
                adapter = new RetrofitRecyclerAdapter(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(@NonNull Call<List<RetrofitModelClass>> call, @NonNull Throwable t) {

                Toast.makeText(RetrofitRecyclerView.this,"There is an error"
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}