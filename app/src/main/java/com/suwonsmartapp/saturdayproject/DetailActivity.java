package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mId;
    private TextView mPassword;
    private TextView mEmail;
    private TextView mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String password = intent.getStringExtra("password");
        String email = intent.getStringExtra("email");
        String gender = intent.getStringExtra("gender");

        mId = (TextView) findViewById(R.id.id);
        mPassword = (TextView) findViewById(R.id.password);
        mEmail = (TextView) findViewById(R.id.email);
        mGender = (TextView) findViewById(R.id.gender);

        mId.setText(id);
        mPassword.setText(password);
        mEmail.setText(email);
        mGender.setText(gender);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.result:

                Toast.makeText(DetailActivity.this, "하하하", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
//                intent.putExtra("result", "확인 버튼을 누르셨습니다.");
//
//                finish();
                break;
        }
    }
}
