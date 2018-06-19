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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trip);
        Log.d(TAG,"onCreate: Starting");
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Intent intent = getIntent();

        String activeTrip = intent.getStringExtra("tripName");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(activeTrip);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new DetailsTripFragment(),"Details");
        adapter.addFragment(new ItieraryTripFragment(), "Itinerary");
        viewPager.setAdapter(adapter);
    }
}
