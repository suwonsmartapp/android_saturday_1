package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdEditText;
    private EditText mPassWord;
    private EditText mConfirm;
    private EditText mEmail;
    private RadioButton mRadioMale;
    static final int RESULT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.apply).setOnClickListener(this);
        findViewById(R.id.init).setOnClickListener(this);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPassWord = (EditText) findViewById(R.id.password_edit);
        mConfirm = (EditText) findViewById(R.id.password_confirm);
        mEmail = (EditText) findViewById(R.id.email);
        mRadioMale = (RadioButton) findViewById(R.id.radio_male);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.init:
                Toast.makeText(LoginActivity.this, "초기화 하겠습니다", Toast.LENGTH_SHORT).show();
                break;
            case R.id.apply:
                if( !mPassWord.getText().toString().equals(mConfirm.getText().toString())  ){
                    Toast.makeText(LoginActivity.this, "비밀번호가 같지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, DetailActivity.class);

                    intent.putExtra("id", mIdEditText.getText().toString());
                    intent.putExtra("password", mPassWord.getText().toString());
                    intent.putExtra("email", mEmail.getText().toString());
                    intent.putExtra("gender", mRadioMale.isChecked() ? "남성" : "여성");

                    startActivityForResult(intent, RESULT_REQUEST);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == RESULT_REQUEST  && resultCode == RESULT_OK ) {
            Toast.makeText(LoginActivity.this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }

}
