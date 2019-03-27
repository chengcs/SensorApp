package com.example.sensorapp;

import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Map;

public class MultipartRequestHelper {
    TextView mTextView;
    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    private final String boundary = "javaauthenticationrequest";

    public MultipartRequestHelper(TextView textView) {
        mTextView = textView;
    }

    public void SendAuthenticationMessage(RequestQueueSingleton requestQueueSingleton, int requestMethod, String url, JSONArray jsonContent, final Map<String, String> headers) //long timestamp, float velX, float velY, float velZ, float posX, float posY, float posZ)
    {
        StringRequest jsonArrayRequest = new StringRequest(requestMethod, url, jsonContent,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        mTextView.setText("Response is: " + response.toString());
                    }

                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work! " + error.getMessage());
            }
        }
        ){ //no semicolon or coma
            @Override
            public String getBodyContentType() {
                return "multipart/form-data;boundary=" + boundary;
            }

        requestQueueSingleton.addToRequestQueue(jsonArrayRequest);
    }
}
