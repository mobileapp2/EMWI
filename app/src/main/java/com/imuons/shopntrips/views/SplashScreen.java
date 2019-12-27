package com.imuons.shopntrips.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.shopntrips.R;
import com.imuons.shopntrips.utils.SharedPreferenceUtils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(5 * 1000);
                    String token = SharedPreferenceUtils.getAccesstoken(SplashScreen.this);
                    if (token == null) {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(SplashScreen.this, DashboardActivity.class));
                    }
                    finish();

                } catch (Exception e) {

                }
            }

        };
        background.start();
    }
}
