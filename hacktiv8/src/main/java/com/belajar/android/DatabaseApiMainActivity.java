package com.belajar.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.belajar.android.database.api.StudentRVAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseApiMainActivity extends AppCompatActivity {

    SwipeRefreshLayout srl_main;
    RecyclerView rv_main;
    ArrayList<String> array_nama,array_noinduk,array_alamat,array_hobi;
    ProgressDialog progressDialog;

    StudentRVAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_api_main2);
        srl_main    = findViewById(R.id.srl_main);
        rv_main     = findViewById(R.id.rv_main);
        progressDialog = new ProgressDialog(this);

        rv_main.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_main.setLayoutManager(layoutManager);

        srl_main.setOnRefreshListener(() -> {
            scrollRefresh();
            srl_main.setRefreshing(false);
        });

        scrollRefresh();
    }

    public void scrollRefresh(){
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },1200);
    }

    void initializeArray(){
        array_noinduk = new ArrayList<>();
        array_nama = new ArrayList<>();
        array_alamat = new ArrayList<>();
        array_hobi = new ArrayList<>();

        array_noinduk.clear();
        array_nama.clear();
        array_alamat.clear();
        array_hobi.clear();
    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://sibeux.my.id/database/android_crud/getData.php")
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);

                                    array_noinduk.add(jo.getString("noinduk"));
                                    array_nama.add(jo.getString("nama"));
                                    array_alamat.add(jo.getString("alamat"));
                                    array_hobi.add(jo.getString("hobi"));
                                }
                                recycleViewAdapter = new StudentRVAdapter(DatabaseApiMainActivity.this,array_noinduk,array_nama,array_alamat,array_hobi);
                                rv_main.setAdapter(recycleViewAdapter);
                            }else{
                                Toast.makeText(DatabaseApiMainActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                                recycleViewAdapter = new StudentRVAdapter(DatabaseApiMainActivity.this,array_noinduk,array_nama,array_alamat,array_hobi);
                                rv_main.setAdapter(recycleViewAdapter);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutambah,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_add){
            Intent i = new Intent(DatabaseApiMainActivity.this,DatabaseApiAddActivity.class);
            startActivityForResult(i,1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode==2){
            if(resultCode==RESULT_OK){
                scrollRefresh();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
