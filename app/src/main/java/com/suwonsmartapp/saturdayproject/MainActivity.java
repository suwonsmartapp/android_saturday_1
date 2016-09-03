package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.suwonsmartapp.saturdayproject.list.ListExamActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
}
