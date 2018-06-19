package com.back4app.quickstartexampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailsTripFragment extends Fragment {

    private static final String TAG = "Details";
    //private Button netbtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_trip_tab, container,false);
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
