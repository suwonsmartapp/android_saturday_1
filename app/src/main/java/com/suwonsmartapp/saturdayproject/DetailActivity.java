package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViewById(R.id.result_button).setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            String email = intent.getStringExtra("email");
            String password = intent.getStringExtra("password");
            String sex = intent.getStringExtra("sex");

            Log.d(TAG, "onCreate: " + id + ", " + email + ", " + password + ", " + sex);
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", "확인 버튼을 누르셨습니다");
        setResult(RESULT_OK, intent);
        finish();
    }
}
