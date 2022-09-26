package com.course.udemy;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServicesExample extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ServicesExample() {
        super("name");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("Service","Service is created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Service","Service is started");
        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("Service","Service is stopped");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("Service","onHandleIntent Method Worked");
    }
}
