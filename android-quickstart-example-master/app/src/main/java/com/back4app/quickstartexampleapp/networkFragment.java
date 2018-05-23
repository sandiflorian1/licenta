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
import android.widget.Toast;

public class NetworkFragment extends Fragment {

    private static final String TAG = "Network";
    private Button netbtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_tab, container,false);
        netbtn = (Button) view.findViewById(R.id.netbtn);

        netbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"ai apasat netbtn",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CreatePost.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
