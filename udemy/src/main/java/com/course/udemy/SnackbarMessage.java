package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarMessage extends AppCompatActivity {

    ImageView picture;
    TextView text;
    ToggleButton onOff;
    LinearLayout linearLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_message);

        picture = findViewById(R.id.insta);
        text = findViewById(R.id.textView);
        onOff = findViewById(R.id.onOff);

        linearLayout = findViewById(R.id.linear);

        onOff.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                text.setText("Image Show");
                picture.setVisibility(View.VISIBLE);
                Snackbar.make(linearLayout, "Image Show",Snackbar.LENGTH_INDEFINITE)
                        .setAction("Close", v -> {

                        }).show();
            } else{
                text.setText("Image Hide");
                picture.setVisibility(View.INVISIBLE);
                Snackbar.make(linearLayout,"Image Hide",Snackbar.LENGTH_SHORT)
                        .setAction("Close", v -> {

                        }).show();
            }
        });
    }
}