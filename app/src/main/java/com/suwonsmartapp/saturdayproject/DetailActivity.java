package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Toast.makeText(DetailActivity.this, id, Toast.LENGTH_SHORT).show();
    }
}
