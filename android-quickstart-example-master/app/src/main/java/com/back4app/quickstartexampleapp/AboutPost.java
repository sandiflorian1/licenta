package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;



public class AboutPost extends AppCompatActivity {
    private String postId;
    private ImageView imagev;
    private TextView tripName;
    private TextView reviewTrans;
    private TextView reviewAccom;
    private TextView companyTrans;
    private TextView companyAccom;
    private TextView top1Place;
    private TextView top2Place;
    private TextView top3Place;
    private TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_about_post);
        postId = intent.getStringExtra("postId");
        preparePostData();

        imagev = (ImageView)findViewById(R.id.imgPost);
         tripName = (TextView)findViewById(R.id.title);
         reviewTrans = (TextView) findViewById(R.id.reviewTrans);
         reviewAccom = (TextView) findViewById(R.id.reviewAccom);
         companyTrans = (TextView) findViewById(R.id.companyTrans);
         companyAccom = (TextView) findViewById(R.id.companyAccom);
         top1Place = (TextView) findViewById(R.id.top1);
         top2Place = (TextView) findViewById(R.id.top2);
         top3Place = (TextView) findViewById(R.id.top3);
        username = (TextView) findViewById(R.id.username);



    }

    private void preparePostData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("objectId", postId);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject post, ParseException e) {
                if (post != null) {

                    reviewTrans.setText(String.valueOf(post.get("reviewTrans")));
                    reviewAccom.setText(String.valueOf(post.get("reviewAccom")));
                    top1Place.setText(String.valueOf(post.get("Top1")));
                    top2Place.setText(String.valueOf(post.get("Top2")));
                    top3Place.setText(String.valueOf(post.get("Top3")));
                    username.setText(String.valueOf(post.get("username")));
                    tripName.setText(String.valueOf(post.get("nameTrip")));

                    ParseQuery<ParseObject> trans = ParseQuery.getQuery("Transport");
                    trans.whereEqualTo("TripBy", post.get("TripBy"));

                    trans.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject trans, ParseException e) {
                            if(trans != null){
                                companyTrans.setText(String.valueOf(trans.get("nameTrans")));
                            }

                        }
                    });

                    ParseQuery<ParseObject> accom = ParseQuery.getQuery("Accommodation");
                    accom.whereEqualTo("TripBy", post.get("TripBy"));

                    accom.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject accom, ParseException e) {
                            if(accom != null){
                                companyAccom.setText(String.valueOf(accom.get("nameAccom")));
                            }

                        }
                    });

                    ParseFile file = (ParseFile) post.get("image");


                    file.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {

                            if (e == null && data != null) {

                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                imagev.setImageBitmap(bitmap);
                            }


                        }
                    });

                } else {

                }
            }
        });

    }


}
