package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Bligh-Park on 2016. 8. 13..
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Toast.makeText(DetailActivity.this, id, Toast.LENGTH_SHORT).show();

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("result", "id 잘 받았어요");
//        startActivity(intent);

        setResult(RESULT_OK, intent);
        finish();
    }
}
