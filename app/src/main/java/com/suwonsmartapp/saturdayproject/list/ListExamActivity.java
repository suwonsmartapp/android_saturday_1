package com.suwonsmartapp.saturdayproject.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.suwonsmartapp.saturdayproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 교재 1015 페이지 참고
 */
public class ListExamActivity extends AppCompatActivity {

    private ArrayList<Memo> mData;
    private MemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam);

        // 3. view
        ListView listView = (ListView) findViewById(R.id.list);

        // 1. data
        mData = new ArrayList<>();

        // 2. adapter
        mAdapter = new MemoAdapter(this, mData);
        listView.setAdapter(mAdapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExamActivity.this, MemoActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            Memo memo = (Memo) data.getSerializableExtra("memo");
            mData.add(0, memo);
            mAdapter.notifyDataSetChanged();
        }
    }

    static class MyContactAdapter extends BaseAdapter {
        private static final String TAG = MyContactAdapter.class.getSimpleName();
        private final Context mContext;
        private final List<Person> mData;

        public MyContactAdapter(Context context, List<Person> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "getView: " + position + ", convertView: " + convertView);

            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
                TextView name = (TextView) convertView.findViewById(android.R.id.text1);
                TextView phoneNumber = (TextView) convertView.findViewById(android.R.id.text2);

                viewHolder = new ViewHolder();
                viewHolder.name = name;
                viewHolder.phoneNumber = phoneNumber;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Person person = mData.get(position);
//            Person person2 = (Person) getItem(position);

            PhoneNumberFormattingTextWatcher watcher = new PhoneNumberFormattingTextWatcher();

            viewHolder.name.setText(person.getName());
            viewHolder.phoneNumber.addTextChangedListener(watcher);
            viewHolder.phoneNumber.setText(person.getPhoneNumber());

            return convertView;
        }

        static class ViewHolder {
            TextView name;
            TextView phoneNumber;
        }
    }
}
