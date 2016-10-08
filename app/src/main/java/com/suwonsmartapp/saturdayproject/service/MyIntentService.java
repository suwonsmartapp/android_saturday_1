package com.suwonsmartapp.saturdayproject.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Intent Service
 */
public class MyIntentService extends IntentService {
    private static String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, "MyService: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
