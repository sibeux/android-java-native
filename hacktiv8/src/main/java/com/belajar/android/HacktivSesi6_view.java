package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HacktivSesi6_view extends AppCompatActivity {

    TextView nama, alamat, umur, kelamin, universitas, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacktiv_sesi6_view);

        nama = findViewById(R.id.namaLengkap);
        alamat = findViewById(R.id.alamat);
        umur = findViewById(R.id.umur);
        kelamin = findViewById(R.id.kelamin);
        universitas = findViewById(R.id.universitas);
        jurusan = findViewById(R.id.jurusan);

        Bundle data = getIntent().getExtras();
        nama.setText(data.getString("namaLengkap"));
        alamat.setText(data.getString("alamat"));
        umur.setText(data.getString("umur"));
        kelamin.setText(data.getString("kelamin"));
        universitas.setText(data.getString("universitas"));
        jurusan.setText(data.getString("jurusan"));
    }
}