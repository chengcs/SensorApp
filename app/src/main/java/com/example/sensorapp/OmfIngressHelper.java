package com.example.sensorapp;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OmfIngressHelper {
    private static final String OCS_BASE_URI = "https://staging.osipi.com";
    private static final String OMF_ENDPOINT = "/api/omf";
    private static final String TOKEN_ENDPOINT = "/identity/connect/token";
    private static final String GRANT_TYPE = "client_credentials";

    private static SimpleDateFormat dateFormatter;

    private static Map<String, String> headers;
    public OmfIngressHelper(RequestHelper requestHelper, String producerToken, String messageType, String action, Boolean setContentType)
    {
        headers = requestHelper.CreateOmfHeaders(producerToken, messageType, action, setContentType);
    }

    public static JSONArray add_particle(JSONArray particles, JSONObject particle) {
        if (particles == null) {
            particles = new JSONArray();
        }
        return particles.put(particle);
    }

    public static JSONObject create_particle(JSONArray values, Particle particle) {
        JSONObject data = new JSONObject();
        try {
            data.put("containerid", "\"" + particle.name + "\"");
            data.put("values", values);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static JSONArray add_particle_value(JSONArray values, Particle particle) {
        if (values == null) {
            values = new JSONArray();
        }
        return values.put(create_particle_value(particle));
    }


    private static JSONObject create_particle_value(Particle particle)
    {
        Date date = new Date(particle.timestamp);
        String timestamp = dateFormatter.format(date);

        JSONObject value = new JSONObject();
        try {
            value.put("Timestamp", "\"" + timestamp + "\"");
            value.put("Position X", particle.positionX);
            value.put("Position Y", particle.positionY);
            value.put("Position Z", particle.positionZ);
            value.put("Velocity X", particle.velocityX);
            value.put("Velocity Y", particle.velocityY);
            value.put("Velocity Z", particle.velocityZ);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void send_data(RequestHelper requestHelper, RequestQueueSingleton requestQueueSingleton, JSONArray dataMessage) {
        String uri = OCS_BASE_URI + OMF_ENDPOINT;

        requestHelper.Send(requestQueueSingleton, Request.Method.POST, uri, dataMessage, headers);
    }

    public static String getAccessToken(String clientId, String clientSecret) {
        String uri = OCS_BASE_URI + TOKEN_ENDPOINT;

    }
}



/*        return "[" +
                "{" +
                "\"containerid\": \"" + particle.name + "\"," +
                "\"values\": [" +
                "{" +
                "\"Timestamp\": \"" + timestamp + "\"," +
                "\"Position X\": " + particle.positionX +"," +
                "\"Position Y\": " + particle.positionY +"," +
                "\"Position Z\": " + particle.positionZ +"," +
                "\"Velocity X\": " + particle.velocityX +"," +
                "\"Velocity Y\": " + particle.velocityY +"," +
                "\"Velocity Z\": " + particle.velocityZ +"" +
                "}" +
                "]" +
                "}" +
                "]";*/
