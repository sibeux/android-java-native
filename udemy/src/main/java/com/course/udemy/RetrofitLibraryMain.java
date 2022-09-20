package com.course.udemy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLibraryMain extends AppCompatActivity {

    TextView texta,textb,textc,textd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_library_main);

        texta = findViewById(R.id.textViewA);
        textb = findViewById(R.id.textViewB);
        textc = findViewById(R.id.textViewC);
        textd = findViewById(R.id.textViewD);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        Call<List<RetrofitModelClass>> call = retrofitApi.getModelClass();

        call.enqueue(new Callback<List<RetrofitModelClass>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<RetrofitModelClass>> call, @NonNull Response<List<RetrofitModelClass>> response) {

                if (!response.isSuccessful()){
                    texta.setText("Error");
                    textb.setText("Error");
                    textc.setText("Error");
                    textd.setText("Error");
                }
                List<RetrofitModelClass> data;
                data = response.body();

                assert data != null;
                texta.setText(""+data.get(0).getUserID());
                textb.setText(""+data.get(0).getID());
                textc.setText(""+data.get(0).getTitle());
                textd.setText(""+data.get(0).getSubTitle());
            }

            @Override
            public void onFailure(@NonNull Call<List<RetrofitModelClass>> call, @NonNull Throwable t) {

                Toast.makeText(RetrofitLibraryMain.this,"There is an error"
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }
}