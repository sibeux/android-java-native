package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class DatabaseApiAddActivity extends AppCompatActivity {

    TextView et_noinduk,et_nama,et_alamat,et_hobi;
    String noinduk,nama,alamat,hobi;
    Button btn_submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_api_add);

        et_noinduk          = findViewById(R.id.et_noinduk);
        et_nama             = findViewById(R.id.et_nama);
        et_alamat           = findViewById(R.id.et_alamat);
        et_hobi             = findViewById(R.id.et_hobi);
        btn_submit          = findViewById(R.id.btn_submit);

        progressDialog      = new ProgressDialog(this);

        btn_submit.setOnClickListener(v -> {
            progressDialog.setMessage("Menambahkan Data...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            noinduk = et_noinduk.getText().toString();
            nama = et_nama.getText().toString();
            alamat = et_alamat.getText().toString();
            hobi = et_hobi.getText().toString();

            new Handler().postDelayed(this::validasiData,1200);
        });
    }

    void validasiData(){
        if(noinduk.equals("") || nama.equals("") || alamat.equals("") || hobi.equals("")){
            progressDialog.dismiss();
            Toast.makeText(DatabaseApiAddActivity.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://sibeux.my.id/database/android_crud/tambahSiswa.php") //sesuaikan dengan api server kalian
                .addBodyParameter("noinduk",""+noinduk)
                .addBodyParameter("nama",""+nama)
                .addBodyParameter("alamat",""+alamat)
                .addBodyParameter("hobi",""+hobi)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(DatabaseApiAddActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(DatabaseApiAddActivity.this)
                                        .setMessage("Berhasil Menambahkan Data !")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", (dialog, which) -> {
                                            Intent i = getIntent();
                                            setResult(RESULT_OK,i);
                                            DatabaseApiAddActivity.this.finish();
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(DatabaseApiAddActivity.this)
                                        .setMessage("Gagal Menambahkan Data !")
                                        .setPositiveButton("Kembali", (dialog, which) -> {
                                            Intent i = getIntent();
                                            setResult(RESULT_CANCELED,i);
                                            DatabaseApiAddActivity.this.finish();
                                        })
                                        .setCancelable(false)
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu_back){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}