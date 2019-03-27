package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private RequestQueueSingleton requestQueueSingleton;
    private RequestQueue requestQueue;
    private RequestHelper requestHelper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTextView = new TextView(this);
        mTextView = findViewById(R.id.textView);
        mTextView.setText("N/A");

        requestHelper = new RequestHelper(mTextView);
        RequestQueueSingleton requestQueueSingleton = RequestQueueSingleton.getInstance(this.getApplicationContext());
        requestQueue = requestQueueSingleton.getRequestQueue();
        requestQueue.start();

        //String url = "https://staging.osipi.com/identity/.well-known/openid-configuration";
        //requestHelper.Send(requestQueueSingleton, Request.Method.GET, url, null);
    }
}

/*
public class MainActivity extends AppCompatActivity {
    private MagnetometerListener mMagnetometerListener;
    private TextView mTextView;
    private SensorManager mSensorManager;
    private PowerManager mPowerManager;
    private WindowManager mWindowManager;
    private Display mDisplay;
    private PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Get an instance of the PowerManager
        mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);

        // Get an instance of the WindowManager
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = mWindowManager.getDefaultDisplay();

        // Create a bright wake lock
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass()
                .getName());

        // instantiate our simulation view and set it as the activity's content
        mMagnetometerListener = new MagnetometerListener();
        //mSimulationView.setBackgroundResource(R.drawable.ic_launcher_background);

        mTextView = findViewById(R.id.textView);
        mTextView.setText("0");
    }


    @Override
    protected void onResume() {
        super.onResume();
        *//*
         * when the activity is resumed, we acquire a wake-lock so that the
         * screen stays on, since the user will likely not be fiddling with the
         * screen or buttons.
         *//*
        mWakeLock.acquire();

        // Start the simulation
        mMagnetometerListener.startSimulation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        *//*
         * When the activity is paused, we make sure to stop the simulation,
         * release our sensor resources and wake locks
         *//*

        // Stop the simulation
        mMagnetometerListener.stopSimulation();

        // and release our wake-lock
        mWakeLock.release();
    }

    class MagnetometerListener implements SensorEventListener {
        private Sensor mMagnetometer;

        public void startSimulation() {
            mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_GAME);
        }

        public void stopSimulation() {
            mSensorManager.unregisterListener(this);
        }

        public MagnetometerListener() {
            mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() != Sensor.TYPE_MAGNETIC_FIELD)
                return;

            double magneticValue = Math.sqrt((event.values[0]*event.values[0]) + (event.values[1]*event.values[1]) + (event.values[2]*event.values[2]));
            mTextView.setText(Double.toString(magneticValue));
            //mTextView.setText("x: " + Float.toString(event.values[0]) + " y: " + Float.toString(event.values[1]) + " z: " + Float.toString(event.values[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}*/
