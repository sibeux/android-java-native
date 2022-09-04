package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IntentApp extends AppCompatActivity implements View.OnClickListener{

    Button explicitButton, implicitButton;
    TextView fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_app);

        fullname = findViewById(R.id.fullname);

        explicitButton = findViewById(R.id.explicitButton);
        explicitButton.setOnClickListener(this);

        implicitButton = findViewById(R.id.implicitButton);
        implicitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.explicitButton){
            Toast.makeText(this,"Klik Explicit Button",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,IntentApp2.class);
            intent.putExtra("nilai","100");
            intent.putExtra("fullname",fullname.getText().toString());
            startActivity(intent);
        } else if(v.getId() == R.id.implicitButton) {
            Toast.makeText(this,"Klik Implicit Button",Toast.LENGTH_SHORT).show();

            //            website intent
            Uri webpage = Uri.parse("https://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(webIntent);

//            phone intent
//            Uri number = Uri.parse("tel:5551234");
//            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        }
    }
}