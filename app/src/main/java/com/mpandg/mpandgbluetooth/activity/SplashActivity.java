package com.mpandg.mpandgbluetooth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mpandg.mpandgbluetooth.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start the activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                // close this activity
                finish();
            }
        }, 1000);
    }
}
