package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button init = (Button) findViewById(R.id.init);
        Button apply = (Button) findViewById(R.id.apply);
        apply.setOnClickListener(this);
        init.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.init:
                Toast.makeText(LoginActivity.this, "초기화 하겠습니다", Toast.LENGTH_SHORT).show();
                break;
            case R.id.apply:
                Toast.makeText(LoginActivity.this, "가입합니다", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
