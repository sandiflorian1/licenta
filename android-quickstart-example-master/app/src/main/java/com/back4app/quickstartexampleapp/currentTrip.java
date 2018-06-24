package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class CurrentTrip extends AppCompatActivity {
    private static final String TAG = "CurrentTrip";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private String tripId;
    private String tripName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trip);
        Log.d(TAG,"onCreate: Starting");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        Intent intent = getIntent();
        tripId = intent.getStringExtra("tripId");
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPagerX(mViewPager, tripId);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tripName = intent.getStringExtra("tripName");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(tripName);

    }

    private void setupViewPagerX(ViewPager viewPager, String tripId){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        DetailsTripFragment detailsFrag = new DetailsTripFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tripCurrentId", tripId);
        bundle.putString("tripName", tripName);
        detailsFrag.setArguments(bundle);
        adapter.addFragment(detailsFrag,"Details");
        adapter.addFragment(new ItieraryTripFragment(), "Itinerary");
        viewPager.setAdapter(adapter);
    }

}
