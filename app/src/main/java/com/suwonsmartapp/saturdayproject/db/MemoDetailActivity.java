package com.suwonsmartapp.saturdayproject.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.suwonsmartapp.saturdayproject.R;

public class MemoDetailActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mContentsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        mTitleEditText = (EditText) findViewById(R.id.title_edit);
        mContentsEditText = (EditText) findViewById(R.id.contents_edit);
    }

    @Override
    public void onBackPressed() {
        // back key 처리
        Intent intent = new Intent();
        intent.putExtra("title", mTitleEditText.getText().toString());
        intent.putExtra("contents", mContentsEditText.getText().toString());

        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}
