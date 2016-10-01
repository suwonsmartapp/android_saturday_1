package com.suwonsmartapp.saturdayproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.suwonsmartapp.saturdayproject.models.Weather;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Weather>> {

    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ListView listView = (ListView) findViewById(R.id.list_view);

        mAdapter = new WeatherAdapter(null);
        listView.setAdapter(mAdapter);

        // 로더 시작
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<ArrayList<Weather>> onCreateLoader(int id, Bundle args) {
        return new WeatherAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Weather>> loader, ArrayList<Weather> data) {
        mAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Weather>> loader) {
        mAdapter.swapData(null);
    }

    private static class WeatherAsyncTaskLoader extends AsyncTaskLoader<ArrayList<Weather>> {
        private OkHttpClient client = new OkHttpClient();

        private ArrayList<Weather> mData;

        public WeatherAsyncTaskLoader(Context context) {
            super(context);
            forceLoad();
        }

        @Override
        public ArrayList<Weather> loadInBackground() {
            try {
                String result = run("http://suwonsmartapp.iptime.org/test/junsuk/weather.json");
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Weather>>(){}.getType();
                mData = gson.fromJson(result, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mData;
        }

        private String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

    }

    private static class WeatherAdapter extends BaseAdapter {
        private HashMap<String, String> mImageMap = new HashMap<>();

        private ArrayList<com.suwonsmartapp.saturdayproject.models.Weather> mData;

        public WeatherAdapter(ArrayList<com.suwonsmartapp.saturdayproject.models.Weather> data) {
            mData = data;

            mImageMap.put("비", "http://suwonsmartapp.iptime.org/test/junsuk/rainy.png");
            mImageMap.put("맑음", "http://suwonsmartapp.iptime.org/test/junsuk/sunny.png");
            mImageMap.put("눈", "http://suwonsmartapp.iptime.org/test/junsuk/snow.png");
            mImageMap.put("흐림", "http://suwonsmartapp.iptime.org/test/junsuk/cloudy.png");
            mImageMap.put("우박", "http://suwonsmartapp.iptime.org/test/junsuk/blizzard.png");
        }

        public void swapData(ArrayList<Weather> data) {
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            }
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
