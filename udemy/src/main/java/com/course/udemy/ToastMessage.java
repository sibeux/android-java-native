package com.course.udemy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ToastMessage extends AppCompatActivity {
    ImageView insta;
    TextView text;
    ToggleButton onOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_message);

        insta = findViewById(R.id.insta);
        text = findViewById(R.id.textView);
        onOff = findViewById(R.id.onOff);

        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    text.setText("Image Show");
                    insta.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Image show",Toast.LENGTH_SHORT).show();
                } else{
                    text.setText("Image Hide");
                    insta.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Image hide",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}