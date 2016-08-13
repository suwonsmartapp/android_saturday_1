package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


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
        startActivity(intent);
    }
}
