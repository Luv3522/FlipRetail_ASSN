package com.example.flipreatil_assn;

import static android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));

        Intent notificationService = new Intent(this,NotificationListener.class);
        startService(notificationService);
    }
}