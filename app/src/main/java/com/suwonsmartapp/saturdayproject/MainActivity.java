package com.suwonsmartapp.saturdayproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.suwonsmartapp.saturdayproject.db.MemoActivity;
import com.suwonsmartapp.saturdayproject.list.ListExamActivity;
import com.suwonsmartapp.saturdayproject.provider.ProviderActivity;
import com.suwonsmartapp.saturdayproject.service.MyIntentService;
import com.suwonsmartapp.saturdayproject.service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyService.MyServiceListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text_view2);

        // 1 첫번째
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        });

        // 2
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        };

        button.setOnClickListener(listener);

        // 3
        button.setOnClickListener(this);


        // 암시적 인텐트로 전화걸기
        findViewById(R.id.tel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button1 = (Button) view;
                dialPhoneNumber(button1.getText().toString());
            }
        });


        findViewById(R.id.list_button).setOnClickListener(this);
        findViewById(R.id.lifecycle_button).setOnClickListener(this);

    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "없어요", Toast.LENGTH_SHORT).show();
        }
    }


    // 3
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mTextView.setText("버튼 눌림");

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view1:
                break;
            case R.id.list_button:
                startActivity(new Intent(this, ListExamActivity.class));
                break;
            case R.id.lifecycle_button:
                startActivity(new Intent(this, LifeCycleActivity.class));
                break;
        }
    }

    public void json(View view) {
        startActivity(new Intent(this, JsonActivity.class));
    }

    public void weather(View view) {
        startActivity(new Intent(this, WeatherActivity.class));
    }

    public void memo(View view) {
        startActivity(new Intent(this, MemoActivity.class));
    }

    public void provider(View view) {
        startActivity(new Intent(this, ProviderActivity.class));
    }

    public void broadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("com.suwonsmartapp.saturdayproject.action.TEST_BROADCAST");
        sendBroadcast(intent);
    }

    private MyService mService;
    boolean mBound = false;

    @Override
    protected void onStart() {
        super.onStart();

        // Bind to LocalService
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;

            stopService(new Intent(this, MyService.class));
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyService.MyServiceBinder binder = (MyService.MyServiceBinder) service;
            mService = binder.getService();
            mService.setOnMyServiceListener(MainActivity.this);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void startedService(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("message", "test");
        intent.setAction("randomNumber");
        startService(intent);
    }

    public void intentService(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    public void bindService(View view) {
        if (mBound) {
            Log.d(TAG, "bindService: " + mService.randomNumber());
        }
    }

    @Override
    public void onFinished(int randomNumber) {
        Log.d(TAG, "onFinished: " + randomNumber);
    }

    public void foregroundService(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction("foreground");
        startService(intent);
    }
}
