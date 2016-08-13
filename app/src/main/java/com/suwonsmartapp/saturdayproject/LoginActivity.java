package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        findViewById(R.id.login_button).setOnClickListener(this);

        if (getIntent() != null) {
            Toast.makeText(LoginActivity.this, getIntent().getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", mIdEditText.getText().toString());
        startActivity(intent);
    }
}
