package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TripsFragment extends Fragment {

    private static final String TAG = "Trip";
    private Button tripBtn;
    private List<TripClass> tripList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TripAdapter mAdapter;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trips_tab, container,false);

        View rootView = inflater.inflate(R.layout.trips_tab, container, false);
        tripBtn = (Button) rootView.findViewById(R.id.tripBtn);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        rv.setHasFixedSize(true);

       // TripAdapter adapter = new TripAdapter(new String[]{"test one", "test two", "test three", "test four", "test five" , "test six" , "test seven"});
        mAdapter = new TripAdapter(tripList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(mAdapter);

        rv.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TripClass trip = tripList.get(position);
                //Toast.makeText(getContext(), trip.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CurrentTrip.class);
                intent.putExtra("tripName", trip.getTitle());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        tripBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"ai apasat tripBtn",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddTrip.class);
                startActivity(intent);
            }

        });
        prepareTripData();
        return rootView;
    }
    private void prepareTripData() {

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
                            String name = String.valueOf(trip.get("nameTrip"));
                            Date startDate = trip.getDate("startDate");
                            Date endDate =  trip.getDate("startDate");

                            Calendar calendarS = Calendar.getInstance();
                            calendarS.setTime(startDate);

                            String startDateTrip = String.valueOf(calendarS.get(Calendar.DAY_OF_MONTH)) + " " + calendarS.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());

                            Calendar calendarE = Calendar.getInstance();
                            calendarE.setTime(endDate);
                            String endDateTrip = String.valueOf(calendarE.get(Calendar.DAY_OF_MONTH)) + " " + calendarE.getDisplayName(Calendar.MONTH,Calendar.SHORT, Locale.getDefault());

                            String year = String.valueOf(calendarE.get(Calendar.YEAR));
                            TripClass tripC = new TripClass(name, startDateTrip, endDateTrip, year);
                            tripList.add(tripC);

                        }

                        mAdapter.notifyDataSetChanged();
                    }


                } else {

                    e.printStackTrace();

                }

            }
        });

    }
}
