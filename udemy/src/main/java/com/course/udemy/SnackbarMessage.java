package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarMessage extends AppCompatActivity {

    ImageView insta;
    TextView text;
    ToggleButton onOff;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_message);

        insta = findViewById(R.id.insta);
        text = findViewById(R.id.textView);
        onOff = findViewById(R.id.onOff);

        linearLayout = findViewById(R.id.linear);

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    text.setText("Image Show");
                    insta.setVisibility(View.VISIBLE);
                    Snackbar.make(linearLayout, "Image Show",Snackbar.LENGTH_INDEFINITE)
                            .setAction("Close", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                } else{
                    text.setText("Image Hide");
                    insta.setVisibility(View.INVISIBLE);
                    Snackbar.make(linearLayout,"Image Hide",Snackbar.LENGTH_SHORT)
                            .setAction("Close", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                }
            }
        });
    }
}