package com.suwonsmartapp.saturdayproject.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.suwonsmartapp.saturdayproject.R;

public class MemoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.save_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                finish();
                break;
            case R.id.save_button:
                EditText title = (EditText) findViewById(R.id.title_edit);
                EditText contents = (EditText) findViewById(R.id.contents_edit);

                Memo memo = new Memo();
                memo.setTitle(title.getText().toString());
                memo.setContents(contents.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("memo", memo);

                setResult(RESULT_OK, intent);
                finish();

                break;
        }
    }
}
