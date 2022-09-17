package com.belajar.android;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class BackgroundServices extends IntentService {
    private static final String TAG = BackgroundServices.class.getName();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BackgroundServices(String name) {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int sleep = intent.getIntExtra("sleep",1);

        int counter = 0;
        while (counter < sleep){
            Log.i(TAG,"Thread = "+Thread.currentThread().getName()+" "+counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }
}
