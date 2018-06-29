package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailsTripFragment extends Fragment {

    private static final String TAG = "Details";
    private Button addTrans;
    private Button addAcc;
    private List<TransportClass> transList = new ArrayList<>();
    private TransAdapter mAdapter;
    private List<AccommodationClass> accList = new ArrayList<>();
    private AccAdapter mAdapterAcc;
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_trip_tab, container,false);
        View rootView = inflater.inflate(R.layout.details_trip_tab, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView rv2 = (RecyclerView) rootView.findViewById(R.id.recycler_view_acc);
        addTrans = (Button) rootView.findViewById(R.id.addTrans);
        addAcc = (Button) rootView.findViewById(R.id.addAcc);
        final String tripId = getArguments().getString("tripCurrentId");
        final String tripName = getArguments().getString("tripName");

        Log.i("etalii id",String.valueOf(tripId));

        Log.i("etalii id",String.valueOf(tripName));


        //list
        rv.setHasFixedSize(true);
        rv2.setHasFixedSize(true);

        mAdapter = new TransAdapter(transList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(mAdapter);

        prepareTransData(String.valueOf(tripId));

        mAdapterAcc = new AccAdapter(accList);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        rv2.setLayoutManager(mLayoutManager2);
        rv2.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv2.setAdapter(mAdapterAcc);

        prepareAccData(String.valueOf(tripId));

        addAcc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getActivity(),"ai apasat netbtn",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddAccommodation.class);
                intent.putExtra("tripId", tripId);
                intent.putExtra("tripName", tripName);
                startActivity(intent);
            }
        });

        addTrans.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getActivity(),"ai apasat netbtn",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddTransport.class);
                intent.putExtra("tripId", tripId);
                intent.putExtra("tripName", tripName);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void prepareTransData(String tripId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        query.whereEqualTo("objectId", tripId);



        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject trip, ParseException e) {
                if (trip != null) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Transport");
                    query.whereEqualTo("TripBy",trip);

                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> transports, ParseException e) {

                            if (e == null) {

                                if (transports.size() > 0) {

                                    for (ParseObject trans : transports) {
                                        String name = String.valueOf(trans.get("nameTrans"));
                                        String reservNr = String.valueOf(trans.get("reservationNo"));
                                        String note = String.valueOf(trans.get("noteTrans"));
                                        Number price = trans.getNumber("price");
                                        String depStation = String.valueOf(trans.get("depStation"));
                                        String arrStation = String.valueOf(trans.get("arrStation"));
                                        Date depDate = trans.getDate("departureDate");
                                        Date arrDate =  trans.getDate("arrivalDate");

                                        final java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
                                        String depD = String.valueOf(dateFormat.format(depDate.getTime()));

                                        final java.text.DateFormat dateFormat2 = java.text.DateFormat.getDateTimeInstance();
                                        String arrD = String.valueOf(dateFormat2.format(arrDate.getTime()));

                                        String priceS = String.valueOf(price);

                                        TransportClass transC = new TransportClass(name, reservNr, depStation, arrStation, note, priceS, depD, arrD);
                                        transList.add(transC);

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

    private void prepareAccData(String tripId) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Trip");
        query.whereEqualTo("objectId", tripId);

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject trip, ParseException e) {
                if (trip != null) {

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Accommodation");
                    query.whereEqualTo("TripBy",trip);

                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> accommodtions, ParseException e) {

                            if (e == null) {

                                if (accommodtions.size() > 0) {

                                    for (ParseObject acc : accommodtions) {
                                        String name = String.valueOf(acc.get("nameAcc"));
                                        String reservNr = String.valueOf(acc.get("reservationNo"));
                                        String note = String.valueOf(acc.get("noteAcc"));
                                        Number price = acc.getNumber("price");
                                        String address = String.valueOf(acc.get("addressAcc"));
                                        Date checkin = acc.getDate("CheckInDate");
                                        Date checkout =  acc.getDate("CheckOutDate");

                                        final java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
                                        String checkinDate = String.valueOf(dateFormat.format(checkin.getTime()));

                                        final java.text.DateFormat dateFormat2 = java.text.DateFormat.getDateTimeInstance();
                                        String checkoutDate = String.valueOf(dateFormat2.format(checkout.getTime()));

                                        String priceS = String.valueOf(price);

                                        AccommodationClass accC = new AccommodationClass(name, reservNr, address, note, priceS, checkinDate, checkoutDate);
                                        accList.add(accC);

                                    }

                                    mAdapterAcc.notifyDataSetChanged();
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
