package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private EditText mEmailEditText;
    private RadioButton mMaleRadio;
    private RadioButton mFemaleRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        // 가입
        findViewById(R.id.submit_button).setOnClickListener(this);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit);
        mEmailEditText = (EditText) findViewById(R.id.email_edit);

        mMaleRadio = (RadioButton) findViewById(R.id.male_radio);
        mFemaleRadio = (RadioButton) findViewById(R.id.female_radio);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_button:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", mIdEditText.getText().toString());
                intent.putExtra("password", mPasswordEditText.getText().toString());
                intent.putExtra("email", mEmailEditText.getText().toString());
                intent.putExtra("sex", mMaleRadio.isChecked() ? "남성" : "여성");
                startActivityForResult(intent, 1000);
                break;
            case R.id.reset_button:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1000) {
            Toast.makeText(LoginActivity.this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }
}
