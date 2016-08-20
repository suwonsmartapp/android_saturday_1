package com.suwonsmartapp.saturdayproject.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by junsuk on 16. 8. 20..
 */
public class MemoAdapter extends BaseAdapter {
    private static final String TAG = MemoAdapter.class.getSimpleName();
    private final Context mContext;
    private final List<Memo> mData;

    public MemoAdapter(Context context, List<Memo> data) {
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

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
            TextView name = (TextView) convertView.findViewById(android.R.id.text1);
            TextView phoneNumber = (TextView) convertView.findViewById(android.R.id.text2);

            viewHolder = new ViewHolder();
            viewHolder.title = name;
            viewHolder.contents = phoneNumber;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Memo memo = mData.get(position);

        viewHolder.title.setText(memo.getTitle());
        viewHolder.contents.setText(memo.getContents());

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView contents;
    }
}
