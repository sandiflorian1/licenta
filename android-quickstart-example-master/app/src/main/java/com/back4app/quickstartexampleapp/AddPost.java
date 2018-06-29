package com.back4app.quickstartexampleapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public List<String> tripList = new ArrayList<>();
    public List<String> placesList = new ArrayList<>();
    private ArrayAdapter<String> dataAdapter;
    private ArrayAdapter<String> dataAdapterTop;
    ParseObject post = new ParseObject("Post");

    public String idTrip;

    public void getPhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);

    }

    public void goToGetPhoto(View view){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            } else {

                getPhoto();

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                Log.i("Photo", "Received");

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] byteArray = stream.toByteArray();

                ParseFile file = new ParseFile("image.png", byteArray);

                post.put("image", file);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getPhoto();

            }

        }

    }

    public void addPost(View view){
        EditText reviewTrans = (EditText) findViewById(R.id.reviewTrans);
        EditText reviewAccom = (EditText) findViewById(R.id.reviewAccom);

        post.put("createdBy", ParseUser.getCurrentUser());
        post.put("username", ParseUser.getCurrentUser().getUsername());
        post.put("TripBy", ParseObject.createWithoutData("Trip",idTrip));
        post.put("reviewTrans", reviewTrans.getText().toString());
        post.put("reviewAccom", reviewAccom.getText().toString());


        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if( e == null){
                    Log.i("create", "creare trip cu succes");
                    Intent intent = new Intent(getApplicationContext(),MainApp.class);
                    startActivity(intent);
                }else{
                    Log.i("create", "creare trip cu eroare");
                    Log.i("create", String.valueOf(e));
                }
            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        Spinner spinner = findViewById(R.id.planets_spinner);
        Spinner spinnerTop1 = findViewById(R.id.spinner1);
        Spinner spinnerTop2 = findViewById(R.id.spinner2);
        Spinner spinnerTop3 = findViewById(R.id.spinner3);

        spinner.setOnItemSelectedListener(this);


        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tripList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prepareTripData();

        spinner.setAdapter(dataAdapter);

        dataAdapterTop = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, placesList);
        dataAdapterTop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTop1.setAdapter(dataAdapterTop);
        spinnerTop2.setAdapter(dataAdapterTop);
        spinnerTop3.setAdapter(dataAdapterTop);

        spinnerTop1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3)
            {
                post.put("Top1", parent.getItemAtPosition(position).toString());
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        });
        spinnerTop2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3)
            {
                post.put("Top2", parent.getItemAtPosition(position).toString());
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        });
        spinnerTop3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3)
            {
                post.put("Top3", parent.getItemAtPosition(position).toString());
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
                // TODO Auto-generated method stub
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        preparePlacesData( adapterView.getItemAtPosition(pos).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void prepareTripData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        Log.i("Info", String.valueOf(ParseUser.getCurrentUser().getObjectId()));
        query.whereLessThan("endDate",Calendar.getInstance().getTime());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> trips, ParseException e) {

                if (e == null) {

                    if (trips.size() > 0) {

                        for (ParseObject trip : trips) {
                            String name = String.valueOf(trip.get("nameTrip"));
                            Log.i("Trip:", name);
                            tripList.add(name);
                        }
                        dataAdapter.notifyDataSetChanged();
                    }
                } else {

                    e.printStackTrace();

                }

            }
        });

    }
    private void preparePlacesData(String nameTrip) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        query.whereEqualTo("nameTrip", nameTrip);
        post.put("nameTrip",nameTrip);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject trip, ParseException e) {
                if (trip != null) {

                    idTrip = trip.getObjectId();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
                    query.whereEqualTo("TripBy", trip);

                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> places, ParseException e) {

                            if (e == null) {
                                Log.i("merime vec", String.valueOf(places.size()));

                                if (places.size() > 0) {

                                    for (ParseObject place : places) {
                                        String name = String.valueOf(place.get("namePlace"));
                                        Log.i("Info", name);
                                        placesList.add(name);
                                    }
                                    dataAdapterTop.notifyDataSetChanged();
                                }
                            } else {

                                e.printStackTrace();

                            }

                        }
                    });

                } else {

                }
            }
        });



    }
}
