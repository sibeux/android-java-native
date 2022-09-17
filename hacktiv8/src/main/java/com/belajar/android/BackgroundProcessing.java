package com.belajar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BackgroundProcessing extends AppCompatActivity {

    Button download;
    private static final String TAG = BackgroundProcessing.class.getName();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_processing);

        download = findViewById(R.id.buttonDownload);
        TextView textView = findViewById(R.id.status);

        download.setOnClickListener(v -> {
            //
            textView.setText("Download Start");
            final Handler handler = new Handler();
            Runnable runnable = () -> {

                Log.i(TAG, "Download");
                synchronized (this){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.i(TAG, "Download Completed");
                handler.post(() -> textView.setText("Download Completed"));
            };

            Thread thread = new Thread(runnable);
            thread.start();
        });
    }

    public void onClick(View v){

    }

    private void runThread(){

    }
}