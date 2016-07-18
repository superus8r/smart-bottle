package com.mpandg.mpandgbluetooth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mpandg.mpandgbluetooth.Const;
import com.mpandg.mpandgbluetooth.R;
import com.mpandg.mpandgbluetooth.Utils;

public class SplashActivity extends AppCompatActivity {

    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start the activity

                if (Utils.isFirstTimeUse(SplashActivity.this)) {

                    // open registration activity.
                    startActivityForResult(new Intent(SplashActivity.this, RegistrationActivity.class), Const.REQUEST_CODE_REGISTRATION);
                } else {

                    // open main activity.
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    // close this activity
                    finish();
                }

            }
        }, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Const.REQUEST_CODE_REGISTRATION && resultCode == RESULT_OK) {

            // the user has registered successfully; invalidate first time use and open main activity.
            Utils.invalidateFirstTimeUse(this);
            startActivity(new Intent(this, MainActivity.class));
            // close this activity
            finish();
        } else {

            // user has not registered, keep this activity and show registration button.
            btRegister = (Button) findViewById(R.id.bt_register);
            btRegister.setVisibility(View.VISIBLE);
            btRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // open registration activity.
                    startActivityForResult(new Intent(SplashActivity.this, RegistrationActivity.class), Const.REQUEST_CODE_REGISTRATION);
                }
            });
        }
    }
}
