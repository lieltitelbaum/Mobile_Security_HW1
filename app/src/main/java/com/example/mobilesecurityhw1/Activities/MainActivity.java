package com.example.mobilesecurityhw1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilesecurityhw1.Modules.SecureConditions;
import com.example.mobilesecurityhw1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find View
        Button loginBtn = findViewById(R.id.logInBtn);
        final EditText passwordTxt = findViewById(R.id.passwordTxt);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordTxt.getText().length() > 0) {
                    if (checkConditions()) {
                        Log.d("loginActivity", "log in successfully");
                        startActivity(new Intent(MainActivity.this, LoginSuccessActivity.class));
                        finish();
                    } else {
                        Log.d("loginActivity", "log in failed");
                        Toast.makeText(getApplicationContext(), "Please enable one condition or more...", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password can not be empty...", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private boolean checkConditions() {
        SecureConditions sC = new SecureConditions(this);
        return sC.isBatteryPercOver75() && sC.isConnectedToNetwork() && sC.isRingerModeOn() &&
                sC.isDarkModeOn() && sC.isPhoneInPortraitMode() && sC.isGpsEnabled();
    }

}