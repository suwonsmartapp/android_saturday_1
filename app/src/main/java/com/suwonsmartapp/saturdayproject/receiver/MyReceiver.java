package com.suwonsmartapp.saturdayproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.suwonsmartapp.saturdayproject.action.TEST_BROADCAST")) {
            Toast.makeText(context, "방송이 수신 됨", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "베터리가 없음", Toast.LENGTH_SHORT).show();
        }
    }
}
