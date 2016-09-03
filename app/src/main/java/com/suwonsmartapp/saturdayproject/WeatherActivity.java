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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.suwonsmartapp.saturdayproject.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final ListView listView = (ListView) findViewById(R.id.list_view);

        String url = "http://suwonsmartapp.iptime.org/test/junsuk/weather.json";
        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<Weather> data = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        String country = response.getJSONObject(i).getString("country");
                        String temperature = response.getJSONObject(i).getString("temperature");
                        String weather = response.getJSONObject(i).getString("weather");

                        data.add(new Weather(country, temperature, weather));
                    }

                    WeatherAdapter adapter = new WeatherAdapter(data);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, url, null, responseListener, errorListener);

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);


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
