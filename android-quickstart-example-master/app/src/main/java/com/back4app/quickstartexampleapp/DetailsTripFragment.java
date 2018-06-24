package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailsTripFragment extends Fragment {

    private static final String TAG = "Details";
    private Button addTrans;
    private Button addAcc;
    private List<TransportClass> transList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TransAdapter mAdapter;
    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_trip_tab, container,false);
        View rootView = inflater.inflate(R.layout.details_trip_tab, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        addTrans = (Button) view.findViewById(R.id.addTrans);
        addAcc = (Button) view.findViewById(R.id.addAcc);
        final String tripId = getArguments().getString("tripCurrentId");
        final String tripName = getArguments().getString("tripName");

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

        //list
        rv.setHasFixedSize(true);

        mAdapter = new TransAdapter(transList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(mAdapter);

        prepareTransData();
        return rootView;
    }

    private void prepareTransData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Transport");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> transports, ParseException e) {

                if (e == null) {

                    if (transports.size() > 0) {

                        for (ParseObject trans : transports) {
                            String name = String.valueOf(trans.get("nameTrip"));
                            String reservNr = String.valueOf(trans.get("reservationNo"));
                            String note = String.valueOf(trans.get("noteTrans"));
                            Number price = trans.getNumber("price");
                            String depStation = String.valueOf(trans.get("depStation"));
                            String arrStation = String.valueOf(trans.get("arrStation"));
                            Date depDate = trans.getDate("depDate");
                            Date arrDate =  trans.getDate("arrDate");

                            TransportClass transC = new TransportClass(name, reservNr, depStation, arrStation, note, price, depDate, arrDate);
                            transList.add(transC);

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
