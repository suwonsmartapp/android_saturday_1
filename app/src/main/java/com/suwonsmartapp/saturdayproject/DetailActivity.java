package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class DetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String password = intent.getStringExtra("password");
        String email = intent.getStringExtra("email");

        Toast.makeText(DetailActivity.this, id, Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailActivity.this, password, Toast.LENGTH_SHORT).show();
        Toast.makeText(DetailActivity.this, email, Toast.LENGTH_SHORT).show();
//        findViewById(R.id.button).setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(this, LoginActivity.class);
//
//        finish();
//    }
}
