package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ToastMessage extends AppCompatActivity {
    ImageView picture;
    TextView text;
    ToggleButton onOff;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_message);

        picture = findViewById(R.id.insta);
        text = findViewById(R.id.textView);
        onOff = findViewById(R.id.onOff);

        onOff.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                text.setText("Image Show");
                picture.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Image show",Toast.LENGTH_SHORT).show();
            } else{
                text.setText("Image Hide");
                picture.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Image hide",Toast.LENGTH_SHORT).show();
            }
        });
    }
}