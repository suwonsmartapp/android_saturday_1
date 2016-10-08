package com.suwonsmartapp.saturdayproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * Started Service
 */
public class MyService extends Service {
    private static String TAG = MyService.class.getSimpleName();

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String message = intent.getStringExtra("message");

        if (intent.getAction().equals("randomNumber")) {
            randomNumber();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.d(TAG, "MyService: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return START_NOT_STICKY;
    }

    public int randomNumber() {
        return new Random().nextInt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceBinder();
    }


    public class MyServiceBinder extends Binder {
        public MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }
}
