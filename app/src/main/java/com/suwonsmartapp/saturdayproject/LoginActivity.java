package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", mIdEditText.getText().toString());
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1000) {
            Toast.makeText(LoginActivity.this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }
}
