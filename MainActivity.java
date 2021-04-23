package com.example.iamhomebroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendHomeBroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("com.example.I_AM_HOME");
        intent.putExtra("Meal", "1");
        intent.setComponent(new ComponentName("com.example.foodieapp","com.example.foodieapp.MyBroadcastReceiver"));
        sendBroadcast(intent);
    }

}