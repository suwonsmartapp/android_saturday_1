package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LifeCycleActivity extends AppCompatActivity {
    private static final String TAG = LifeCycleActivity.class.getSimpleName();
    private TextView mTextView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        mTextView = (TextView) findViewById(R.id.text_view);

        // 1. View
        mListView = (ListView) findViewById(R.id.list_view);

        // 2. Data
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("data" + i);
        }

        // 3. Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                data);

        mListView.setAdapter(adapter);


        // 자료 복원
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");

        // 자료 복원
        String text = savedInstanceState.getString("text");
        mTextView.setText(text);

        // 리스트뷰 상태 복원
        mListView.setSelection(savedInstanceState.getInt("position") + 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 자료 저장
        outState.putString("text", mTextView.getText().toString());

        // 리스트뷰 상태 저장
        outState.putInt("position", mListView.getFirstVisiblePosition());

        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void change(View view) {
        mTextView.setText("변경 됨");
    }
}
