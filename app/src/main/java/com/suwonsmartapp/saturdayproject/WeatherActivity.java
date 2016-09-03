package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suwonsmartapp.saturdayproject.models.Weather;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Weather>>(){}.getType();
                ArrayList<Weather> data = gson.fromJson(response.toString(), type);

                WeatherAdapter adapter = new WeatherAdapter(data);
                listView.setAdapter(adapter);
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
        private HashMap<String, String> mImageMap = new HashMap<>();

        private ArrayList<Weather> mData;

        public WeatherAdapter(ArrayList<Weather> data) {
            mData = data;

            mImageMap.put("비", "http://suwonsmartapp.iptime.org/test/junsuk/rainy.png");
            mImageMap.put("맑음", "http://suwonsmartapp.iptime.org/test/junsuk/sunny.png");
            mImageMap.put("눈", "http://suwonsmartapp.iptime.org/test/junsuk/snow.png");
            mImageMap.put("흐림", "http://suwonsmartapp.iptime.org/test/junsuk/cloudy.png");
            mImageMap.put("우박", "http://suwonsmartapp.iptime.org/test/junsuk/blizzard.png");
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
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                // 최초
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
                NetworkImageView image = (NetworkImageView) convertView.findViewById(R.id.image_view);
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
//            holder.image.setImageResource(mImageMap.get(weather.getWeather()));
            // 이미지를 다운로드 -> Bitmap 변환 -> ImageView에 표시

            // Retrieves an image specified by the URL, displays it in the UI.
            String url = mImageMap.get(weather.getWeather());

            ImageLoader imageLoader = MySingleton.getInstance(parent.getContext()).getImageLoader();
            holder.image.setImageUrl(url, imageLoader);


            holder.country.setText(weather.getCountry());
            holder.temperature.setText(weather.getTemperature());

            return convertView;
        }

        static class ViewHolder {
            NetworkImageView image;
            TextView country;
            TextView temperature;
        }
    }
}
