package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class TripsFragment extends Fragment {

    private static final String TAG = "TripsFragment";
    private Button tripBtn;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trips_tab, container,false);
        tripBtn = (Button) view.findViewById(R.id.tripBtn);

        tripBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"ai apasat tripBtn",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddTrip.class);
                startActivity(intent);
            }

        });

        final ArrayList<String> trips = new ArrayList<String>();

        final ListView tripsListView = (ListView) view.findViewById(R.id.tripsView);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, trips);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        ParseQuery<ParseObject> innerQuery  = ParseQuery.getQuery("User");
        innerQuery.whereEqualTo("nameuser", ParseUser.getCurrentUser().getUsername());
        query.whereDoesNotMatchQuery("User", innerQuery);
        Log.i("Info", String.valueOf(ParseUser.getCurrentUser().getObjectId()));
        query.addDescendingOrder("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseObject trip : objects) {
                            trips.add(String.valueOf(trip.get("nameTrip")));
                            Log.i("Info", String.valueOf(trip.get("nameTrip")));

                        }

                        tripsListView.setAdapter(arrayAdapter);
                    }


                } else {

                    e.printStackTrace();

                }

            }
        });

        return view;
    }
}
