package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void goToLogin(View view){
        Intent intent = new Intent(getApplicationContext(),LoginUser.class);
        startActivity(intent);
        Log.i("Info","Buton apaaasat");

    }
    public void goToRegister(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterUser.class);
        startActivity(intent);
        Log.i("Info","Buton apasat");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
