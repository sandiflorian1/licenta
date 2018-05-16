package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class registerUser extends AppCompatActivity {

    public void goToRegisterUser(View view){
        EditText userEditText = (EditText) findViewById(R.id.userText);
        EditText emailEditText = (EditText) findViewById(R.id.emailText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordText);
        EditText confirmPassEditText = (EditText) findViewById(R.id.confirmPass);
        Log.i("Signup", "aici");
        String pass = passwordEditText.getText().toString();
        String confirmPass = confirmPassEditText.getText().toString();
        if (pass.equals(confirmPass)) {

            ParseUser user = new ParseUser();
            user.setUsername(userEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        Log.i("Signup", "Successful");
                        Intent intent = new Intent(getApplicationContext(),LoginUser.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(registerUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }else {
            Log.i("Signup", passwordEditText.getText().toString());
            Log.i("Signup", confirmPassEditText.getText().toString());
            Toast.makeText(registerUser.this,"Wrong match password", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }
}
