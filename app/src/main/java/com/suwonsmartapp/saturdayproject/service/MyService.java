package com.suwonsmartapp.saturdayproject.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.suwonsmartapp.saturdayproject.R;

import java.util.Random;

/**
 * Started Service
 */
public class MyService extends Service {
    private static String TAG = MyService.class.getSimpleName();

    public interface MyServiceListener {
        void onFinished(int randomNumber);
    }

    MyServiceListener mListener;

    public void setOnMyServiceListener(MyServiceListener listener) {
        mListener = listener;
    }

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String message = intent.getStringExtra("message");

        if (intent.getAction().equals("randomNumber")) {
            randomNumber();
        } else if (intent.getAction().equals("foreground")) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("서비스 실행 중");
            builder.setContentText("포어그라운드 서비스 ...");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            builder.setLargeIcon(bitmap);
            builder.setColor(Color.RED);
            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            builder.setVibrate(new long[] {1000, 2000, 3000});

            startForeground(1, builder.build());

            return START_REDELIVER_INTENT;
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
                if (mListener != null) {
                    mListener.onFinished(randomNumber());
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
