package com.suwonsmartapp.saturdayproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.suwonsmartapp.saturdayproject.R;

public class MemoActivity extends AppCompatActivity {

    private MemoCursorAdapter mAdapter;
    private MemoDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo2);

        mDbHelper = new MemoDbHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MemoActivity.this, MemoDetailActivity.class), 1000);
            }
        });

        ListView listView = (ListView) findViewById(R.id.memo_list);

        // SELECT * FROM memo ORDER BY _id DESC
//        Cursor cursor = mDbHelper.getReadableDatabase().execSQL("SELECT * FROM memo ORDER BY _id DESC");
        Cursor cursor = mDbHelper.getReadableDatabase().query(
                MemoContract.MemoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                "_id DESC"
        );
        mAdapter = new MemoCursorAdapter(this, cursor);

        listView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String contents = data.getStringExtra("contents");

            // DB 저장

            // Gets the data repository in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, title);
            values.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS, contents);

            // Insert the new row, returning the primary key value of the new row
            // INSERT INTO memo (title, contents) VALUES (?, ?)
            long newRowId;
            newRowId = db.insert(
                    MemoContract.MemoEntry.TABLE_NAME,
                    null,
                    values);

            // 화면 갱신
            if (newRowId != -1) {
                Cursor cursor = mDbHelper.getReadableDatabase().query(
                        MemoContract.MemoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        "_id DESC"
                );

//                List<Memo> memoList = new ArrayList<>();
//                cursor.moveToFirst();   // -1
//                while (cursor.moveToNext()) {
//                    Log.d("DB", "onActivityResult: " + cursor.getString(cursor.getColumnIndex("title")));
//
//                    Memo memo = new Memo();
//                    memo.setTitle(cursor.getString(cursor.getColumnIndex("title")));
//                    memo.setContents(cursor.getString(cursor.getColumnIndex("contents")));
//                    memoList.add(memo);
//                }

                mAdapter.swapCursor(cursor);        // baseAdapter 의 notifyDatasetChanged 역할
                Toast.makeText(MemoActivity.this, "insert 성공" + newRowId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MemoActivity.this, "insert 에러", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class MemoCursorAdapter extends CursorAdapter {

        public MemoCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView titleText = (TextView) view.findViewById(android.R.id.text1);

            String title = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_TITLE));
            titleText.setText(title);
        }
    }

}
