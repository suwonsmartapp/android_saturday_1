package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.suwonsmartapp.saturdayproject.models.Weather;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ListView listView = (ListView) findViewById(R.id.list_view);

        ArrayList<Weather> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Weather("test" + i, "10", "맑음"));
        }

        WeatherAdapter adapter = new WeatherAdapter(data);

        listView.setAdapter(adapter);

    }

    private static class WeatherAdapter extends BaseAdapter {
        private ArrayList<Weather> mData;

        public WeatherAdapter(ArrayList<Weather> data) {
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                // 최초
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
                ImageView image = (ImageView) convertView.findViewById(R.id.image_view);
                TextView country = (TextView) convertView.findViewById(R.id.country);
                TextView temperature = (TextView) convertView.findViewById(R.id.temperature);

                holder.image = image;
                holder.country = country;
                holder.temperature = temperature;

                convertView.setTag(holder);
            } else {
                // 재사용
                holder = (ViewHolder) convertView.getTag();
            }

            // 데이터 셋팅
            Weather weather = mData.get(position);
            holder.country.setText(weather.getCountry());
            holder.temperature.setText(weather.getTemperature());

            return convertView;
        }

        static class ViewHolder {
            ImageView image;
            TextView country;
            TextView temperature;
        }
    }
}
