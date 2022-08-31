package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class HacktivSesi6_main extends AppCompatActivity implements View.OnClickListener{

    TextView namaLengkap, alamat, umur, universitas, jurusan;
    Button submit;
    RadioGroup kelamin;
    RadioButton pria, wanita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacktiv_sesi6);

        namaLengkap = findViewById(R.id.namaLengkap);
        alamat = findViewById(R.id.alamat);
        umur = findViewById(R.id.umur);
        universitas = findViewById(R.id.universitas);
        jurusan = findViewById(R.id.jurusan);
        submit = findViewById(R.id.submit_area);
        submit.setOnClickListener(this);
        kelamin = findViewById(R.id.kelamin);
        pria = findViewById(R.id.radioMale);
        wanita = findViewById(R.id.radioFemale);
    }

    @Override
    public void onClick(View v) {
        if ((namaLengkap.getText().toString().replaceAll(" ","")).length()==0){
            namaLengkap.setError("Nama tidak boleh kosong!");
        }else if (alamat.getText().toString().replaceAll(" ","").length()==0){
            alamat.setError("Alamat tidak boleh kosong!");
        }else if (umur.getText().toString().length()==0){
            umur.setError("Umur tidak boleh kosong!");
        }else if (universitas.getText().toString().replaceAll(" ","").length()==0){
            universitas.setError("Universitas tidak boleh kosong!");
        }else if (jurusan.getText().toString().replaceAll(" ","").length()==0){
            jurusan.setError("Jurusan tidak boleh kosong!");
        }else if (kelamin.getCheckedRadioButtonId() == -1){
            pria.setError("Pilih salah satu!");
            wanita.setError("pilih salah satu!");
        } else{
            pria.setError(null);
            wanita.setError(null);
            Toast.makeText(getApplicationContext(), "Registrasi Berhasil!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,HacktivSesi6_view.class);
            intent.putExtra("namaLengkap",namaLengkap.getText().toString().trim());
            intent.putExtra("alamat",alamat.getText().toString().trim());
            intent.putExtra("umur",umur.getText().toString());
            intent.putExtra("universitas",universitas.getText().toString().trim());
            intent.putExtra("jurusan",jurusan.getText().toString().trim());
            String kelamin = pria.isChecked() ? "Laki-laki" : "Perempuan";
            intent.putExtra("kelamin",kelamin);
            startActivity(intent);
        }
    }
}