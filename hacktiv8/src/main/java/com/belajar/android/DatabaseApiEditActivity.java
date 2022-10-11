package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class DatabaseApiEditActivity extends AppCompatActivity {

    TextView et_noinduk,et_nama,et_alamat,et_hobi;
    String noinduk,nama,alamat,hobi;
    Button btn_submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_api_edit);

        et_noinduk          = findViewById(R.id.et_noinduk);
        et_nama             = findViewById(R.id.et_nama);
        et_alamat           = findViewById(R.id.et_alamat);
        et_hobi             = findViewById(R.id.et_hobi);
        btn_submit          = findViewById(R.id.btn_submit);

        progressDialog      = new ProgressDialog(this);

        getDataIntent();

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

    void getDataIntent(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            et_noinduk.setText(bundle.getString("noinduk"));
            et_nama.setText(bundle.getString("nama"));
            et_alamat.setText(bundle.getString("alamat"));
            et_hobi.setText(bundle.getString("hobi"));
        }else{
            et_noinduk.setText("");
            et_nama.setText("");
            et_alamat.setText("");
            et_hobi.setText("");
        }
    }

    void validasiData(){
        if(noinduk.equals("") || nama.equals("") || alamat.equals("") || hobi.equals("")){
            progressDialog.dismiss();
            Toast.makeText(DatabaseApiEditActivity.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            updateData();
        }
    }

    void updateData(){
        AndroidNetworking.post("https://sibeux.my.id/database/android_crud/updateSiswa.php")
                .addBodyParameter("noinduk",""+noinduk)
                .addBodyParameter("nama",""+nama)
                .addBodyParameter("alamat",""+alamat)
                .addBodyParameter("hobi",""+hobi)
                .setTag("Update Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("responEdit",""+response);
                        try{
                            boolean status = response.getBoolean("status");
                            if(status){
                                new AlertDialog.Builder(DatabaseApiEditActivity.this)
                                        .setMessage("Berhasil Mengupdate Data")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", (dialog, which) -> {
                                            Intent i = getIntent();
                                            setResult(RESULT_OK,i);
                                            DatabaseApiEditActivity.this.finish();
                                        })
                                        .show();
                            }else{
                                new AlertDialog.Builder(DatabaseApiEditActivity.this)
                                        .setMessage("Gagal Mengupdate Data")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", (dialog, which) -> {
                                            Intent i = getIntent();
                                            setResult(RESULT_CANCELED,i);
                                            DatabaseApiEditActivity.this.finish();
                                        })
                                        .show();
                            }
                        }catch (Exception e){
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