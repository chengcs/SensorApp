package com.example.sensorapp;

import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
    TextView mTextView;

    public RequestHelper(TextView textView) {
        mTextView = textView;
    }

    public void SendOmfMessage(RequestQueueSingleton requestQueueSingleton, int requestMethod, String url, JSONArray jsonContent, final Map<String, String> headers) //long timestamp, float velX, float velY, float velZ, float posX, float posY, float posZ)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(requestMethod, url, jsonContent,
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        requestQueueSingleton.addToRequestQueue(jsonArrayRequest);
    }

    public Map<String, String> CreateOmfHeaders(String bearerToken, String messageType, String action, Boolean setContentType) {
        Map<String, String> headers = new HashMap<String, String>();
        if (setContentType) {
            headers.put("Content-Type", "application/json");
        }
        headers.put("authentication", "bearer " + bearerToken);
        headers.put("messagetype", messageType);
        headers.put("action", action);
        headers.put("messageformat", "JSON");
        headers.put("omfversion", "1.1");

        return headers;
    }

    public Map<String, String> CreateAuthenticationHeaders(String bearerToken, String messageType, String action, Boolean setContentType) {
        Map<String, String> headers = new HashMap<String, String>();

        headers.put("Content-Type", "multipart/form-data");

        return headers;
    }

    public void CreateAuthenticationBody(String clientId, String clientSecret) {
        JSONArray body = new JSONArray();
        body.put("client_Id", clientId);
        body.put("client_secret", clientSecret);
        body.put("grant_type", "client_credentials");

    }

}
