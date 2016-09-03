package com.suwonsmartapp.saturdayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonActivity extends AppCompatActivity {

    String url = "http://suwonsmartapp.iptime.org/test/junsuk/weather.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        Response.Listener<JSONArray> responseListener = new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    String country = response.getJSONObject(0).getString("country");
                    Toast.makeText(JsonActivity.this, country, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JsonActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        };


        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, url, null, responseListener, errorListener);


        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
