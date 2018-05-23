package com.back4app.quickstartexampleapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;



public class ItieraryTripFragment extends Fragment {
    private static final String TAG = "Itinerary";
   // private Button netbtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_tab, container,false);
//        netbtn = (Button) view.findViewById(R.id.netbtn);
//
//        netbtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Toast.makeText(getActivity(),"ai apasat netbtn",Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

}
