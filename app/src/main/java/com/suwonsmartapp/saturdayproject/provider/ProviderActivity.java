package com.suwonsmartapp.saturdayproject.provider;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.suwonsmartapp.saturdayproject.R;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        ListView listView = (ListView) findViewById(R.id.list_view);


        Cursor cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                null,
                null,
                null,
                null);

        ContactsCursorAdapter adapter = new ContactsCursorAdapter(this, cursor);

        listView.setAdapter(adapter);
    }

    private class ContactsCursorAdapter extends CursorAdapter {

        public ContactsCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            String displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            TextView textView = (TextView)view.findViewById(android.R.id.text1);
            textView.setText(displayName);
        }
    }
}
