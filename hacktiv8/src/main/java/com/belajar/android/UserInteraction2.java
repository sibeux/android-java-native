package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInteraction2 extends AppCompatActivity {

    TextView detailTeam;
    ImageView logoTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interaction2);

        detailTeam = findViewById(R.id.nama_team);
        logoTeam = findViewById(R.id.logo_team);

        Bundle data = getIntent().getExtras();
        detailTeam.setText(data.getString("namaTeam"));
        int logo = data.getInt("logoTeam");
        logoTeam.setImageResource(logo);
    }


}