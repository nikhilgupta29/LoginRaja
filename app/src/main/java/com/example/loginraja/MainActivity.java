package com.example.loginraja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    //Splash Screen Activity
    public static int Time1 = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Splash = new Intent(MainActivity.this,StartPage.class);
                startActivity(Splash);
                finish();
            }
        },Time1);
    }
}
