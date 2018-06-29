package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ItieraryTripFragment extends Fragment {
    private static final String TAG = "Itinerary";
    private Button netbtn;
    private Button addPlaceMan;
    private List<PlaceClass> placeList = new ArrayList<>();
    private PlaceAdapter mAdapter;
    private RecyclerView recyclerView;
    public String tripId;
    public String tripName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.itinerary_trip_tab, container,false);
        View rootView = inflater.inflate(R.layout.itinerary_trip_tab, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        tripId = getArguments().getString("tripCurrentId");
        tripName = getArguments().getString("tripName");


        addPlaceMan = (Button) rootView.findViewById(R.id.addPlaceMan);

        addPlaceMan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPlace.class);
                intent.putExtra("tripId", tripId);
                intent.putExtra("tripName", tripName);
                startActivity(intent);
            }
        });

        rv.setHasFixedSize(true);

        mAdapter = new PlaceAdapter(placeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(mAdapter);

        rv.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PlaceClass place = placeList.get(position);
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q=" + place.getName());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        preparePlaceData();

        return rootView;
    }


    private void preparePlaceData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        query.whereEqualTo("objectId", tripId);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject trip, ParseException e) {
                if (trip != null) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
                    query.whereEqualTo("TripBy",trip);

                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> places, ParseException e) {

                            if (e == null) {

                                if (places.size() > 0) {

                                    for (ParseObject place : places) {
                                        String name = String.valueOf(place.get("namePlace"));
                                        String latitude = String.valueOf(place.get("latitude"));
                                        String longitude = String.valueOf(place.get("longitude"));

                                        PlaceClass placeC = new PlaceClass(name, latitude, longitude);
                                        placeList.add(placeC);

                                    }

                                    mAdapter.notifyDataSetChanged();
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
