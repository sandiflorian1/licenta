package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginUser extends AppCompatActivity {

    public void loginUser (View view){
        EditText usernameEditText = (EditText) findViewById(R.id.userText);
        EditText passwordEditText = (EditText) findViewById(R.id.passText);
        ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if (user != null) {

                    Log.i("Info", "Login successful");
                    Intent intent = new Intent(getApplicationContext(),MainApp.class);
                    startActivity(intent);

                } else {

                    Log.i("Info", "Eoare la logare");

                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

//        RelativeLayout backgroundRelativeLayoutLogin = (RelativeLayout) findViewById(R.id.backgroundRelativeLayoutLogin);
//        backgroundRelativeLayoutLogin.setOnClickListener(this);
    }


}
