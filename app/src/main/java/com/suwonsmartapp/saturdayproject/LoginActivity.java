package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdEditText;
    private EditText mPassWord;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPassWord = (EditText) findViewById(R.id.password_edit);
        mEmail = (EditText) findViewById(R.id.email);

        findViewById(R.id.apply).setOnClickListener(this);
        findViewById(R.id.init).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.init:
                Toast.makeText(LoginActivity.this, "초기화 하겠습니다", Toast.LENGTH_SHORT).show();
                break;
            case R.id.apply:
//                Toast.makeText(LoginActivity.this, "가입합니다", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, DetailActivity.class);
//                intent.putExtra("id", mIdEditText.getText().toString());
//                intent.putExtra("password", mPassWord.getText().toString());
//                intent.putExtra("email", mEmail.getText().toString());

                startActivity(intent);
                break;
        }
    }

}
