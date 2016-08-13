package com.suwonsmartapp.saturdayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text_view2);

        // 1 첫번째
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        });

        // 2
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "버튼 눌림", Toast.LENGTH_SHORT).show();
            }
        };

        button.setOnClickListener(listener);

        // 3
        button.setOnClickListener(this);

    }

    // 3
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                mTextView.setText("버튼 눌림");

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view1:
                break;
        }
    }

}
