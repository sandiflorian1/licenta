package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddPlace extends AppCompatActivity {
    public String tripId;
    public String tripName;

    public void addPlace (View view){
        EditText namePlace = (EditText) findViewById(R.id.namePlace);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

        ParseObject Place = new ParseObject("Place");

        Place.put("TripBy", ParseObject.createWithoutData("Trip",tripId));
        Place.put("namePlace", namePlace.getText().toString());
        Place.put("latitude", latitude.getText().toString());
        Place.put("longitude", longitude.getText().toString());

        Place.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if( e == null){
                    Log.i("create", "creare trip cu succes");
                    Intent intent = new Intent(getApplicationContext(),CurrentTrip.class);
                    intent.putExtra("tripName", tripName);
                    intent.putExtra("tripId", tripId);
                    startActivity(intent);
                }else{
                    Log.i("create", "creare place cu eroare");
                    Log.i("create", String.valueOf(e));
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        Intent intent = getIntent();
        tripId = intent.getStringExtra("tripId");
        tripName = intent.getStringExtra("tripName");

//        double latitude = Double.parseDouble(latlong[0]);
//        double longitude = Double.parseDouble(latlong[1]);
    }
}
